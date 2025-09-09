package com.backend.mapper;

import com.backend.dto.response.UserResponse;
import com.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse entityToResponse(User user);
}
