# Pickbooking Backend - Features Documentation

## Project Overview

Pickbooking is a comprehensive microservices-based social networking and badminton court booking platform with integrated real-time messaging capabilities. The system is designed using a modular architecture where each service operates independently on dedicated ports, enabling scalability and maintainability.

## Architecture

The application follows a **microservices architecture** with the following modules:

| Module | Port | Database | Description |
|--------|------|----------|-------------|
| Main API | 8080 | MySQL | Social posts and user interactions |
| Auth Management | 8081 | MySQL | Authentication and authorization |
| Friendships | 8082 | MySQL | Friend connections management |
| Booking Court | 8083 | MySQL | Court and cluster management |
| Chat App | 8085 | MongoDB | Real-time messaging system |
| Post Management | TBD | - | Dedicated post management (planned) |

## Technology Stack

- **Framework**: Spring Boot 3.5.4
- **Language**: Java 17
- **Build Tool**: Maven
- **Databases**: MySQL (relational), MongoDB (chat storage)
- **Security**: Spring Security with BCrypt, JWT (HS512)
- **Real-time**: Spring WebSocket
- **ORM**: Spring Data JPA
- **Token Management**: Nimbus JOSE/JWT
- **Code Generation**: Lombok

---

## Module Features

### 1. Authentication & Authorization Module (Port 8081)

**Purpose**: Centralized user authentication and JWT token management

#### Features

##### User Registration
- New user account creation with validation
- Password encryption using BCrypt (strength 10)
- Email and phone number support
- Role assignment (USER/ADMIN)

##### User Authentication
- Username/password-based login
- JWT token generation with HS512 algorithm
- Token expiration: 1 hour
- Issuer: pickbooking.vn

##### Token Validation (Introspect)
- JWT token verification
- Expiration checking
- Token integrity validation

##### User Management (Admin)
- Retrieve all registered users
- Get user details by ID
- User profile information access

#### API Endpoints

```
POST   /api/auth/register         - Register new user
POST   /api/auth/login            - Authenticate and get JWT token
POST   /api/auth/introspect       - Validate JWT token
GET    /api/admin/users           - Get all users (admin)
GET    /api/admin/users/{id}      - Get user by ID
```

#### Data Models

**User Entity**
- userId (PK)
- username (unique)
- password (encrypted)
- email
- fullName
- phone
- role (USER/ADMIN)

---

### 2. Social Posts & Interactions Module (Port 8080)

**Purpose**: Core social networking features including posts, reactions, comments, and search

#### Features

##### Post Management
- Create posts with text content and optional images
- Retrieve all posts (sorted by creation date, descending)
- Get posts by user ID
- Delete posts by ID
- Image attachment support (max 10MB per file)

##### Emoji Reaction System
- Six emoji types supported: 👍, ❤️, 😂, 😮, 😢, 😡
- One reaction per user per post
- Toggle functionality: clicking same emoji removes the reaction
- Update reaction by selecting different emoji
- Real-time reaction count aggregation

##### Comments System
- Add comments to posts
- Retrieve all comments for a post
- Chronological ordering by creation time
- User attribution for each comment

##### Full-Text Search
- Search across users (username, fullName)
- Search across posts (content)
- Case-insensitive matching
- Combined results in single response

##### File Upload & Management
- Image upload via multipart/form-data
- Local filesystem storage in `/uploads` directory
- File serving via HTTP endpoint
- Request size limits: 10MB per file, 20MB total

#### API Endpoints

```
POST   /api/posts                      - Create new post
GET    /api/posts                      - Get all posts
GET    /api/posts/{id}                 - Get post by ID
GET    /api/posts/userId/{user_id}     - Get posts by user
DELETE /api/posts/{id}                 - Delete post

POST   /api/posts/{postId}/react       - Add/update/remove reaction
GET    /api/posts/{postId}/reactions   - Get reaction counts
POST   /api/posts/{postId}/comments    - Add comment
GET    /api/posts/{postId}/comments    - Get all comments

GET    /api/search?keyword={keyword}   - Search users and posts

POST   /api/uploads                    - Upload image file
GET    /api/uploads/{filename}         - Download/view image
```

#### Data Models

**Post Entity**
- postId (PK)
- user (FK)
- content (TEXT)
- imageUrl
- createdAt
- updatedAt

**PostReaction Entity**
- reactionId (PK)
- user (FK)
- post (FK)
- type (emoji string)
- createdAt

**Comment Entity**
- commentId (PK)
- user (FK)
- post (FK)
- content
- createdAt

---

### 3. Friendship Management Module (Port 8082)

**Purpose**: Social connection management with friend requests and relationships

#### Features

##### Friend Request System
- Send friend requests between users
- Prevent duplicate requests to same user
- Self-request prevention
- Request status tracking (PENDING/ACCEPTED/REJECTED)

##### Request Management
- Accept pending friend requests
- Reject/decline friend requests
- Cancel sent requests
- Remove existing friendships

##### Friend Lists
- View all accepted friends for a user
- View pending friend requests received
- Bidirectional relationship tracking

#### API Endpoints

```
POST   /api/friends/request                  - Send friend request
POST   /api/friends/{friendshipId}/accept    - Accept request
POST   /api/friends/{friendshipId}/reject    - Reject request
DELETE /api/friends/{friendshipId}/remove    - Remove friendship
GET    /api/friends/{userId}                 - Get friends list
GET    /api/friends/{userId}/requests        - Get pending requests
```

#### Data Models

