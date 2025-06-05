# OrderHub Netty.io Matching Engine (TCP Client & Server)

This project implements a basic **order matching engine** using [Netty.io](https://netty.io/), with a TCP client sending `MARKET` orders (BUY or SELL), and a TCP server that maintains an order book and matches incoming orders according to predefined logic.

## 📌 Features

- TCP Server built with Netty.io
- TCP Client sends market orders in JSON format
- Matching Engine Logic:
  - BUY orders match with lowest price SELL orders
  - SELL orders match with highest price BUY orders
  - FIFO order execution for same price
  - Only full order matches (no partial fills)
  - Unmatched orders are rejected

---

## 📦 Order Format (Client → Server)

Send orders as JSON:

```json
{ "type": "BUY", "quantity": 10, "accountId": "1233" }

{ "type": "SELL", "quantity": 5, "accountId": "5678" }
```

---

## 📤 Execution Report Format (Server → Client)

### ✅ Successful Match (FILLED)

```json
{
  "type": "exe_report",
  "initialQuantity": "100",
  "executedPrice": 100,
  "executedQuantity": 10,
  "accountId": "1233",
  "status": "FILLED"
}
```

### ❌ Rejected (No Match)

```json
{
  "type": "exe_report",
  "initialQuantity": "100",
  "executedPrice": null,
  "executedQuantity": null,
  "accountId": "5678",
  "status": "REJECTED"
}
```

---

## ⚙️ Matching Logic

1. **Order Book Initialization**
   - Server loads initial BUY and SELL orders
   - BUY orders: sorted by **highest price first**
   - SELL orders: sorted by **lowest price first**

2. **When MARKET order arrives**
   - For `BUY`, find lowest-priced `SELL` order
   - For `SELL`, find highest-priced `BUY` order
   - FIFO applies for same price
   - Only full matches are executed
   - If no match found, order is rejected

---

## 🛠️ How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/auto-monkey/mobile-app.git
cd mobile-app
```

### 2. Build and Run the Server

```bash
cd server
./gradlew run
```

### 3. Run the Client

```bash
cd client
./gradlew run
```

Or compile and run directly via IDE like IntelliJ or VS Code.

---

## ✅ Dependencies

- Java 8+
- Netty.io
- Gson or Jackson (for JSON parsing)
- Gradle (or Maven)

---

## 📃 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
