Project Structure
I organized the project in a structured manner, which is as follows:

```
Rule-Engine-App/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── rule_engine_app/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── model/
│   │   │   │   │   ├── service/
│   │   │   │   │   ├── ast/
│   │   │   │   │   ├── repository/
│   │   │   │   │   ├── RuleEngineApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   ├── test/
│
└── pom.xml

```

# Rule-Engine-App
Rule Engine with AST enables dynamic rule creation and evaluation based on user attributes like age, department, and income. Built with Java and Spring Boot, it features
a RESTful API and uses MySQL for storage. The application handles complex logical expressions efficiently, providing flexibility for eligibility determinations across 
various applications.
Rule Engine with AST
Description
This Rule Engine application allows for dynamic rule creation and evaluation based on user attributes such as age, department, income, and spending. Utilizing an 
Abstract Syntax Tree (AST), the application offers a flexible approach to manage complex logical expressions efficiently.

Features
Dynamic Rule Management: Create and evaluate rules using a simple syntax.
RESTful API: Access endpoints for rule management and evaluation.
MySQL Database: Persistent storage for rules.
Technologies Used
Java
Spring Boot
MySQL
Maven
Getting Started
Prerequisites
JDK 11 or higher
MySQL Server
Maven
Installation

Clone the Repository:
git clone https://github.com/yourusername/Rule-Engine-App.git
cd Rule-Engine-App
Configure MySQL:

Create a database named rule_engine_db.
Update the application.properties file with your MySQL credentials.
Build the Application:

mvn clean install
Run the Application:

mvn spring-boot:run
API Endpoints
Create Rule

Method: POST
URL: /api/rules/create
Request Body:
json
Copy code
{
  "ruleString": "age > 30 AND department = 'Sales'"
}
Get All Rules

Method: GET
URL: /api/rules/all
Evaluate Rule

Method: POST
URL: /api/rules/evaluate
Request Body:
json
{
  "age": 35,
  "department": "Sales",
  "income": 60000,
  "experience": 3,
  "spend": 500
}
Testing
Use Postman or any REST client to test the API endpoints.

