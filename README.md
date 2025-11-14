# SpringBoot Security 🔐

> A clean, modern starter project demonstrating **Spring Security** with **JWT** authentication, signup/login flows, and best-practice token handling.

---



## 🚀 Features

- User **Sign Up** and **Login** endpoints.
- JWT-based authentication and authorization.
- Password hashing with **BCrypt**.
- Role-based access (e.g. `ROLE_USER`, `ROLE_ADMIN`).
- Refresh token pattern (optional) for longer sessions.
- Clean, modular package structure for easy extension.

## 🧭 Tech stack

- Java 17+ (or appropriate JDK)
- Spring Boot (2.7+ / 3.x)
- Spring Security
- jjwt / jose / Nimbus (your JWT library)
- Maven or Gradle
- H2 (dev) / PostgreSQL / MySQL (production)

---

## 🔧 Quick Start — Run locally

> These steps assume you have Java and Maven (or Gradle) installed.

```bash
# 1. Clone the repo
https://github.com/Harshvardhan210/SpringBootSecurity.git

# 2. Build
mvn clean package    # or: ./gradlew build

# 3. Run
mvn spring-boot:run  # or: java -jar target/*.jar
```

The API will start at `http://localhost:8080` by default.

---

## ⚙️ Configuration

Set environment variables or fill `application.yml` / `application.properties`:

```yaml
spring:
  datasource:
    url: 
    username: 
    password:

jwt:
  secret: "REPLACE_THIS_WITH_A_STRONG_SECRET"
  expirationMs: 3600000        # 1 hour
  refreshExpirationMs: 86400000 # 1 day (if implementing refresh tokens)

app:
  default-role: ROLE_USER
```

> **Security tip:** never commit your `jwt.secret` or production DB credentials to source control. Use a secrets manager or environment variables.

---

## 🔁 Authentication Flow (overview)

1. **Sign Up** (`POST /api/auth/signup`) — create user (password hashed).
2. **Login** (`POST /api/auth/login`) — submit credentials → get JWT (and optional refresh token).
3. **Use JWT** — pass `Authorization: Bearer <token>` header to access protected endpoints.
4. **Token Renewal** — use refresh token endpoint (if implemented) to obtain a new JWT.

---

## 📚 API Endpoints (example)

### Auth

```http
POST /api/auth/signup
Content-Type: application/json

{
  "username": "harsh",
  "email": "harsh@example.com",
  "password": "p@ssw0rd"
}
```

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "harsh",
  "password": "p@ssw0rd"
}

# Response
{
  "accessToken": "eyJhbGciOiJI...",
  "tokenType": "Bearer",
  "expiresIn": 3600,
  "refreshToken": "..." (optional)
}
```

### Protected route example

```http
GET /api/user/me
Authorization: Bearer <accessToken>
```

---

## 🧩 Project structure

Your actual project structure looks like this:

```
src/
└─ main/
   ├─ java/com/example/securityTesting/
   │  ├─ config/
   │  │  ├─ JwtFilter.java
   │  │  └─ SecurityConfig.java
   │  ├─ controller/
   │  │  ├─ StudentController.java
   │  │  ├─ textController.java
   │  │  └─ UserController.java
   │  ├─ model/        # Entities (User, Roles, etc.)
   │  ├─ repo/         # Repositories
   │  ├─ service/
   │  │  ├─ JWTService.java
   │  │  ├─ MyUserDetailService.java
   │  │  └─ UserService.java
   │  └─ SecurityTestingApplication.java
   └─ resources/
      ├─ static/
      └─ templates/
```


---

## ✅ Best practices & notes

- Use **HTTPS** in production to protect tokens in transit.
- Keep JWT expiration short (e.g., 15m–60m) and use refresh tokens for longer sessions.
- Store refresh tokens securely (HTTP-only cookies or server-side store) — **do not** store refresh tokens in localStorage for sensitive apps.
- Revoke refresh tokens on logout or when detecting a breach.
- Rotate `jwt.secret` periodically and have a strategy for invalidating old tokens.

---

## 🧪 Testing

- Add unit tests for `JwtService` and auth flows.
- Use Postman/Insomnia to manually test signup, login, and protected endpoints.
- Integration tests can run with an in-memory H2 DB.

---

## 🖌️ UI (optional)

If you add a frontend, a minimal flow:

1. Signup page → POST `/api/auth/signup`
2. Login page → POST `/api/auth/login` and store access token temporarily
3. Use token to call protected APIs

> Example: React + Axios — set default header `Authorization: Bearer ${token}`

---

## ➕ Optional improvements

- Social login (OAuth2) — Google, GitHub.
- Two-Factor Authentication (2FA) with TOTP.
- Account verification (email link).
- Admin dashboard and role management.

---

## 🤝 Contributing

Contributions are welcome! Please open an issue or a pull request with a clear description of changes.

1. Fork the repo
2. Create a feature branch `feature/awesome`
3. Commit changes and make a PR

---

## 📝 License

This project is MIT licensed — see `LICENSE` for details.

---

