import { useState } from 'react';

function App() {
  const [transcript, setTranscript] = useState('');
  const [response, setResponse] = useState('');
  const [listening, setListening] = useState(false);

  const recognition = new window.webkitSpeechRecognition(); // Or window.SpeechRecognition
  recognition.continuous = false;
  recognition.lang = 'en-US';

  const startListening = () => {
    setListening(true);
    recognition.start();

    recognition.onresult = async (event) => {
      const spokenText = event.results[0][0].transcript;
      setTranscript(spokenText);
      console.log("You said:", spokenText);

      const blandResponse = await sendToBackend(spokenText);
      setResponse(blandResponse);

      speakResponse(blandResponse);
      setListening(false);
    };

    recognition.onerror = (e) => {
      console.error("Speech recognition error", e);
      setListening(false);
    };
  };

  const sendToBackend = async (spokenText) => {
    try {
      const res = await fetch(import.meta.env.VITE_BACKEND_API_URL + "/api/chat", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${import.meta.env.VITE_BLAND_API_KEY}`,
        },
        body: JSON.stringify({ transcript: spokenText }),
      });

      const data = await res.json();
      return data.response || "No response from bot.";
    } catch (err) {
      console.error("Error sending to backend:", err);
      return "Error communicating with the backend.";
    }
  };

  const speakResponse = (text) => {
    const synth = window.speechSynthesis;
    const utter = new SpeechSynthesisUtterance(text);
    synth.speak(utter);
  };

  return (
    <div style={{ padding: '2rem', fontFamily: 'sans-serif' }}>
      <h1>OTC Voice Trading Bot</h1>
      <button onClick={startListening} disabled={listening} style={{ padding: '0.75rem 1.5rem', fontSize: '1rem' }}>
        {listening ? "Listening..." : "Start Talking"}
      </button>

      <div style={{ marginTop: '2rem' }}>
        <h3>You said:</h3>
        <div style={{ backgroundColor: '#f0f0f0', padding: '1rem', borderRadius: '8px' }}>{transcript}</div>
      </div>

      <div style={{ marginTop: '1.5rem' }}>
        <h3>Bot Response:</h3>
        <div style={{ backgroundColor: '#e8f5e9', padding: '1rem', borderRadius: '8px' }}>{response}</div>
      </div>
    </div>
  );
}

export default App;
