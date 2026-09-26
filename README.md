# Hospital Management System (Java Swing GUI)

## Project Overview

The **Hospital Management System** is a desktop-based Java application developed using **Java Swing GUI** and **Object-Oriented Programming (OOP)** concepts.

The system is designed to manage common hospital activities such as:

* Patient Management
* Doctor Management
* Appointment Management
* Medical Record Management
* Medical Bill Management
* File-Based Data Persistence
* Dashboard and Schedule Management

This project demonstrates the practical implementation of:

* Encapsulation
* Inheritance
* Abstraction
* Polymorphism
* Exception Handling
* ArrayList Collections
* File Handling
* GUI Development using Java Swing

---

# Features

## 1. Patient Management

Users can manage patient information including:

* Patient ID
* Patient Name
* Phone Number
* Age
* Address
* Diseases
* Blood Group

### Available Operations:

* Add Patient
* View Patients
* Update Patient
* Delete Patient

### Validation:

* Duplicate Patient ID checking
* Required field validation
* Numeric input validation

---

## 2. Doctor Management

The system allows hospital staff to manage doctor information.

### Doctor Information:

* Doctor ID
* Doctor Name
* Phone Number
* Specialization
* Qualification
* Salary

### Available Operations:

* Add Doctor
* View Doctors
* Update Doctor
* Delete Doctor

### Validation:

* Duplicate Doctor ID checking
* Required field validation
* Numeric input validation

---

## 3. Appointment Management

Appointments can be created and managed between patients and doctors.

### Appointment Information:

* Appointment ID
* Patient ID
* Doctor ID
* Appointment Date
* Appointment Time
* Reason

### Available Operations:

* Create Appointment
* View Appointments
* Update Appointment
* Delete Appointment

### Validation:

* Duplicate Appointment ID checking
* Patient ID existence checking
* Doctor ID existence checking
* Invalid input handling

---

## 4. Medical Record Management

The system stores medical information related to patients.

### Medical Record Information:

* Record ID
* Patient ID
* Doctor ID
* Diagnosis
* Treatment

### Available Operations:

* Create Medical Record
* View Medical Records
* Update Medical Record
* Delete Medical Record

### Validation:

* Duplicate Record ID checking
* Patient and Doctor reference validation
* Invalid input handling

---

## 5. Medical Bill Management

The system provides basic medical billing management.

### Bill Information:

* Bill ID
* Patient ID
* Bill Amount
* Bill Date
* Payment Status

### Available Operations:

* Add Medical Bill
* View Medical Bills
* Update Medical Bill
* Delete Medical Bill

### Validation:

* Duplicate Bill ID checking
* Patient ID validation
* Numeric input validation
* Invalid input handling

---

## 6. File Handling and Data Persistence

The project uses file handling to save and restore data even after the application is closed.

Separate files are used for different types of records:

* `Patient_File.txt`
* `Doctor_File.txt`
* `Appointment_File.txt`
* `MedicalRecord_File.txt`
* `MedicalBill_File.txt`

The `FileManager` class handles:

* Writing ArrayList data to files
* Loading data from files
* Updating stored data
* Maintaining persistent records

When the application starts, previously saved data is loaded automatically.

---

## 7. Dashboard

The Java Swing GUI includes a modern hospital administration dashboard.

The dashboard provides:

* Total Patient Count
* Total Doctor Count
* Total Appointment Count
* Medical Record Count
* Medical Bill Count
* Activity Overview
* Appointment/Schedule Information
* Medical Bill Information
* Quick Navigation

The dashboard is designed using a card-based interface with a modern hospital administration theme.

---

## 8. Exception Handling

A custom exception class is implemented:

```java
class CustomException extends Exception
```

It is used for handling business-rule and validation errors such as:

* Duplicate IDs
* Patient ID not found
* Doctor ID not found
* Appointment not found
* Medical Record not found
* Medical Bill not found
* Invalid input conditions

The GUI displays runtime error messages using Swing dialog boxes.

---

# Technologies Used

