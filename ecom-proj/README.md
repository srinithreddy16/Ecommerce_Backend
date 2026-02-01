# Ecom-Proj

A REST API backend for product management built with **Spring Boot**. It provides full CRUD operations for products, image upload support, and keyword search—suitable for e-commerce backends or product catalogs.

---

## Features

- **Product CRUD** — Create, read, update, and delete products
- **Image upload** — Store product images (multipart/form-data) with optional file on create/update
- **Product search** — Search by name, description, brand, or category (case-insensitive)
- **Image retrieval** — Serve product images via dedicated endpoint with correct content type
- **Validation** — Required fields (name, price) validated at service layer
- **H2 Console** — In-browser database UI for development and debugging
- **CORS enabled** — Ready for frontend apps on other origins

---

## Tech Stack

| Component        | Technology              |
|-----------------|-------------------------|
| Runtime         | Java 21                 |
| Framework       | Spring Boot 4.0.2       |
| Web             | Spring Web MVC          |
| Data            | Spring Data JPA         |
| Database        | H2 (in-memory)          |
| Utilities       | Lombok                  |
| Dev             | Spring DevTools         |

---

## Prerequisites

- **Java 21** or later  
- **Maven 3.6+** (or use the included Maven Wrapper)

---

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd ecom-proj
```

### 2. Run the application

**Using Maven Wrapper (recommended):**

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

**Using installed Maven:**

```bash
mvn spring-boot:run
```

The API is available at **http://localhost:8080**.

### 3. (Optional) Run tests

```bash
.\mvnw.cmd test
```

---

## API Reference

Base path: **`/api`**

| Method   | Endpoint                      | Description                    |
|----------|--------------------------------|--------------------------------|
| `GET`    | `/products`                    | List all products              |
| `GET`    | `/product/{id}`                | Get product by ID              |
| `POST`   | `/product`                     | Create product (see below)     |
| `PUT`    | `/product/{id}`                | Update product (see below)     |
| `DELETE` | `/product/{id}`                | Delete product                 |
| `GET`    | `/product/search?keyword=...`  | Search products by keyword     |
| `GET`    | `/product/{productId}/image`   | Get product image (binary)     |

### Create product — `POST /api/product`

- **Content-Type:** `multipart/form-data`
- **Parts:**
  - `product` (JSON) — Product payload
  - `imageFile` (file, optional) — Product image

**Product JSON fields:** `name`, `description`, `brand`, `price`, `category`, `releaseDate` (yyyy-MM-dd), `productAvailable`, `stockQuantity`.  
`name` and `price` are required.

### Update product — `PUT /api/product/{id}`

- Same as create: `multipart/form-data` with `product` (JSON) and optional `imageFile`.  
- If `imageFile` is omitted, existing image is kept.

### H2 Console

- **URL:** http://localhost:8080/h2-console  
- **JDBC URL:** `jdbc:h2:mem:telusko`  
- **Username:** `sa`  
- **Password:** *(leave empty)*  

---

## Project Structure

```
src/main/java/com/srinith/ecom_proj/
├── EcomProjApplication.java      # Application entry point
├── controller/
│   └── ProductController.java    # REST endpoints
├── model/
│   └── Product.java              # JPA entity
├── repo/
│   └── ProductRepo.java          # JPA repository + search query
└── service/
    └── ProductService.java       # Business logic & validation
```

---

## Configuration

Key settings in `application.properties`:

| Property                              | Default / value   | Description                    |
|---------------------------------------|-------------------|--------------------------------|
| `server.port`                          | 8080              | HTTP port                      |
| `spring.datasource.url`                | jdbc:h2:mem:telusko | H2 in-memory DB               |
| `spring.jpa.hibernate.ddl-auto`        | update            | Schema update strategy         |
| `spring.servlet.multipart.max-file-size` | 10MB           | Max upload size per file       |
| `spring.servlet.multipart.max-request-size` | 10MB        | Max request size               |

Override via environment variables or `application.properties` / `application.yml` as needed.

---

## License

This project is provided as-is for demonstration and development use.
