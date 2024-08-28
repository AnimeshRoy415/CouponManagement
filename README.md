Here’s an updated version of the README that includes the new analytics features:

---
# Coupon Management System

## Overview

The Coupon Management System is a Spring Boot application designed to manage discount coupons for an e-commerce platform. It allows users to create, update, retrieve, and delete coupons, apply them to shopping carts, and analyze their performance. Additionally, the system supports dynamic coupon generation, user segmentation, and other advanced features to enhance the user experience.

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
- **Coupon Expiry Notifications**: Notify users when a coupon is about to expire.
- **Coupon Sharing**: Allow users to share coupons via email or social media.
- **Dynamic Coupon Generation**: Automatically generate personalized coupons based on user behavior or purchase history.
- **Coupon Stacking Rules**: Define rules for stacking multiple coupons in a single purchase.
- **User Segmentation for Coupons**: Target specific user segments with customized coupons.

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

### Advanced Features

- **Coupon Expiry Notifications**
  - `GET /api/coupons/notify-expiry`
  - Action: Triggers notifications for coupons about to expire.

- **Coupon Sharing**
  - `POST /api/coupons/share`
  - Request Body: `ShareCouponRequest`
  - Action: Shares a coupon via email or social media.

- **Dynamic Coupon Generation**
  - `POST /api/coupons/generate-dynamic`
  - Request Body: `UserActivityData`
  - Response: `Coupon`
  - Action: Generates personalized coupons based on user behavior.

- **User Segmentation for Coupons**
  - `POST /api/coupons/segment`
  - Request Body: `UserSegmentCriteria`
  - Response: `List<Coupon>`
  - Action: Retrieves coupons for a specific user segment.

- **Coupon Stacking Rules**
  - `GET /api/coupons/stacking-rules`
  - Action: Retrieves the rules for stacking multiple coupons.

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

3. **Generating a Performance Report:**

   Request:

   ```json
   {
     "startDate": "2024-01-01",
     "endDate": "2024-08-31"
   }
   ```

   Response:

   ```json
   {
     "reportId": 1001,
     "status": "GENERATED",
     "downloadLink": "http://example.com/reports/1001"
   }
   ```

## Exception Handling

The application includes custom exceptions for specific scenarios:

- **CouponNotFound:** Thrown when a requested coupon does not exist.
- **CouponExpire:** Thrown when a coupon is expired.
- **ConditionNotMeet:** Thrown when a coupon's conditions are not met during application.
- **CouponUsageHistoryNotFound:** Thrown when requested couponUsageHistory does not exist.
- **InvalidSegmentCriteria:** Thrown when the user segmentation criteria are invalid.

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
  - `isShared`: Boolean (Indicates if the coupon has been shared)

- **Cart Table:**
  - `id`: Long (Primary Key)
  - `items`: List<CartItem> (One-to-Many relationship with CartItem)

- **UserActivity Table:**
  - `id`: Long (Primary Key)
  - `userId`: Long (Foreign Key)
  - `activityType`: Enum (Type of user activity, e.g., PURCHASE, BROWSE)
  - `timestamp`: Long (Timestamp)

## Contributing

Contributions are welcome! Fork the repository and create a pull request for any feature additions or bug fixes.

---

This README now reflects an end-to-end implementation with all the requested features. Would you like to dive into the implementation details of any specific feature?
