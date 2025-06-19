# 🧠 Voice-Operated OTC Trading Bot (Backend)

This is the **backend** service for a voice-powered OTC (Over-the-Counter) crypto trading assistant. It accepts transcribed voice commands, processes the logic to simulate a trade, and returns bot-like conversational responses.

Built using **Spring Boot**, the backend handles:

* Command parsing
* Exchange selection
* Symbol identification
* Quantity detection
* Price lookup from real exchanges (Binance, OKX, Bybit, Deribit)
* Order confirmation response

---

## 📦 Project Structure

```
backend/
├── Controller/
│   └── VoiceBotController.java
├── Service/
│   ├── ExchangeService.java
│   └── VoiceService.java
├── DemoApplication.java
├── resources/
│   └── application.properties
```

---

## 🚀 Features

* Accepts POST requests with user transcript
* Parses exchange names (e.g., Binance, OKX)
* Detects trading symbols (e.g., BTC, ETH)
* Fetches real-time price from public APIs
* Responds with conversational prompts
* Simulates order confirmation without placing real trades

---

## 🧰 Requirements

* Java 17+
* Maven or Gradle

---

## 🔧 Setup Instructions

### 1. Clone the repository

```bash
cd backend
```

### 2. Install dependencies and build

If using Maven:

```bash
mvn clean install
```

If using Gradle:

```bash
gradle build
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

Application will run by default on `http://localhost:8080`

---

## 🔄 API Endpoint

### `POST /api/chat`

Accepts transcript as JSON and returns a bot response.

#### Request

```json
{
  "transcript": "Buy 2 BTC from Binance"
}
```

#### Response

```json
{
  "response": "Confirming: Buy 2 BTC at 65000 USDT. Say 'yes' to confirm."
}
```

---

## 🛠️ Environment & API Keys

* No keys are required for public exchange APIs.
* You may need a VPN to access OKX or Deribit APIs from certain regions.

---

## 🌐 Integration Flow

The backend is meant to be triggered by:

* A Bland.ai webhook or
* A frontend voice interface (React + Vite app)

---

## 🧪 Testing

You can test locally using curl or Postman:

```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"transcript": "start"}'
```

---

## 🧱 Tech Stack

* Java 17
* Spring Boot
* RESTful API
* org.json for JSON parsing
* Public exchange APIs (Binance, OKX, Bybit, Deribit)