**Friendship Entity**
- id (PK)
- sender (FK to User)
- receiver (FK to User)
- status (PENDING/ACCEPTED/REJECTED)
- createdAt
- updatedAt
- Unique constraint: (sender_id, receiver_id)

---

### 4. Court Booking Management Module (Port 8083)

**Purpose**: Badminton court and facility management for booking services

#### Features

##### Cluster Management
- Create court clusters (facilities/locations)
- List all available clusters
- Delete clusters
- Store cluster details (name, address, description)

##### Court Management
- Add courts to clusters
- Track court availability status
- Update court availability (available/unavailable)
- Assign court numbers and locations
- Set court capacity

##### Hierarchical Organization
- One-to-many relationship: Cluster → Courts
- Filter courts by cluster
- Independent court status management

#### API Endpoints

```
GET    /api/admin/clusters                      - Get all clusters
POST   /api/admin/clusters                      - Create cluster
DELETE /api/admin/clusters/{id}                 - Delete cluster

GET    /api/admin/courts                        - Get all courts
POST   /api/admin/courts                        - Create court
DELETE /api/admin/courts/{id}                   - Delete court
GET    /api/admin/courts/cluster/{clusterId}    - Get courts by cluster
PUT    /api/admin/courts/{courtId}              - Update court status
```

#### Data Models

**Cluster Entity**
- clusterId (PK)
- name
- address
- description
- courts (One-to-Many relationship)

**Court Entity**
- courtId (PK)
- cluster (FK)
- court_number
- available (boolean)
- location
- name

---

### 5. Real-Time Chat Module (Port 8085)

**Purpose**: WebSocket-based real-time messaging system

#### Features

##### Real-Time Messaging
- WebSocket connection for instant messaging
- Message broadcasting to all connected clients
- Connection state management
- Session tracking

##### Room-Based Chat
- Messages organized by chat rooms
- Room-specific message retrieval
- Multi-room support

##### Message Persistence
- MongoDB storage for chat history
- Timestamp tracking
- Sender attribution
- Chronological message ordering

##### Chat History
- Retrieve historical messages by room
- Full conversation history access

#### API Endpoints

```
WS     /ws-chat                              - WebSocket connection
GET    /api/chat/rooms/{roomId}/messages     - Get chat history
```

#### WebSocket Message Format

```json
{
  "type": "CHAT",
  "roomId": "string",
  "senderId": "string",
  "content": "string"
}
```

#### Data Models

**ChatMessage Entity** (MongoDB)
- id (ObjectId)
- roomId
- senderId
- content
- createdAt
- Collection: `chat_messages`

---

## Cross-Cutting Features

### Security

#### JWT Authentication
- Algorithm: HS512
- Token duration: 1 hour
- Issuer: pickbooking.vn
- Configurable signing key

#### Password Security
- BCrypt encryption
- Strength factor: 10
- Minimum password length: 8 characters

#### Authorization
- Role-based access control (USER/ADMIN)
- Admin-only endpoints for user management
- Token-based API access

### API Configuration

#### CORS Policy
- Enabled for all origins (`*`)
- Configured on all REST controllers
- Supports cross-origin requests

#### Request Validation
- Input validation on registration
- Password strength requirements
- File upload size limits
- Request body validation

### File Management

#### Upload Configuration
- Storage: Local filesystem (`/uploads` directory)
- Max file size: 10MB
- Max request size: 20MB
- Supported formats: Images (JPG, JPEG, PNG)

#### File Access
- HTTP-based file serving
- Direct filename access
- Public read access for uploaded files

### Database Configuration

#### MySQL
- Host: localhost:3306
- Database: pickbooking
- Auto-schema generation enabled (`ddl-auto=update`)
- Connection pooling

#### MongoDB
- Host: localhost:27017
- Database: pickbooking
- Used exclusively for chat messages
- Document-based storage

---

## Error Handling

### Custom Error Codes

The system implements comprehensive error handling with custom error codes:

- `USER_NOT_EXIST`: User not found in database
- `UNAUTHENTICATED`: Invalid credentials or token
- Additional domain-specific errors per module

### API Response Format

All API responses follow a consistent format:

```json
{
  "code": 1000,
  "message": "Success",
  "result": { /* response data */ }
}
```

---

## Implementation Status

| Feature | Status |
|---------|--------|
| User Authentication & JWT | ✅ Implemented |
| Post Creation & Management | ✅ Implemented |
| Emoji Reaction System | ✅ Implemented |
| Comments System | ✅ Implemented |
| Full-Text Search | ✅ Implemented |
| Friendship Management | ✅ Implemented |
| Court Booking System | ✅ Implemented |
| Real-Time Chat (WebSocket) | ✅ Implemented |
| File Upload | ✅ Implemented |
| Post Management Module | ⚠️ Skeleton Only |

---

## API Testing

Postman collection and environment files are provided for testing:
- `Pickbooking-API-Collection.postman_collection.json`
- `Pickbooking-Environment.postman_environment.json`

Refer to `POSTMAN_GUIDE.md` for detailed API testing instructions.

---

## Future Enhancements

### Planned Features
- Dedicated Post Management Module implementation
- Booking reservation system
- Payment integration
- Notification system
- User profile customization
- Advanced search filters
- Analytics dashboard

### Potential Improvements
- Rate limiting
- API documentation (Swagger/OpenAPI)
- Redis caching
- Message queuing (RabbitMQ/Kafka)
- CDN integration for file storage
- Multi-language support
- Mobile push notifications

---

## Contact & Support

For questions, issues, or contributions, please refer to the project repository and documentation.

---

**Last Updated**: February 2, 2026
**Version**: 1.0
**Maintained by**: Pickbooking Development Team
