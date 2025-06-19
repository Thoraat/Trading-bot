# 🎙️ Voice-Driven OTC Crypto Trading Bot

This project is a full-stack voice-operated Over-the-Counter (OTC) crypto trading bot. Users interact with the system via voice commands to simulate buying crypto assets from major exchanges like Binance or OKX.

## 🧱 Project Structure

```
Tradiing-bot/
├── frontend/   # React + Vite app for voice input and response
├── backend/    # Spring Boot service to process trades and fetch real-time prices
```

---

## ✨ Features

* 🎤 Voice command recognition (Web Speech API)
* 💬 Real-time transcript and voice response
* 🔧 Spring Boot backend for processing logic
* 💰 Fetches real-time prices from public exchange APIs
* ✅ Simulates order confirmation based on user intent

---

## 🏁 Getting Started

### ✅ Prerequisites

* Node.js & npm (for frontend)
* Java 17+ (for backend)
* Git

---

## 🚀 Setup Instructions

### 🔹 Frontend (`frontend/`)

```bash
cd frontend
npm install
```

Create a `.env` file:

```env
VITE_BACKEND_API_URL=http://localhost:8080
VITE_BLAND_API_KEY=your_bland_ai_key_here
```

Start the app:

```bash
npm run dev
```

### 🔹 Backend (`backend/`)

```bash
cd backend/demo
./mvnw spring-boot:run
```

Backend will run on `http://localhost:8080`

---

## 🎤 How to Use

1. Click **Start Talking** in the frontend.
2. Speak commands like:

   * “Start”
   * “Binance”
   * “Buy 2 BTC”
3. The app will confirm intent and respond with the real-time price.

> No real trades are placed — it’s a simulation.

---

## 🌐 Technologies Used

* **Frontend:** React, Vite, Web Speech API, Text-to-Speech
* **Backend:** Java, Spring Boot, REST API, JSON
* **APIs:** Binance (public), OKX, Bybit, Deribit (future scope)

---

## 🤖 Future Enhancements

* Real order placement via exchange API keys
* Natural Language Understanding (NLU) for more flexible commands
* User authentication + trade history tracking
* Deployment (Render, Vercel, Heroku, etc.)

---

## 📂 Related Files

* `frontend/README.md` — for voice interface setup
* `backend/README.md` — for API and logic setup

---

Happy building! 🚀
