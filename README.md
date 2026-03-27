# API Automation Testing with REST Assured using Java

This repository contains API automation practice exercises built with Java, REST Assured, and TestNG.
The project is used to practice API testing concepts, request handling, response validation, and test execution structure in a simple automation framework.

---

## 📌 Project Objective

This project helps practice:

* Building API test cases with REST Assured
* Sending HTTP requests (GET, POST, PUT, DELETE)
* Validating response status codes and response bodies
* Organizing test cases using TestNG
* Managing dependencies with Maven
* Creating reusable request specifications

---

## 🛠 Tech Stack

* Java
* Maven
* REST Assured
* TestNG

---

## 📂 Project Structure

```text
restassuredtestng/
├── src/
│   ├── main/
│   └── test/
├── suites/
├── logs/
├── pom.xml
└── README.md
```

### Folder Description

* **src/test/** → contains API test classes
* **suites/** → contains TestNG suite XML files
* **logs/** → execution logs
* **pom.xml** → Maven dependencies and build configuration

---

## ✅ Features Covered

* GET/POST/PUT/PATCH/DELETE request testing
* Request specification reuse
* Response validation
* Status code assertion
* JSON response handling
* JSON schema assertion
* Allure Report
* Log4j
* TestListener
* Common functions
* Parallel set up

---

## ▶️ How to Run the Project

### 1. Clone repository

```bash
git clone https://github.com/ntdaonguyen/restassuredtestng.git
```

### 2. Open project in IntelliJ IDEA

Import as Maven project.

### 3. Install dependencies

```bash
mvn clean install
```

### 4. Run all tests

```bash
mvn clean test
```

### 5. Run specific TestNG suite

```bash
mvn test -DsuiteXmlFile=suites/testng.xml
```

---

## ⚙️ Requirements

* JDK 17+
* Maven installed
* IntelliJ IDEA recommended

---

## 📖 Learning Purpose

This repository is created for learning and practicing API automation concepts during training/course exercises.

---

## 👨‍🏫 Acknowledgement

Special thanks to my instructor Anh Tester for guidance during the learning process.
