# ☕ MoonBean Café - Final Project

A professional JavaFX Desktop Application for managing a café's ordering system, integrated with a NoSQL MongoDB backend.

## 🚀 Quick Start Guide
To run this project successfully on your local machine, please follow these steps:

1. **Install MongoDB:** Ensure [MongoDB Community Server](https://www.mongodb.com/try/download/community) is installed.
2. **Setup Data Directory:** Create the physical path `C:\data\db` on your drive (required for MongoDB's default storage).
3. **Start Database:** Run `mongod.exe` from your MongoDB bin folder to start the server on port `27017`.
4. **Launch App:** Open the project in IntelliJ IDEA and execute the `run` task via the Gradle menu.

## ✨ Key Features
* **MongoDB Integration:** Full CRUD operations using the MongoDB Java Driver and POJO Codecs.
* **Real-time Inventory Management:** Product stock levels automatically decrease upon successful checkout.
* **Unique Order Tracking:** Every transaction generates a custom unique Order ID (e.g., `MB-A1B2C3`).
* **User Authentication:** Secure registration and login system for customers.
* **Dynamic Cart System:** Real-time subtotal and total calculations.

## 🛠️ Tech Stack
* **Language:** Java 17+
* **Framework:** JavaFX (UI)
* **Build Tool:** Gradle
* **Database:** MongoDB (NoSQL)