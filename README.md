# Authenticator API Overview

The **Authenticator API** provides essential functionality for managing users and their interactions within the authentication system. The API includes endpoints for user sign-up, login, retrieving user details, and checking the health of the application. It is designed to facilitate easy interaction with the application using basic HTTP methods like `GET` and `POST`.

Additionally, **Redis** is used for caching frequently accessed user data, improving performance and reducing database load.

## API Endpoints Overview

### 1. **App Health**
- **Method**: `GET`
- **URL**: `/health`
- **Description**: Verifies if the application is up and running.
- **Response**: Returns a simple message confirming the application's health.

### 2. **Sign Up User**
- **Method**: `POST`
- **URL**: `/signup`
- **Description**: Allows new users to register by providing essential user details (username, email, phone, password, etc.).
- **Request Body**: Contains the user's personal information.
- **Response**: Confirms whether the user was successfully signed up or if validation errors occurred.

### 3. **User Login**
- **Method**: `POST`
- **URL**: `/login`
- **Description**: Authenticates users by validating their username and password.
- **Request Body**: Contains the username and password.
- **Response**: Confirms successful login or returns an error if credentials are invalid.

### 4. **Get All Users**
- **Method**: `GET`
- **URL**: `/all-users`
- **Description**: Retrieves a list of all registered users.
- **Response**: Returns a list of all users in the system.

### 5. **Get User by ID**
- **Method**: `GET`
- **URL**: `/users/{userId}`
- **Description**: Retrieves the details of a specific user by their ID or username.
- **Response**: Returns user details or an error if the user is not found.

## Redis Caching

To optimize the performance of the **Authenticator API**, **Redis** is used for caching:

- **Redis** helps to store and quickly retrieve user data, reducing the load on the database.
- Frequently accessed data, such as user details (usernames, emails, phone numbers), are cached in Redis.
- This caching mechanism significantly improves the response time for repeated requests and helps scale the application.

## Postman Collection

This Postman collection provides pre-configured requests for interacting with the API:

- **Health Check**: Check if the application is running.
- **Sign Up**: Register a new user.
- **Login**: Authenticate a user.
- **Get All Users**: Retrieve all registered users.
- **Get User by ID**: Retrieve user details by their ID or username.

---

## How to Use the Collection

1. Import the **Authenticator API** Postman collection into Postman.
2. Run the server locally and ensure it is accessible via `http://localhost:8081`.
3. Use the provided collection to send requests to the API and interact with the **Authenticator** application.
4. You can use the **GET** requests to check the health of the app or fetch user details, and **POST** requests to sign up or log in users.

---

## Conclusion

The **Authenticator API** provides essential user management functionality such as sign-up, login, and retrieving user data. It utilizes **Redis caching** to optimize performance and reduce database load, ensuring faster response times for users. The API can be further expanded to include additional features for managing authentication-related tasks.
