# StudyMate - Requirements

## 1. Business Requirements (BR)
- **Mục tiêu**: Kết nối học sinh và gia sư, tạo môi trường học tập cộng đồng, khuyến khích học tập thông qua điểm thưởng (xu) và bảng xếp hạng.
- **Người dùng**:
    - Học sinh: đặt câu hỏi, trả lời, tích điểm, chat/video call
    - Gia sư: trả lời câu hỏi, nhận thưởng xu, chat/video call
    - Admin: quản lý người dùng, câu hỏi, báo cáo, giám sát xu
- **Giá trị**:
    - Học sinh cải thiện kỹ năng
    - Gia sư có cơ hội nhận thưởng
    - Nền tảng thu hút người dùng tích cực

---

## 2. Functional Requirements (FR)
### 2.1 Authentication & User Management
- Đăng ký/Đăng nhập qua Form, Google, Facebook
- JWT cho xác thực API
- Quản lý profile (avatar, xu, thông tin cá nhân)

### 2.2 Xu System
- Mua, nạp, tiêu xu
- Nhận xu khi trả lời được upvote
- Rút xu / quy đổi ra tiền

### 2.3 Q&A System
- Học sinh đăng câu hỏi (tiêu xu)
- Người khác trả lời câu hỏi
- Vote câu trả lời
- Thưởng xu cho câu trả lời được upvote nhiều nhất

### 2.4 Leaderboard
- BXH tuần/tháng dựa trên điểm xu
- Hiển thị top người dùng
- Vinh danh người đứng đầu và gửi thưởng

### 2.5 Messaging
- Chat 1-1 và nhóm
- Thông báo real-time

### 2.6 Facetime (Video Call)
- Học nhóm hoặc học sinh-gia sư
- Peer-to-peer qua WebRTC

### 2.7 Admin Panel
- Quản lý user, câu hỏi, báo cáo vi phạm
- Theo dõi hoạt động và xu của người dùng

---

## 3. Non-Functional Requirements (NFR)
- **Performance**: hệ thống chịu được >1000 user đồng thời
- **Security**: JWT, OAuth2, HTTPS
- **Scalability**: dễ mở rộng backend / database
- **Reliability**: backup DB định kỳ, logging
- **Maintainability**: cấu trúc module rõ ràng, code dễ mở rộng

---

## 4. User Stories
- Học sinh muốn đặt câu hỏi → trả xu → nhận câu trả lời
- Gia sư muốn trả lời câu hỏi → nhận thưởng xu
- Người dùng muốn chat/video call trực tiếp với bạn cùng học
- Admin muốn giám sát xu, xử lý report vi phạm

---

## 5. Acceptance Criteria (ví dụ)
- Khi học sinh đăng câu hỏi, xu tương ứng bị trừ và câu hỏi xuất hiện trên danh sách
- Câu trả lời được vote nhiều nhất sẽ nhận xu thưởng tự động
- Người dùng có thể gửi/nhận tin nhắn real-time
- Video call hoạt động ổn định giữa 2 người dùng
- Admin có thể xem toàn bộ người dùng và câu hỏi
