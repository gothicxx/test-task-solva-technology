# 🌐 Solva Technology Bank System

A microservice prototype for handling bank transactions, currency exchange rates, and expense limits.
This project was developed as part of a backend test assignment for a Junior Java Developer position.

## ✨ Project Goal

Develop a Spring Boot microservice that integrates into an existing banking system. The microservice must:

### Functional Requirements (based on the assignment):

1. **Receive and store spending transactions** in KZT, RUB, and other currencies in real time (Note: **implemented using UAH instead of RUB**).
2. **Store monthly spending limits** in USD for two expense categories: `PRODUCT` and `SERVICE`.
3. **Fetch exchange rates** for currency pairs (e.g., UAH/USD, UAH/EUR, etc.) using an external API (NBU used instead of paid providers). Rates are fetched once per day and stored in the DB. If rates are not available for the current day, the most recent rate (previous close) is used.
4. **Mark transactions that exceed the monthly limit** with a flag `limit_exceeded`.
5. **Allow clients to set a new limit** (only for the current day; updating previous limits is not allowed).
6. **Return a list of transactions that exceeded limits**, along with:

    * limit amount,
    * limit date,
    * currency (USD).

## ⚙ Technologies Used

* Java 17
* Spring Boot 3.2.4
* Spring Data JPA
* WebClient (Spring WebFlux)
* MapStruct
* Flyway
* H2 (in-memory DB)
* Swagger UI (`springdoc-openapi`)
* Lombok

## 📋 Differences from the original assignment

* **UAH** (Ukrainian Hryvnia) is used instead of RUB.
* External exchange rate provider: **NBU (National Bank of Ukraine)** API.

## ⚡ Run the project

### Build the project

```bash
mvn clean install
```

### Run locally

```bash
mvn spring-boot:run
```

### Run with Docker

```bash
docker build -t solvatechnology-app .
docker run -p 8080:8080 solvatechnology-app
```

## 🔍 Swagger API Docs

Once the app is running, open:

```
http://localhost:8080/swagger-ui/index.html
```

## 📃 Endpoints

### Transactions

* `POST /api/v1/transactions/save` — save and process transaction
* `GET /api/v1/transactions/exceeded` — get list of transactions that exceeded limits

### Currency

* `GET /api/v1/currencies/all-today` — get today’s currency rates
* `GET /api/v1/currencies/all-by-date?date=yyyy-MM-dd` — get rates by date
* `GET /api/v1/currencies/one-by-date?date=yyyy-MM-dd&code=USD` — get specific currency rate by date

### Limits

* `POST /api/v1/limits/set` — set a new spending limit for a category
* `GET /api/v1/limits/{category}` — get the current limit for category (`PRODUCT` or `SERVICE`)

## 📄 Assignment

See full test assignment here:
[BackEnd\_Test\_Assignment.pdf](./BackEnd_Test_Assignment.pdf)

## ✏️ TODO

* [ ] Add integration tests
* [ ] Migrate to MySQL (if needed for production)
* [ ] Exception handling improvements
* [ ] Add Docker Compose file with MySQL
* [ ] Use FeignClient for exchange rate fetching (optional)

---

> Author: [gothicxx](https://github.com/gothicxx)
>
> Feedback is welcome!