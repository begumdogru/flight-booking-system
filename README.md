# FLIGHT BOOKING SYSTEM
## Flight Booking System
This repository contains the source code for a Flight Booking System, a REST-based application designed to facilitate the booking of flight tickets. The system allows users to add, remove, and update flights, manage seats for existing flights, list available flights and seats, and process payments for selected seats. The primary goal of this project is to provide a robust and production-ready solution for flight ticket booking.

## Requirements
To meet the requirements outlined for the Flight Booking System, the following REST services have been implemented:

### Flight Management

* Adding flights
* Removing flights
* Updating flight details

### Seat Management for Existing Flights

* Adding seats
* Removing seats
* Updating seat information

### Flight and Seat Listing Service

* Retrieve flight information including name, description, available seats, and price.

### Payment Service
* Allow end users to purchase selected seats.
* Ensure that a seat is not sold to two passengers simultaneously.
* Implement robust error handling for concurrent seat bookings.
## Implementation Details
* This application is designed as a backend service, and no front-end interface is included.
* The system has been thoroughly tested to ensure reliability and correctness. Test coverage for both integration and unit tests exceeds 80%.
* A production-grade solution has been provided, adhering to best practices and industry standards.

## Usage
* To use this Flight Booking System, follow these steps:

* Clone this repository to your local environment.

* Set up the required dependencies, including the database and any necessary external services.

* Build and deploy the application to your preferred hosting environment.

* Access the REST endpoints to interact with the system:

    * Use the flight management endpoints to add, remove, or update flights.
    * Use the seat management endpoints to add, remove, or update seats for existing flights.
    * Use the flight and seat listing endpoint to retrieve information about available flights and seats.
    * Utilize the payment service to complete seat bookings while ensuring concurrent booking protection.

## Run unit tests
$ ./run_unit_tests.sh

## Run integration tests
$ ./run_integration_tests.sh

## Deployment
For deployment instructions and considerations, please refer to the deployment documentation provided in the repository.

## Contributions
Contributions to this project are welcome! If you would like to contribute, please fork the repository, make your changes, and submit a pull request. Ensure that your contributions align with the project's goals and adhere to best coding practices.

# License
This project is licensed under the MIT License. Feel free to use, modify, and distribute it as needed, but please acknowledge the original source.

Thank you for choosing the Flight Booking System. We hope this application serves your needs effectively and efficiently. If you encounter any issues or have suggestions for improvement, please don't hesitate to open an issue or reach out to our development team.

Happy booking! ✈️🛫🛬

