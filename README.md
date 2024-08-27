Here’s an updated version of the README that includes the new analytics features:

---

# Coupon Management System

## Overview

The Coupon Management System is a Spring Boot application designed to manage discount coupons for an e-commerce platform. It enables users to create, update, retrieve, and delete coupons, apply them to shopping carts, and find applicable coupons. The application also supports various discount criteria like Buy X Get Y, cart-wise discounts, and product-wise discounts.

## Features

- **Create Coupons**: Easily create new discount coupons with specific criteria.
- **Retrieve Coupons**: Fetch individual coupons or all available coupons, with optional filtering by active/inactive status.
- **Update Coupons**: Update existing coupons with new details.
- **Delete Coupons**: Remove coupons from the system.
- **Apply Coupons**: Apply one or multiple coupons to a shopping cart and calculate discounts.
- **Find Applicable Coupons**: Retrieve a list of applicable coupons for a specific cart.
- **Analytics**: 
  - **Top Performing Coupons**: Identify the top-performing coupons based on the revenue they generate.
  - **Least Used Coupons**: Identify the coupons with the least number of redemptions.
  - **Performance Reports**: Generate detailed reports on coupon performance and usage trends.
- **Usage History**: Track and retrieve the usage history of coupons.

## Getting Started

### Prerequisites

Ensure you have the following installed before running the application:

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

The application uses an in-memory H2 database by default. You can configure a different database in the `application.properties` file.

### Running Tests

To run unit tests:

```bash
mvn test
```

## API Endpoints

The application exposes several RESTful endpoints for managing, applying, and analyzing coupons.

### Coupon Management

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

- **Find Applicable Coupons**
  - `POST /api/coupons/applicable-coupons`
  - Request Body: `Cart`
  - Response: `List<CouponResponseDto>`

- **Apply Multiple Coupons**
  - `POST /api/coupons/apply-coupons`
  - Request Body: `ApplyCouponsRequest`
  - Response: `Cart`

### Coupon Analytics

- **Get Top Performing Coupons**
  - `GET /api/analytics/top-performing-coupons`
  - Query Params: `limit` (default is 10)
  - Response: `List<Coupon>`

- **Get Least Used Coupons**
  - `GET /api/analytics/least-used-coupons`
  - Query Params: `limit` (default is 10)
  - Response: `List<Coupon>`

- **Generate Performance Report**
  - `POST /api/analytics/generate-performance-report`
  - Action: Generates a report on coupon performance.
  
- **Generate Usage Report**
  - `POST /api/analytics/generate-usage-report`
  - Action: Generates a report on coupon usage.

### Coupon Usage History

- **Get All Coupon Usage History**
  - `GET /api/coupon-usage-history/all`
  - Response: `List<CouponUsageHistResponseDto>`

- **Get Coupon Usage History by ID**
  - `GET /api/coupon-usage-history/{id}`
  - Response: `CouponUsageHistResponseDto`

- **Get Coupon Usage History List by IDs**
  - `GET /api/coupon-usage-history`
  - Query Params: `ids`
  - Response: `List<CouponUsageHistResponseDto>`

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

2. **Applying Multiple Coupons:**

   Request:

   ```json
   {
     "couponIds": [1, 2],
     "cart": {
       "items": [
         {
           "productId": 101,
           "quantity": 3,
           "price": 50.0
         }
       ]
     }
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

## Exception Handling

The application includes custom exceptions for specific scenarios:

- **CouponNotFound:** Thrown when a requested coupon does not exist.
- **CouponExpire:** Thrown when a coupon is expired.
- **ConditionNotMeet:** Thrown when a coupon's conditions are not met during application.

## Database Schema

The application uses an H2 in-memory database with the following schema:

- **Coupons Table:**
  - `id`: Long (Primary Key)
  - `type`: Enum (Coupon type, e.g., CART_WISE, PRODUCT_WISE, BX_GY)
  - `details`: JSON (Details about the coupon)
  - `expirationDate`: Long (Timestamp)
  - `repetitionLimit`: Integer
  - `totalRevenueGenerated`: Double (Total revenue generated by the coupon)
  - `redemptionCount`: Integer (Number of times the coupon has been redeemed)

- **Cart Table:**
  - `id`: Long (Primary Key)
  - `items`: List<CartItem> (One-to-Many relationship with CartItem)


## Contributing

Contributions are welcome! Fork the repository and create a pull request for any feature additions or bug fixes.

---
