# Fast And Flexter Application
Backend Booking management API


## Pre-Requisite

   OS: MacOS or Ubuntu 22.04

   Project implemented using Java 21, Gradle 8.12

   Docker version 28.0.4 (pre-installed)

   Docker Compose version v2.34.0* (pre-installed)

   We use Postgresql as data storage, Redis as data cache


## Database Design


## Application Endpoints

### Booking endpoints

  ``` sh
  POST /api/v1/bookings, create a new booking

  GET /api/v1/bookings/{bookingId}, get booking using id

  ```

  
### Vehicle endpoints
  
  ``` sh
  
   GET /api/v1/vehicles?city={city}&pickupDateTime={pickupDateTime}&returnDateTime={returnDateTime}, Retrieve all vehicles that match parameters criteria
   ```


## Configuration and Deployment

   Clone the project
   ``` sh
   git clone https://github.com/cnleng/bookingsystem.git
   ```

   Install docker & docker-compose MacOS (Using brew)
   ``` sh
   brew install docker
   brew install docker-compose
   ```

   Build and deploy project from command line using docker compose & docker. Make sure that ports 8080, 8082, 6379, 5432, 5005 are not in used.
   ``` sh
   cd bookingsystem/
   docker-compose up --build -d

   or

   docker-compose up --build
   ```

   Test an API call using credentials admin/dangerous (hardcoded values). API Endpoints Swagger could also be accessed through following url http://localhost:8080/swagger-ui/index.html

   Create Booking:
   ``` sh
    curl -X 'POST' \
    'http://localhost:8080/api/v1/bookings' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
    "vehicleId": "cffd5682-5857-4d46-b256-265df98b5f0a",
    "pickupDateTime": "22-05-2025 08:32:00",
    "returnDateTime": "23-05-2025 08:32:00"
    }'
   ```

   Get Booking:
   ``` sh
        curl -X 'GET' \
        'http://localhost:8080/api/v1/bookings/accd9ad1-ab59-4183-b0d4-cebd2013bd34'
   ```

   Get Vehcile using city, pickup and return date and time:
   ``` sh
        curl -X 'GET' \
        'http://localhost:8080/api/v1/vehicles?city=Los%20Angeles&pickupDateTime=22-05-2025%2008%3A32%3A00&returnDateTime=23-05-2025%2008%3A32%3A00' \
        -H 'accept: */*'
   ```

   Tear down the project from command line using docker compose & docker
   ``` sh
   docker-compose down
   ```

## Room for Improvement

* Complete covergae for Unit tests (unit tests, and mocks)
* Complete validation fot transport objects
