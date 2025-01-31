# Student Testing Platform

## Overview
The **Student Testing Platform** is a private web-based application designed exclusively for internal use. It allows teachers to create, manage, and conduct tests. Students can access their assigned tests using a unique passkey and submit their answers securely. The platform ensures efficient test management and evaluation.

## Features
- **Teacher Features:**  
  - Create and manage tests with multiple questions.
  - Secure test access with a unique passkey.
  - View and evaluate student submissions.
  
- **Student Features:**  
  - Access assigned tests using a passkey.
  - Submit answers securely.
  - Receive test results and feedback.

## Tech Stack
- **Backend:** Spring Boot, MySQL, JPA
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL

## Installation & Setup
### Prerequisites
- Java 17+
- MySQL Server
- Node.js (for frontend development, if applicable)

### Backend Setup
1. Clone the repository (for authorized users only):
   ```sh
   git clone https://github.com/your-username/student-testing-platform.git
   cd student-testing-platform
   ```
2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/student_testing_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
3. Run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```

### Frontend Setup
1. Navigate to the frontend directory:
   ```sh
   cd frontend
   ```
2. Open `index.html` in a browser or use a local server.

## Usage
- **Teachers:** Log in, create tests, and provide the passkey to students.
- **Students:** Enter the passkey to access and submit tests.

## Future Enhancements
- Implement automated grading for objective questions.
- Add analytics for performance tracking.
- Enhance UI with modern frameworks like React or Angular.

## Contributing
This project is **private** and not open for public contributions. Unauthorized use, modification, or distribution is strictly prohibited.

## License
This project is **private and proprietary**. Copying, modifying, or distributing without explicit permission is not allowed.
