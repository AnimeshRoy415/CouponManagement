# Coupon Management System

## Overview

The Coupon Management System is a Spring Boot application designed to manage discount coupons for an e-commerce platform. The application allows users to create, update, retrieve, and delete coupons. It also supports the application of coupons to shopping carts, providing discounts based on different criteria such as Buy X Get Y, Cart-wise discounts, and Product-wise discounts.

## Features

- **Create Coupons**: Create new discount coupons with specific criteria.
- **Retrieve Coupons**: Fetch individual coupons or all available coupons, with optional filtering for active/inactive status.
- **Update Coupons**: Update existing coupons with new details.
- **Delete Coupons**: Remove coupons from the system.
- **Apply Coupons**: Apply a coupon to a shopping cart to calculate discounts.
- **Find Applicable Coupons**: Retrieve a list of applicable coupons for a specific cart.

## Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6.3** or higher
- **Spring Boot 3.x**

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AnimeshRoy415/CouponManagement.git
   cd coupon-management-system
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

### Configuration

The application uses an in-memory H2 database for simplicity. You can configure a different database in the `application.properties` file.

### Running Tests

To run the unit tests:

```bash
mvn test
```

### API Endpoints

The application exposes several RESTful endpoints for managing and applying coupons.

- **Create Coupon**
  - `POST /api/coupons`
  - Request Body: `CouponDto`
  - Response: `Coupon`

- **Get Coupon by ID**
  - `GET /api/coupons/{id}`
  - Response: `Coupon`

- **Get All Coupons**
  - `GET /api/coupons`
  - Query Params: `isActive` (optional)
  - Response: `List<Coupon>`

- **Update Coupon**
  - `PUT /api/coupons/{id}`
  - Request Body: `Coupon`
  - Response: `Coupon`

- **Delete Coupon**
  - `DELETE /api/coupons/{id}`

- **Apply Coupon**
  - `POST /api/coupons/{couponId}/apply`
  - Request Body: `Cart`
  - Response: `Cart`

- **Find Applicable Coupons**
  - `POST /api/coupons/applicable`
  - Request Body: `Cart`
  - Response: `List<CouponResponseDto>`

### Example Usage

1. **Creating a Coupon:**

   Request:

   ```json
   {
     "type": "CART_WISE",
     "details": {
       "discountPercentage": 10.0,
       "threshold": 100.0
     },
     "expirationDate": 1716166464000,
     "repetitionLimit": 1
   }
   ```

   Response:

   ```json
   {
     "id": 1,
     "type": "CART_WISE",
     "details": {
       "discountPercentage": 10.0,
       "threshold": 100.0
     },
     "expirationDate": 1716166464000,
     "repetitionLimit": 1
   }
   ```

2. **Applying a Coupon:**

   Request:

   ```json
   {
     "items": [
       {
         "productId": 101,
         "quantity": 3,
         "price": 50.0
       }
     ]
   }
   ```

   Response:

   ```json
   {
     "totalPrice": 150.0,
     "totalDiscount": 15.0,
     "finalPrice": 135.0,
     "items": [
       {
         "productId": 101,
         "quantity": 3,
         "price": 50.0,
         "totalDiscount": 15.0
       }
     ]
   }
   ```

### Exception Handling

The application has custom exceptions for various scenarios:

- **CouponNotFound:** Thrown when a requested coupon does not exist.
- **CouponExpire:** Thrown when a coupon is expired.
- **ConditionNotMeet:** Thrown when a coupon's conditions are not met during application.

### Database Schema

The application uses an H2 in-memory database with the following structure:

- **Coupons Table:**
  - `id`: Long (Primary Key)
  - `type`: Enum (Coupon type, e.g., CART_WISE, PRODUCT_WISE, BX_GY)
  - `details`: JSON (Details about the coupon)
  - `expirationDate`: Long (Timestamp)
  - `repetitionLimit`: Integer

- **Cart Table:**
  - `id`: Long (Primary Key)
  - `items`: List<CartItem> (One-to-Many relationship with CartItem)

### Future Enhancements

- **User Authentication**: Implement user authentication and authorization to restrict access to coupon management features.
- **Integration with Payment Gateway**: Integrate the coupon system with a payment gateway to apply discounts at checkout.
- **Analytics**: Add features to track coupon usage and generate reports.

### Contributing

Contributions are welcome! Please fork the repository and create a pull request for any feature additions or bug fixes.


---
