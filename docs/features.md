# Tính năng StudyMate

StudyMate là nền tảng học tập cộng đồng, với các tính năng chính sau:

## 1. Authentication
- Đăng ký/Đăng nhập qua:
    - Form (email/username + password)
    - OAuth2: Google, Facebook
- JWT dùng để xác thực API
- Profile user (avatar, điểm xu)

## 2. Quản lý xu (Points)
- Mua, nạp xu
- Tiêu xu để đặt câu hỏi
- Nhận xu khi trả lời được upvote
- Rút xu, quy đổi ra tiền

## 3. Hỏi & Đáp (Q&A)
- User đăng câu hỏi (tiêu xu)
- Người khác trả lời
- Vote câu trả lời
- Câu trả lời nhiều vote nhất nhận xu

## 4. Bảng xếp hạng (Leaderboard)
- BXH tuần/tháng dựa trên điểm xu
- Hiển thị top người dùng
- Vinh danh và gửi thưởng

## 5. Messaging
- Chat 1-1 hoặc nhóm
- Real-time qua WebSocket

## 6. Facetime (Video Call)
- Học nhóm hoặc gia sư online
- Sử dụng WebRTC

## 7. Quản trị (Admin)
- Quản lý user, câu hỏi, báo cáo vi phạm
- Theo dõi hoạt động và xu
