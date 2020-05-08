# Spring Security Module With Session Management

### Prerequisites
* java 1.8.x
* maven 3.x

### Steps To Setup

**1. Clone the repository**
```bash
    git clone https://github.com/AbhayKatharotiya/SecurityModule.git
```

**2. Build project**
```bash
    mvn clean install
``` 

**3. Run project** 
```bash
    java -jar target/securitymodule-0.0.1-SNAPSHOT.jar
``` 
- Alternatively, you can run the app without packaging it using -
```bash
    mvn spring-boot:run
```

## Explore apis 

The app defines following APIs. 
 
```   
    POST /signup   
    POST /login  
    GET /logout  
    GET /  
```

#### /signup API
* Use following body in signup api
```json
    {
      "name" : "Your name",
      "email" : "Your valid email",
      "contactNo" : "Valid contact number",
      "address" : "Your address",
      "password" : "Your password"
    }
```
* In this body email and contact no should be valid 
* Email will be unique field in database
* API does not need authentication

#### /login API
* Use following body in login api
```json
    {
      "username" : "Your email",
      "password" : "Your password"
    }
```
* Here username will be your registered email.
* API does not need authentication

#### /logout API
* This api will destroy your session

#### / API
* This api will return ` 200 OK ` if authenticated
