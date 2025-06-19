package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VoiceService {

    @Autowired
    private ExchangeService exchangeService;

    private String selectedExchange = "";
    private String selectedSymbol = "";
    private double quantity = 0;
    private double price = 0;
    private boolean awaitingConfirmation = false;

    public String processUserInput(Map<String, Object> request) {
        Object transcriptObj = request.get("transcript");
        if (transcriptObj == null) {
            return "Error: Missing 'transcript' in request body.";
        }

        String userText = transcriptObj.toString().toLowerCase();

        if (userText.contains("start")) {
            resetState();
            return "Welcome! Which exchange would you like to use? OKX, Binance, Bybit, or Deribit?";
        }

        // Exchange Selection
        if (userText.contains("binance")) {
            selectedExchange = "binance";
            return "Great! Which symbol would you like to trade? (e.g., BTC, ETH, SOL)";
        } else if (userText.contains("okx")) {
            selectedExchange = "okx";
            return "OKX selected. Please specify the trading symbol.";
        } else if (userText.contains("bybit")) {
            selectedExchange = "bybit";
            return "Bybit selected. Please provide the symbol you want to trade.";
        } else if (userText.contains("deribit")) {
            selectedExchange = "deribit";
            return "Deribit selected. What symbol are you interested in trading?";
        }

        // Symbol Selection
        if (selectedExchange.length() > 0 && selectedSymbol.isEmpty()) {
            String detectedSymbol = detectSymbol(userText);
            if (!detectedSymbol.isEmpty()) {
                selectedSymbol = detectedSymbol + "USDT"; // Simplified assumption
                price = exchangeService.getCurrentPrice(selectedSymbol, selectedExchange);
                if (price == 0) {
                    return "Sorry, I couldn't fetch the price for " + selectedSymbol + ". Please try a different symbol.";
                }
                return "The current price of " + detectedSymbol.toUpperCase() + " is " + price + " USDT. How much would you like to buy?";
            } else {
                return "Please specify a valid symbol (e.g., BTC, ETH).";
            }
        }

        // Quantity Selection
        if (selectedSymbol.length() > 0 && quantity == 0) {
            double detectedQty = extractQuantity(userText);
            if (detectedQty > 0) {
                quantity = detectedQty;
                awaitingConfirmation = true;
                return "Confirming: Buy " + quantity + " of " + selectedSymbol + " at " + price + " USDT on " + selectedExchange + ". Say 'yes' to confirm.";
            } else {
                return "Please provide the quantity you want to buy.";
            }
        }

        // Confirmation
        if (awaitingConfirmation && userText.contains("yes")) {
            awaitingConfirmation = false;
            String summary = "\n\u2705 Order Summary:\n" +
                    "Exchange: " + selectedExchange + "\n" +
                    "Symbol: " + selectedSymbol + "\n" +
                    "Quantity: " + quantity + "\n" +
                    "Price: " + price + " USDT\n" +
                    "\nNo actual trade has been placed. Thank you.";
            resetState();
            return summary;
        }

        return "Sorry, I didn't understand. Could you please repeat?";
    }

    private String detectSymbol(String text) {
        String[] knownSymbols = {"btc", "eth", "sol", "xrp", "ltc"};
        for (String sym : knownSymbols) {
            if (text.contains(sym)) {
                return sym.toUpperCase();
            }
        }
        return "";
    }

    private double extractQuantity(String text) {
        Pattern pattern = Pattern.compile("\\b(\\d+(?:\\.\\d+)?)\\b");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        }
        return 0;
    }

    private void resetState() {
        selectedExchange = "";
        selectedSymbol = "";
        quantity = 0;
        price = 0;
        awaitingConfirmation = false;
    }
}
