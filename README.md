**Expense Sharing Backend (Splitwise-like System)**

**Overview**

  * This project is a backend system for an expense sharing application, similar to Splitwise.
  * It allows users to add shared expenses and track who owes whom based on different split strategies.

  * The goal of this project is to demonstrate clean backend design, correct business logic, and RESTful API implementation.

**Features**

* Add shared expenses

* Support multiple split types:

* Equal split

* Exact amount split

* Percentage split

* Track balances between users

* View who owes whom

* REST APIs tested using Postman

* Clean separation of controller and service layers

**Tech Stack**

  (i) Java
  
  (ii) Spring Boot
  
  (iii) Maven
  
  (iv) Postman (for API testing)


**API Endpoints**

1️. Add Expense

* POST /api/expense

* Request Body

    {
      "expenseId": "E1",
      "groupId": "G1",
      "totalAmount": 900,
      "paidBy": "A",
      "splitType": "EQUAL",
      "splits": [
        { "userId": "A", "amount": 0 },
        { "userId": "B", "amount": 0 },
        { "userId": "C", "amount": 0 }
      ]
    }


* Response

  * Expense added successfully

2️. View All Balances

* GET /api/balances

* Response

    {
      "B": { "A": 300.0 },
      "C": { "A": 300.0 }
    }

* Meaning

    User B owes A → 300
    
    User C owes A → 300

3️. View Balance for a Specific User (Optional Enhancement)

* GET /api/balances/{userId}

* Example

    * GET /api/balances/B

    * Response

      {
        "A": 300.0
      }

**Design Decisions**

* Used in-memory data structures for simplicity and clarity

* Followed layered architecture:

* Controller → Service → Business Logic

* Avoided over-engineering (no database/authentication) as per assignment scope

* Designed APIs to be easily extensible in the future

**Testing**

* All APIs were tested using Postman

* Tested with:

    i. Valid inputs

    ii. Multiple expense additions

    iii. Different split types

**How to Run the Project
Prerequisites**

i. Java 8+ { Used Java 21 }

ii. Maven
