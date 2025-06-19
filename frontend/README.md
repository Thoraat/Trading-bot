# 🗣️ Voice-Operated OTC Trading Bot (Frontend)

This is the **frontend** of a voice-activated Over-the-Counter (OTC) trading assistant. It enables users to place simulated cryptocurrency trades using their voice.

Built using **Vite + React**, the app connects to a backend API (powered by Spring Boot) and utilizes browser speech recognition and synthesis APIs for interactive audio feedback.

---

## 🚀 Features

* Voice-to-text using `SpeechRecognition`
* Real-time conversation transcript
* Backend integration for trading flow
* Text-to-speech bot responses
* Environment variable support for API and keys

---

## 🏗️ Project Structure

```
frontend/
├── src/
│   ├── App.jsx         # Main component
│   ├── main.jsx        # React DOM render entry
│   └── assets/         # Optional images or assets
├── .env                # API URLs and Keys
├── index.html          # HTML template
├── vite.config.js      # Vite config
```

---

## 🔧 Setup Instructions

### 1. Clone the repository

```bash
cd frontend
```

### 2. Install dependencies

```bash
npm install
```

### 3. Configure Environment Variables

Create a file named `.env` in the root of the `frontend/` folder:

```
VITE_BACKEND_API_URL=http://localhost:8080
VITE_BLAND_API_KEY=your_bland_ai_key_here
```

> ⚠️ **DO NOT** commit `.env` to source control. It's already ignored in `.gitignore`.

### 4. Start the development server

```bash
npm run dev
```

Visit `http://localhost:5173` in your browser.

---

## 🧪 How to Use

1. Click the **"Start Talking"** button.

2. Speak your command, such as:

   > "Start"

   > "Binance"

   > "Buy 2 BTC"

3. You’ll hear a response from the bot and see both your transcript and the bot’s reply.

---

## 🔐 Notes

* The browser must have **microphone access** enabled.
* This app does **not place real trades**. It is for simulation/demo only.

---

## 🧱 Tech Stack

* [Vite](https://vitejs.dev/)
* [React](https://reactjs.org/)
* Web Speech API (browser native)
* [Spring Boot](https://spring.io/projects/spring-boot) (backend API)