| Technology         | Purpose                   |
| ------------------ | ------------------------- |
| Java               | Core Programming Language |
| Java Swing         | GUI Development           |
| OOP Concepts       | System Design             |
| ArrayList          | Dynamic Data Storage      |
| File Handling      | Persistent Data Storage   |
| Exception Handling | Error Management          |
| IntelliJ IDEA      | Development Environment   |

---

# Object-Oriented Programming Concepts Used

## 1. Abstraction

Abstraction is implemented using:

```java
public abstract class Person
```

The `Person` class contains common attributes for people in the hospital and declares an abstract method:

```java
public abstract void displayInfo();
```

The abstract class provides a common structure for different types of persons in the system.

---

## 2. Inheritance

Inheritance is implemented using the `Person` superclass.

Classes extending `Person`:

```java
class Patient extends Person
class Doctor extends Person
```

This allows Patient and Doctor classes to reuse common properties such as:

* Name
* ID
* Phone Number

---

## 3. Encapsulation

Private variables are used throughout the project.

Example:

```java
private String name;
private int id;
private String phone_number;
```

Access to these variables is controlled through getter and setter methods.

Example:

```java
public String getName()
public int getID()
public void setName(String name)
```

This protects the internal state of objects and demonstrates encapsulation.

---

## 4. Polymorphism

Polymorphism is demonstrated through method overriding.

The abstract `displayInfo()` method in `Person` is overridden by subclasses.

Example:

```java
@Override
public void displayInfo()
```

Different implementations are provided in:

* `Patient`
* `Doctor`

The same method name can therefore provide different behavior for different objects.

---

## 5. Exception Handling

The project uses a custom exception class:

```java
class CustomException extends Exception
```

Exception handling is used in both the console-based management system and the Swing GUI.

The system uses `try-catch` blocks to handle:

* Duplicate ID errors
* Invalid numeric input
* Missing records
* Invalid patient references
* Invalid doctor references

---

# Project Structure

## Classes Used

### 1. Person (Abstract Class)

The parent class of Patient and Doctor.

Contains:

* Name
* ID
* Phone Number
* Getter and Setter Methods
* Abstract `displayInfo()` method

---

### 2. Patient

Extends `Person`.

Stores:

* Patient information
* Age
* Address
* Diseases
* Blood Group

Handles patient information management.

---

### 3. Doctor

Extends `Person`.

Stores:

* Doctor information
* Specialization
* Qualification
* Salary

Handles doctor information management.

---

### 4. Appointment

Handles appointment information between patients and doctors.

Stores:

* Appointment ID
* Patient ID
* Doctor ID
* Date
* Time
* Reason

---

### 5. MedicalRecord

Handles patient medical records.

Stores:

* Record ID
* Patient ID
* Doctor ID
* Diagnosis
* Treatment

---

### 6. MedicalBill

Handles hospital billing information.

Stores:

* Bill ID
* Patient ID
* Amount
* Bill Date
* Payment Status

---

### 7. HospitalManagementSystem

Acts as the main management/backend class.

Contains:

* Patient ArrayList
* Doctor ArrayList
* Appointment ArrayList
* Medical Record ArrayList
* Medical Bill ArrayList

It provides the console-based CRUD management system and connects the data with the `FileManager`.

---

### 8. FileManager

Handles file-based persistence.

Responsible for:

* Saving Patient data
* Saving Doctor data
* Saving Appointment data
* Saving Medical Record data
* Saving Medical Bill data
* Loading saved data when the application starts

---

### 9. CustomException

Custom exception class used for validation and business-rule errors.

```java
public class CustomException extends Exception
```

---

### 10. Driver

Main GUI class of the project.

Contains:

* Hospital administration dashboard
* Sidebar navigation
* Patient management interface
* Doctor management interface
* Appointment management interface
* Medical record interface
* Medical billing interface
* Schedule section
* Dashboard statistics
* JTable-based data display
* GUI event handling
* Runtime error messages

---

# GUI Sections

## Dashboard Panel

Used for:

* Viewing hospital statistics
* Patient count
* Doctor count
* Appointment count
* Medical record count
* Medical bill count
* Activity information
* Quick navigation

---

## Patient Management Panel

Used for:

* Adding patients
* Updating patients
* Deleting patients
* Viewing patient information

---

## Doctor Management Panel

Used for:

