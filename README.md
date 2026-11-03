# JPetStore Web Automation Testing - Katalon Studio

## 📌 Project Overview
This project is an automated web testing suite built using **Katalon Studio** designed to test the **JPetStore (OctoPerf)** web application ([https://petstore.octoperf.com/catalog](https://petstore.octoperf.com/catalog)). JPetStore is a classic sample e-commerce application modeled after an online pet store, featuring product catalog browsing, keyword searching, user authentication, and shopping cart workflows.

This automation project uniquely integrates **UI automated testing with a local MySQL Database**. Instead of hardcoding test data, test scripts dynamically query and fetch user credentials and search parameters from a local database (`db_testing_jpetstore`) during execution, ensuring a realistic data-driven testing approach.

---

## 🛠️ Tech Stack & Prerequisites
* **Test Automation Tool:** Katalon Studio (v11.4.0 or higher recommended)
* **Scripting Language:** Groovy / Java
* **Database:** MySQL / phpMyAdmin (XAMPP/WAMP)
* **Browser:** Google Chrome

---

## 📂 Test Suite & Structure

The repository is organized into two main feature modules covering both positive and negative scenarios:

### 1. Login Module (`Test Cases/Login`)
* **`TC_001_Succes_Login`**: Tests valid user authentication by pulling credentials (`username` and `password`) from the database, entering them into the login form, and confirming successful login by verifying the presence of the Sign Out element.
* **`TC_002_Failed_Login`**: Tests negative authentication scenarios with invalid or incorrect credentials.

### 2. Search Product Module (`Test Cases/Search_Product`)
* **`TC_001_Succes_Search_Product_Fish`**: Dynamically retrieves a valid fish name from the database, executes a product search, and verifies that the product catalog table renders correctly.
* **`TC_002_Field_Search_Null_Failed`**: Validates system behavior when attempting to search with empty or null keywords.
* **`TC_003_Search_Product_Data_Kosong`**: Tests search handling for non-existent or invalid products (e.g., combining a valid name with an invalid suffix like `999_Invalid`) to ensure the system handles empty search results properly.

---

## 📊 Test Coverage

| Module | Test Case ID / Name | Type | Coverage Focus | Status |
| :--- | :--- | :--- | :--- | :---: |
| **Login** | `TC_001_Succes_Login` | Positive | DB-driven valid credentials & session initialization | ✅ Active |
| **Login** | `TC_002_Failed_Login` | Negative | Invalid user login handling | ✅ Active |
| **Search Product** | `TC_001_Succes_Search_Product_Fish` | Positive | DB-driven valid product search & catalog rendering | ✅ Active |
| **Search Product** | `TC_002_Field_Search_Null_Failed` | Negative | Empty/Null search keyword validation | ✅ Active |
| **Search Product** | `TC_003_Search_Product_Data_Kosong` | Negative | Non-existent product search handling | ✅ Active |

---

## 🚀 How to Run the Tests

Follow these steps to set up and run the test suite locally on your machine:

1. **Clone the Repository:**
   ```bash
   git clone [https://github.com/Rezaoctafahlevi10/katalon-petstore-web.git](https://github.com/Rezaoctafahlevi10/katalon-petstore-web.git)
