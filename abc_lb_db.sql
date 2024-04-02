-- Create database (optional, adjust the name as needed)

CREATE DATABASE IF NOT EXISTS abc_lab_db;
USE abc_lab_db;

-- Table for storing patient information
CREATE TABLE IF NOT EXISTS user_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Table for storing tests information
CREATE TABLE IF NOT EXISTS tests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    testName VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    cost VARCHAR(100) NOT NULL
);

-- Table for storing technician information
CREATE TABLE IF NOT EXISTS technicians (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    qualification VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL
);


-- Table for storing appointments
CREATE TABLE IF NOT EXISTS Appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    fullName VARCHAR(255) NOT NULL,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    doctorName VARCHAR(255) NOT NULL,
    appointmentDate DATETIME NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    testId INT NOT NULL,
    technicianId INT,
    address TEXT NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (userId) REFERENCES user_details(id),
    FOREIGN KEY (testId) REFERENCES tests(id),
    FOREIGN KEY (technicianId) REFERENCES technicians(id)
);

-- Table for storing payments
CREATE TABLE IF NOT EXISTS payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    appointmentId INT NOT NULL,
    amount VARCHAR(100) NOT NULL,
    payment_date VARCHAR(255) NOT NULL,
    payment_status TEXT NOT NULL,
    FOREIGN KEY (appointmentId) REFERENCES Appointment(id)
);

CREATE TABLE IF NOT EXISTS results (
	id INT AUTO_INCREMENT PRIMARY KEY,
    appointmentId INT NOT NULL,
    result TEXT NOT NULL
);

