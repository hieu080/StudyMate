package com.backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator {
    private final long nodeId;
    private final long epoch = 1694000000000L; // custom epoch // 2023-09-06T00:00:00Z
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    private final int nodeIdBits = 10;
    private final int sequenceBits = 12;
    private final long maxNodeId = (1L << nodeIdBits) - 1;
    private final long maxSequence = (1L << sequenceBits) - 1;

    public SnowflakeIdGenerator(@Value("${snowflake.node-id}") long nodeId) {
        if(nodeId > maxNodeId || nodeId < 0)
            throw new IllegalArgumentException("Node ID out of range");
        this.nodeId = nodeId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                while (timestamp <= lastTimestamp) timestamp = System.currentTimeMillis();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - epoch) << (nodeIdBits + sequenceBits))
                | (nodeId << sequenceBits)
                | sequence;
    }

}
