# SpringBoot-Cinema

This is a web application for booking movie tickets, developed using Spring Boot 6. The application allows users to browse movies, check showtimes, select seats, and book tickets.

## Features

- **User Registration and Authentication**: Users can create accounts, log in, and log out securely.
- **Browse Movies**: Users can view a list of currently showing movies, along with details such as synopsis, cast, and ratings.
- **Showtimes and Availability**: Users can view showtimes for each movie and check seat availability.
- **Booking Tickets**: Users can select seats and book tickets for a chosen showtime.
- **Booking History**: Users can view their booking history and download tickets.
- **Admin Panel**: Admin users can manage movies, showtimes, and bookings.

## Technologies Used

- **Backend**: Spring Boot 6, Spring Security, Spring Data JPA, Hibernate
- **Database**: MySQL
- **Payment Gateway**: Stripe (or any other payment gateway)
- **Build Tool**: Maven

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.8+
- MySQL database

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/springboot-cinema.git
   cd springboot-cinema
   ```

2. **Set up the database**:
   - Create a MySQL database named `movie_booking`.
   - Update the database configuration in `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/movie_booking
     spring.datasource.username=yourUsername
     spring.datasource.password=yourPassword
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Configure the payment gateway**:
   - Add your payment gateway API keys in `src/main/resources/application.properties`:
     ```properties
     stripe.api.key=yourStripeApiKey
     ```

4. **Build and run the application**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the application**:
   - Open a web browser and navigate to `http://localhost:8080`.


## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any changes.


## Contact

For any inquiries, please contact us at ruyhoang1801@gmail.com.

---
