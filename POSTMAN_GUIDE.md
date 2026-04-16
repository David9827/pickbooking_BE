# Hướng Dẫn Sử Dụng Postman Collection - Pickbooking Backend

## 📋 Nội Dung
- [Setup Ban Đầu](#setup-ban-đầu)
- [Import Collection & Environment](#import-collection--environment)
- [Kiểm Tra Kết Nối Các Services](#kiểm-tra-kết-nối-các-services)
- [Chi Tiết Services](#chi-tiết-services)
- [Quy Trình Kiểm Tra](#quy-trình-kiểm-tra)

---

## 🚀 Setup Ban Đầu

### 1. Khởi Động Các Services (Theo Thứ Tự)

```bash
# 1. Khởi động MySQL (nếu chưa chạy)
# Port: 3306
# Database: pickbooking
# User: root
# Password: Dovancong2004@

# 2. Khởi động MongoDB (cho Chat App)
# Port: 27017
# URI: mongodb://localhost:27017/test

# 3. Auth Management (Port 8081)
cd auth-managerment
mvn spring-boot:run
# hoặc chạy từ IDE

# 4. Main API (Port 8080)
cd ..
mvn spring-boot:run

# 5. Friendships (Port 8082)
cd friendships
mvn spring-boot:run

# 6. Booking Court (Port 8083)
cd booking-court
mvn spring-boot:run

# 7. Chat App (Port 8085)
cd chat-app
mvn spring-boot:run
```

### 2. Verify Services Đang Chạy

```bash
# Kiểm tra từ terminal
curl http://localhost:8080/api/posts
curl http://localhost:8081/api/admin/users
curl http://localhost:8082/api/friends/1
curl http://localhost:8083/api/admin/clusters
curl http://localhost:8085/api/chat/rooms/1/messages
```

---

## 📥 Import Collection & Environment

### Bước 1: Mở Postman

### Bước 2: Import Collection
1. Click **Collections** (bên trái)
2. Click **Import** button
3. Chọn file: `Pickbooking-API-Collection.postman_collection.json`
4. Click **Import**

### Bước 3: Import Environment
1. Click **Environment** icon (bên trái, dưới Collections)
2. Click **Import** button
3. Chọn file: `Pickbooking-Environment.postman_environment.json`
4. Click **Import**

### Bước 4: Chọn Environment
1. Ở góc trên phải Postman, chọn dropdown
2. Chọn **Pickbooking Backend Environment**

---

## ✅ Kiểm Tra Kết Nối Các Services

### Phase 1: Test Auth Service (Port 8081)

#### 1.1 - Register User
- **Endpoint:** `POST http://localhost:8081/api/auth/register`
- **Body:**
```json
{
  "email": "testuser@example.com",
  "password": "password123",
  "username": "testuser",
  "fullName": "Test User"
}
```
- **Expected:** Status 200/201 với user info
- **Kiểm tra:** Xem user được tạo trong database

#### 1.2 - Login
- **Endpoint:** `POST http://localhost:8081/api/auth/login`
- **Body:**
```json
{
  "email": "testuser@example.com",
  "password": "password123"
}
```
- **Expected:** Status 200 với JWT token
- **Lưu ý:** Copy token này vào biến `auth_token` trong Environment

#### 1.3 - Introspect Token
- **Endpoint:** `POST http://localhost:8081/api/auth/introspect`
- **Header:** Bearer Token
- **Expected:** Validate token thành công

#### 1.4 - Get All Users
- **Endpoint:** `GET http://localhost:8081/api/admin/users`
- **Expected:** Danh sách tất cả users

---

### Phase 2: Test Main API (Port 8080)

#### 2.1 - Login via Main API
- **Endpoint:** `POST http://localhost:8080/api/auth/login`
- **Kiểm tra:** Đặc biệt kiểm tra xem Main API có gọi đến Auth Service (8081) không
- **Kết quả hiện tại:** Main API có comment "// đã chuyển sang auth-managerment ở 8081" nhưng chưa implement

#### 2.2 - Create Post
- **Endpoint:** `POST http://localhost:8080/api/posts`
- **Body:**
```json
{
  "title": "Test Post",
  "content": "This is a test post",
  "userId": 1
}
```
- **Expected:** Post được tạo với ID

#### 2.3 - Get Posts
- **Endpoint:** `GET http://localhost:8080/api/posts`
- **Expected:** Danh sách tất cả posts

#### 2.4 - Add Reaction
- **Endpoint:** `POST http://localhost:8080/api/posts/1/react`
- **Body:**
```json
{
  "type": "LIKE",
  "userId": 1
}
```
- **Expected:** Reaction được tạo

#### 2.5 - Add Comment
- **Endpoint:** `POST http://localhost:8080/api/posts/1/comments`
- **Body:**
```json
{
  "content": "Nice post!",
  "userId": 1
}
```
- **Expected:** Comment được thêm

#### 2.6 - Search
- **Endpoint:** `GET http://localhost:8080/api/search?keyword=test`
- **Expected:** Danh sách posts & users phù hợp

---

### Phase 3: Test Friendships (Port 8082)

#### 3.1 - Send Friend Request
- **Endpoint:** `POST http://localhost:8082/api/friends/request`
- **Body:**
```json
{
  "senderId": 1,
  "receiverId": 2
}
```
- **Expected:** Friendship request được tạo

#### 3.2 - Accept Request
- **Endpoint:** `POST http://localhost:8082/api/friends/1/accept`
- **Expected:** Friendship status = ACCEPTED

#### 3.3 - Get Friends List
- **Endpoint:** `GET http://localhost:8082/api/friends/1`
- **Expected:** Danh sách bạn bè

#### 3.4 - Get Pending Requests
- **Endpoint:** `GET http://localhost:8082/api/friends/1/requests`
- **Expected:** Danh sách lời mời chờ

---

### Phase 4: Test Booking Court (Port 8083)

#### 4.1 - Create Cluster
- **Endpoint:** `POST http://localhost:8083/api/admin/clusters`
- **Body:**
```json
{
  "name": "Test Cluster",
  "address": "123 Main Street",
  "city": "Test City"
}
```
- **Expected:** Cluster được tạo

#### 4.2 - Create Court
- **Endpoint:** `POST http://localhost:8083/api/admin/courts`
- **Body:**
```json
{
  "name": "Court A",
  "clusterId": 1,
  "capacity": 8,
  "available": true
}
```
- **Expected:** Court được tạo

#### 4.3 - Get All Courts
- **Endpoint:** `GET http://localhost:8083/api/admin/courts`
- **Expected:** Danh sách courts

#### 4.4 - Get Courts by Cluster
- **Endpoint:** `GET http://localhost:8083/api/admin/courts/cluster/1`
- **Expected:** Courts của cluster 1

---

### Phase 5: Test Chat App (Port 8085)

#### 5.1 - Get Chat History
- **Endpoint:** `GET http://localhost:8085/api/chat/rooms/1/messages`
- **Expected:** Danh sách messages trong room

#### 5.2 - WebSocket Connection
- **Endpoint:** `ws://localhost:8085/ws-chat`
- **Loại:** WebSocket
- **Cách test:** Dùng Postman WebSocket feature hoặc client khác

---

## 🔄 Chi Tiết Services

| Service | Port | Database | JWT | Status |
|---------|------|----------|-----|--------|
| Auth Management | 8081 | MySQL | ✅ Setup | Ready |
| Main API | 8080 | MySQL | ❌ Chưa integrate | Partial |
| Friendships | 8082 | MySQL | ❌ Chưa check | Ready |
| Booking Court | 8083 | MySQL | ❌ Chưa check | Ready |
| Chat App | 8085 | MongoDB | ❌ Chưa check | Ready |

---

## 🎯 Quy Trình Kiểm Tra Toàn Bộ

### 1️⃣ Kiểm Tra Database Connection
```bash
# Từ MySQL client hoặc DBeaver:
SELECT * FROM user;
SELECT * FROM post;
SELECT * FROM friendship;
SELECT * FROM court;
SELECT * FROM cluster;
```

### 2️⃣ Kiểm Tra Service Health
```bash
# Từ terminal:
curl http://localhost:8080/api/posts
curl http://localhost:8081/api/admin/users
curl http://localhost:8082/api/friends/1
curl http://localhost:8083/api/admin/clusters
```

### 3️⃣ Test Workflow Hoàn Chỉnh

```
1. Auth Service (8081):
   ├─ Register: POST /api/auth/register
   ├─ Login: POST /api/auth/login ✓ Lấy token
   └─ Save token trong Environment

2. Main API (8080):
   ├─ Create Post: POST /api/posts
   ├─ Get Posts: GET /api/posts
   ├─ Add Reaction: POST /api/posts/1/react
   ├─ Add Comment: POST /api/posts/1/comments
   └─ Search: GET /api/search?keyword=test

3. Friendships (8082):
   ├─ Send Request: POST /api/friends/request
   ├─ Accept: POST /api/friends/1/accept
   └─ Get Friends: GET /api/friends/1

4. Booking Court (8083):
   ├─ Create Cluster: POST /api/admin/clusters
   ├─ Create Court: POST /api/admin/courts
   └─ Get Courts: GET /api/admin/courts

5. Chat App (8085):
   ├─ Get History: GET /api/chat/rooms/1/messages
   └─ WebSocket: ws://localhost:8085/ws-chat
```

---

## 📝 Ghi Chú Quan Trọng

### ⚠️ Vấn Đề Hiện Tại

1. **Main API - Auth Integration**
   - File: `src/main/java/com/java/pickbooking/controller/AuthController.java`
   - Status: Comment "// đã chuyển sang auth-managerment ở 8081" nhưng chưa implement
   - Cần: Thêm HTTP client để gọi đến Auth Service (8081)

2. **JWT Token Validation**
   - Auth Service (8081) có setup đúng
   - Các services khác chưa validate token
   - Cần: Thêm JWT filter/interceptor vào các services

3. **Service-to-Service Communication**
   - Hiện tại: Các services kết nối qua shared database
   - Nên thay đổi: Dùng HTTP API calls (RestTemplate, WebClient, FeignClient)

### ✅ Điều Hoạt Động

- ✓ Database connections (MySQL)
- ✓ CORS configuration
- ✓ JWT generation (Auth Service)
- ✓ API endpoints mapping
- ✓ MongoDB connection (Chat App)

### ❌ Điều Cần Fix

- ❌ Main API ↔ Auth Service integration
- ❌ JWT validation across services
- ❌ Service-to-service HTTP calls
- ❌ Error handling standardization

---

## 🔧 Environment Variables

| Variable | Value | Notes |
|----------|-------|-------|
| auth_service_url | http://localhost:8081 | Auth Management |
| main_api_url | http://localhost:8080 | Main API |
| friendships_url | http://localhost:8082 | Friendships |
| booking_court_url | http://localhost:8083 | Booking Court |
| chat_app_url | http://localhost:8085 | Chat App |
| auth_token | [từ login] | Update sau login |
| test_email | testuser@example.com | Cho testing |
| db_host | localhost | MySQL host |
| db_port | 3306 | MySQL port |
| db_name | pickbooking | Database name |

---

## 💡 Tips & Tricks

### Tự động Update Token
1. Thêm Test Script vào request Login:
```javascript
pm.environment.set("auth_token", pm.response.json().data.token);
```

### Check Status Tất Cả Services
1. Tạo request mới: `GET` requests sau
2. Chạy Postman Runner để batch test

### Export Test Results
1. Postman Runner → Run Collection
2. Export results to HTML/JSON

---

## 📞 Support

- Nếu service không respond: Kiểm tra console log
- Nếu database error: Verify MySQL running
- Nếu JWT error: Verify token format "Bearer xxx"

---

**Tạo ngày:** 2025-01-11
**Collection:** Pickbooking Backend - Connection Test
**Version:** 1.0
