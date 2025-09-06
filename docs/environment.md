# StudyMate - Environment Setup

## 1. Backend (Spring Boot)

### Yêu cầu

- **Java JDK**: 17+
- **Maven**: 3+
- **Spring Boot**: 3.x
- **Database**: PostgreSQL 15+

### Cài đặt & chạy

```bash
# Di chuyển vào thư mục backend
cd backend

# Build project
./mvnw clean install

# Chạy ứng dụng
./mvnw spring-boot:run
```

---

## 2. Frontend (Angular)

### Yêu cầu

- **Node.js**: 18+
- **npm**: 9+
- **Angular CLI**: 16+

### Cài đặt & chạy

```bash
# Di chuyển vào thư mục frontend
cd frontend

# Cài đặt dependencies
npm install

# Chạy dev server
ng serve
```

- Mặc định chạy trên `http://localhost:4200`

---

## 3. Database (PostgreSQL)

- Tạo database `studymate` với user/password trong `.env` hoặc `application.properties`
- Ví dụ config backend (`application.properties`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/studymate
spring.datasource.username=studymate_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## 4. Docker & Docker Compose

- Yêu cầu: cài Docker 24+, Docker Compose
- Chạy toàn bộ stack:

```bash
docker-compose up -d
```

- Docker Compose sẽ khởi tạo:
  - PostgreSQL
  - Backend Spring Boot
  - Frontend Angular
  - Redis (nếu dùng cache, optional)

---

## 5. IDE & Plugins

- **IntelliJ IDEA**: backend + frontend
- **VS Code**: frontend cũng được
- Plugin gợi ý:
  - Lombok
  - Spring Boot
  - Angular Essentials
  - PlantUML (render sơ đồ)

---

## 6. Notes

- Backend: port 8080
- Frontend: port 4200
- Các service Docker có thể cấu hình lại trong `docker-compose.yml`
- Môi trường dev có thể dùng `.env` hoặc `application-dev.properties` để quản lý cấu hình

