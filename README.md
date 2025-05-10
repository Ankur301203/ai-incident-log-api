# AI Incident Log API

This is a simple REST API for logging AI-related incidents.  
Built using **Java** with the **Spring Boot** framework.

---

## How to Build and Run Locally

1. **Clone the Repository**

```bash
git clone https://github.com/Ankur301203/ai-incident-log-api.git
cd ai-incident-log-api
```

2. **Install Dependencies and Build**

Make sure you have **Java 17+** and **Maven** installed.

```bash
./mvnw clean install
```

3. **Run the Application**

```bash
./mvnw spring-boot:run
```

The API will be available at:  
`http://localhost:8080`

---

## 🛠 Language and Framework Choice

- **Language:** Java
- **Framework:** Spring Boot 3
- **Build Tool:** Maven
- **Database:** MySQL (can easily be switched to PostgreSQL or H2)

---

## 🗄 Database Setup Instructions

1. **Create the Database**

Login to your MySQL server and run:

```sql
CREATE DATABASE log_db;
```

2. **Configure Database Connection**

In the file `src/main/resources/application.properties`, set your database details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/log_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
3. **(Optional) Preload Sample Data**

You can add a data.sql file inside src/main/resources/ to insert initial records automatically when the app starts.

Example data.sql content:
```properties
INSERT INTO incidents (title, description, severity) VALUES ('Sample Incident 1', 'This is a test incident.', 'Low');
INSERT INTO incidents (title, description, severity) VALUES ('Sample Incident 2', 'Another test case.', 'Medium');
```
This way, the app will start with some dummy data already available.

**OR**, use environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/log_db
export SPRING_DATASOURCE_USERNAME=your_mysql_username
export SPRING_DATASOURCE_PASSWORD=your_mysql_password
```

---

## API Endpoints

### 1. Create a New Incident
- **URL:** `POST /api/incidents`
- **Request Body (JSON):**

```json
{
  "title": "Model Failure",
  "description": "The model made a critical error during production.",
  "severity": "High"
}
```

- **Example curl:**

```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{"title":"Model Failure","description":"The model made a critical error during production.","severity":"High"}'
```

---

### 2. Get All Incidents
- **URL:** `GET /api/incidents`

- **Example curl:**

```bash
curl http://localhost:8080/api/incidents
```

---

### 3. Get Incident by ID
- **URL:** `GET /api/incidents/{id}`

- **Example curl:**

```bash
curl http://localhost:8080/api/incidents/1
```

---

### 4. Update an Incident
- **URL:** `PUT /api/incidents/{id}`
- **Request Body (JSON):**

```json
{
  "title": "Updated Title",
  "description": "Updated description details.",
  "severity": "Critical"
}
```

- **Example curl:**

```bash
curl -X PUT http://localhost:8080/api/incidents/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated Title","description":"Updated description details.","severity":"Critical"}'
```

---

### 5. Delete an Incident
- **URL:** `DELETE /api/incidents/{id}`

- **Example curl:**

```bash
curl -X DELETE http://localhost:8080/api/incidents/1
```

---

##  Database Table Schema

Optional manual creation (Hibernate can auto-create):

```sql
CREATE TABLE incidents (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  severity VARCHAR(50),
  reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## Design Decisions and Challenges

- Used **Spring Boot** for faster REST API development.
- Used **JPA (Hibernate)** for clean database interaction.
- `reported_at` field is auto-managed by the database.
- Minimal error handling for simplicity; could be enhanced later.
- Future improvements: add filtering, pagination, and better validations.

---

#  Ready for Deployment!

