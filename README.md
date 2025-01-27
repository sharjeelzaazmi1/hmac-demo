# HMAC Authentication in Spring Boot

This project demonstrates how to implement **HMAC (Hash-based Message Authentication Code)** authentication for API requests in a Spring Boot application. The frontend calculates the HMAC for each request and the backend verifies it, ensuring the authenticity and integrity of sensitive API endpoints.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [Testing the API](#testing-the-api)
- [Security Considerations](#security-considerations)
- [License](#license)

## Features

- **HMAC Authentication**: Secure HMAC generation and verification for API calls.
- **Spring Boot Setup**: Easy-to-setup Spring Boot application with minimal dependencies.
- **Secure API Endpoints**: Protect sensitive API endpoints using HMAC.
- **Base64-encoded HMAC**: HMACs are Base64-encoded for easier transmission.

## Tech Stack

- **Spring Boot** (3.x)
- **Java** (17+)
- **HMAC-SHA256** for hashing
- **Base64** for encoding HMACs
- **Maven** for project management and build

## Getting Started

### Prerequisites

Before you can run this project locally, make sure you have the following installed:

- **Java 17 or higher**: Ensure you have Java 17+ installed on your machine.
- **Maven**: To build and run the project.

You can download Java 17+ from [here](https://adoptopenjdk.net/) and Maven from [here](https://maven.apache.org/download.cgi).

### Clone the Repository

Clone the repository to your local machine using the following command:

```bash
git clone https://github.com/yourusername/hmac-spring-boot-demo.git
cd hmac-spring-boot-demo
```

### Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

### Run the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

By default, the Spring Boot application will be running on `http://localhost:8080`.

## Project Structure

```
src
 ├── main
 │    ├── java
 │    │    ├── com
 │    │    │    ├── example
 │    │    │    │    ├── hmacdemo
 │    │    │    │    │    ├── HMACController.java       # API endpoint to generate HMAC
 │    │    │    │    │    ├── HMACUtil.java              # Utility class for HMAC generation
 ├── resources
 │    ├── application.properties                         # Spring Boot configuration file
```

## Endpoints

### 1. **POST /hmac/generate**

This endpoint generates the HMAC for a given request body using the HMAC-SHA256 algorithm and a shared secret key.

#### Request:

- **URL**: `http://localhost:8080/hmac/generate`
- **Method**: `POST`
- **Body** (raw text or JSON):
  - Example Body:
    ```json
    "Data to be authenticated"
    ```

#### Response:

The response will return the **Base64 encoded HMAC**:

```json
"XyZ6pRslWf5yR6ld8k0Nm68JwRPkmJlMjQemxqIEHXg="
```

If the HMAC generation fails, an error message will be returned:

```json
{
  "error": "Error generating HMAC: <error_message>"
}
```

## Testing the API

### Using Postman:

1. **Create a New Request** in Postman.
2. **Method**: `POST`
3. **URL**: `http://localhost:8080/hmac/generate`
4. **Body**: Select **raw** and **Text** in Postman, and input the text data you want to authenticate (e.g., `"Data to be authenticated"`).
5. **Send the Request** and check the response. The HMAC will be returned as a **Base64 encoded string**.

### Using cURL:

You can also use `cURL` to test the API:

```bash
curl -X POST "http://localhost:8080/hmac/generate" \
  -H "Content-Type: application/json" \
  -d "\"Data to be authenticated\""
```

This will return the **Base64-encoded HMAC** in the response.

### Sample Response:

```json
"XyZ6pRslWf5yR6ld8k0Nm68JwRPkmJlMjQemxqIEHXg="
```

## Security Considerations

- **Secret Key**: The secret key used to generate the HMAC must be **securely stored**. Never expose the secret key in the client-side code (e.g., JavaScript or mobile apps). Use secure vaults or environment variables to manage secrets.
  
- **Data Integrity**: Since HMACs are calculated based on the request data, any changes in the data will result in a different HMAC. This ensures that the request has not been tampered with during transmission.

- **Token Expiry and Rotation**: Rotate secret keys periodically to minimize the impact of potential leaks or security vulnerabilities.

- **Use HMAC for Sensitive Operations Only**: HMAC should be used for sensitive endpoints (e.g., `POST`, `PUT`, `DELETE` operations). For non-sensitive operations like `GET` requests (e.g., fetching public data), HMAC is not necessary.

- **Rate Limiting**: Consider applying **rate-limiting** to your APIs to prevent abuse, especially for endpoints that require HMAC generation.


---

### Notes:

- This project assumes that both the frontend and backend share a **secret key** for generating and validating the HMAC.
- HMAC is only used for requests that modify data (e.g., creating, updating, or deleting resources). Use other forms of authentication, such as **API Keys** or **JWT** tokens, for less sensitive data or public endpoints.
- **Front-end implementation**: For real-world use, the front-end would calculate the HMAC using the same secret key and pass it along in the request header, which the backend will verify.

Let me know if you need any more details or modifications!