* Adding doctors
* Updating doctors
* Deleting doctors
* Viewing doctor information

---

## Appointment Management Panel

Used for:

* Creating appointments
* Updating appointments
* Deleting appointments
* Viewing appointment information

---

## Medical Record Panel

Used for:

* Creating medical records
* Updating medical records
* Deleting medical records
* Viewing medical records

---

## Medical Bill Panel

Used for:

* Creating medical bills
* Updating medical bills
* Deleting medical bills
* Viewing billing information

---

## Schedule Panel

Used for:

* Viewing appointment schedules
* Displaying appointment information
* Monitoring scheduled activities

---

# Sample Functionalities

## Create Patient

```java
Patient patient = new Patient(
    name,
    id,
    phone_number,
    age,
    address,
    diseases,
    blood_group
);
```

---

## Create Doctor

```java
Doctor doctor = new Doctor(
    name,
    id,
    phone_number,
    specialization,
    qualification,
    salary
);
```

---

## Create Appointment

```java
Appointment appointment = new Appointment(
    appointment_id,
    patient_id,
    doctor_id,
    date,
    time,
    reason
);
```

---

## Create Medical Record

```java
MedicalRecord record = new MedicalRecord(
    record_id,
    patient_id,
    doctor_id,
    diagnosis,
    treatment
);
```

---

## Create Medical Bill

```java
MedicalBill bill = new MedicalBill(
    bill_id,
    patient_id,
    amount,
    bill_date,
    payment_status
);
```

---

# Exception Example

```java
throw new CustomException("Patient ID does not exist.");
```

Another example:

```java
throw new CustomException("Doctor ID already exists.");
```

---

# File Persistence Example

Patient information is stored using:

```java
fileManager.PatientFile();
```

Doctor information is stored using:

```java
fileManager.DoctorFile();
```

Appointment information is stored using:

```java
fileManager.AppointmentFile();
```

Medical records are stored using:

```java
fileManager.MedicalRecordFile();
```

Medical bills are stored using:

```java
fileManager.MedicalBillFile();
```

---

# Future Improvements

Possible future upgrades:

* Database Integration (MySQL)
* Login and Authentication System
* Admin and Staff Role Management
* Doctor Availability Management
* Appointment Status Tracking
* Advanced Patient Search
* Prescription Management
* Emergency Patient Management
* Online Appointment Booking
* Advanced Reporting System
* Multi-user Access Control

---

# How to Run the Project

## Step 1

Install:

* JDK 8 or higher
* IntelliJ IDEA / VS Code / NetBeans

---

## Step 2

Open the project in IntelliJ IDEA.

Make sure all Java files are inside the same source folder.

---

## Step 3

Compile the project:

```bash
javac *.java
```

---

## Step 4

Run the GUI application:

```bash
java Driver
```

The Java Swing-based Hospital Management System interface will open.

---

# Data Persistence

The application automatically loads previously saved records when it starts.

The following files are used:

```text
Patient_File.txt
Doctor_File.txt
Appointment_File.txt
MedicalRecord_File.txt
MedicalBill_File.txt
```

New, updated, or deleted records are written back to the corresponding file.

---

# Learning Outcomes

Through this project, the following concepts were practiced:

* Java Swing GUI Programming
* Event Handling
* Object-Oriented Design
* Encapsulation
* Inheritance
* Abstraction
* Polymorphism
* Exception Handling
* Java Collections Framework
* File Handling
* CRUD Operations
* Validation and Business Rules
* Desktop Application Development

---

# Author

Developed as a **Java OOP & GUI Project**.

Course:

**CSE282.6 – Object Oriented Programming using Java**

Project:

**Hospital Management System**

---

# Conclusion

The **Hospital Management System** is a Java Swing-based desktop application designed to manage essential hospital operations through a graphical user interface.

The project integrates:

* Patient Management
* Doctor Management
* Appointment Management
* Medical Records
* Medical Billing
* CRUD Operations
* File-Based Persistence
* Exception Handling
* OOP Concepts
* Modern Swing GUI

The system demonstrates how Object-Oriented Programming, file handling, exception handling, and Java Swing can be combined to develop a practical hospital management application.
