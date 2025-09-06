# Kiến trúc StudyMate

## 1. Tổng quan
StudyMate sử dụng kiến trúc **monolithic** với mô hình client-server:
- **Frontend (Angular)**: giao diện người dùng, gọi REST API tới backend.
- **Backend (Spring Boot)**: xử lý business logic, authentication, quản lý dữ liệu.
- **Database (PostgreSQL)**: lưu trữ thông tin người dùng, câu hỏi, câu trả lời, điểm thưởng, bảng xếp hạng.
- **Messaging & Facetime**: hỗ trợ chat real-time và video call thông qua WebSocket/WebRTC.
- **Docker Compose**: quản lý các service, dễ deploy.

## 2. Thành phần chính

| Component               | Mô tả |
|-------------------------|-------|
| Angular Frontend        | Giao diện web/mobile, tương tác với API backend |
| Spring Boot Backend     | REST API, xử lý nghiệp vụ Q&A, leaderboard, points, messaging |
| PostgreSQL              | Lưu dữ liệu người dùng, câu hỏi, trả lời, điểm thưởng |
| Authentication Service  | JWT + OAuth2 (Google) |
| Messaging / Facetime    | Chat, video call real-time |
| Docker Compose          | Quản lý container, DB, backend, frontend |

## 3. Luồng dữ liệu cơ bản

1. **Đăng nhập / Đăng ký**
    - User nhập thông tin → Frontend gửi request → Backend xác thực → trả JWT.
2. **Q&A**
    - User đăng câu hỏi (trừ xu) → Backend lưu DB.
    - Người khác trả lời → Upvote → Backend tính xu thưởng.
3. **Leaderboard**
    - Backend tính điểm từ xu → lưu vào bảng xếp hạng tuần/tháng → Frontend hiển thị.
4. **Messaging / Facetime**
    - Chat 1-1 hoặc nhóm → WebSocket → real-time.
    - Video call → WebRTC → peer-to-peer.

## 4. Sơ đồ kiến trúc

```plantuml
@startuml
actor "Student / Tutor" as User

rectangle "Angular Frontend" as Frontend {
}

rectangle "Spring Boot Backend" as Backend {
  rectangle "REST API" as API
  rectangle "Business Logic" as Logic
  rectangle "Authentication (JWT/OAuth2)" as Auth
  rectangle "Leaderboard / Xu Service" as Leaderboard
  rectangle "Messaging / Facetime" as Messaging
}

database "PostgreSQL" as DB

User --> Frontend : Giao diện web/mobile
Frontend --> API : REST calls (HTTP)
API --> Auth : Xác thực user
API --> Logic : Xử lý nghiệp vụ
Logic --> DB : Lưu & đọc dữ liệu
Leaderboard --> DB
Messaging --> DB
Frontend --> Messaging : Chat / VideoCall

@enduml