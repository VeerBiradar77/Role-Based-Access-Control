# Role-Based Access Control (RBAC) — Spring Boot, JWT, MySQL
It demonstrates:
- User login with JWT token.
- Dynamic role-permission checks.
- Support for hierarchical roles(inheritance).
- Flexible API path matching for fine-grained access.
- Secure password storage.

##  **Key Features**

 **JWT Token-Based Authentication**
- Users log in with a username & password.
- On success, a JWTis issued (valid for 60 minutes).

**RBAC Interceptor**
- Each request must send:
- in the HTTP headers.  
- The interceptor checks:
  The token is valid and not expired.  
  The user exists and is active.  
  The user’s roles grant permission for the requested API path and HTTP method.
- If any check fails:
- Missing/invalid token → `401 Unauthorized`
- No permission → `403 Forbidden`

  **Role Hierarchy**  
- Example:
- Admin: Full access to all APIs.
- Doctor: Read/write own, read only others where allowed.
- Blood Donor: Read/write own, read doctor.
- User: Read/write own only.
- Supports inheritance, so higher roles automatically get lower-level permissions.

**Optimized Checks**  
- Uses `is_active` flags.
- Checks permissions recursively or with DB joins.

**Tech Stack**
- Java 17
- Spring Boot
- Spring Security
- JPA / Hibernate
- MySQL
- JWT 
- BCrypt

  **Example SQL Inserts**
- please refer in resoureces/DB/rbac.sql
