package com.example.demo.Service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExchangeService {

    public double getCurrentPrice(String symbol, String exchange) {
        try {
            switch (exchange.toLowerCase()) {
                case "binance":
                    return fetchFromBinance(symbol);
                case "okx":
                    return fetchFromOkx(symbol);
                case "bybit":
                    return fetchFromBybit(symbol);
                case "deribit":
                    return fetchFromDeribit(symbol);
                default:
                    return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private double fetchFromBinance(String symbol) throws Exception {
        URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=" + symbol);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        return json.getDouble("price");
    }

    private double fetchFromOkx(String symbol) throws Exception {
        // OKX uses a different format, e.g., BTC-USDT
        symbol = symbol.replace("USDT", "-USDT");
        URL url = new URL("https://www.okx.com/api/v5/market/ticker?instId=" + symbol);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        return json.getJSONArray("data").getJSONObject(0).getDouble("last");
    }

    private double fetchFromBybit(String symbol) throws Exception {
        URL url = new URL("https://api.bybit.com/v2/public/tickers?symbol=" + symbol);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        return json.getJSONArray("result").getJSONObject(0).getDouble("last_price");
    }

    private double fetchFromDeribit(String symbol) throws Exception {
        // Deribit uses lowercase and currency format like btc_usdt
        symbol = symbol.toLowerCase().replace("USDT", "usdt");
        URL url = new URL("https://www.deribit.com/api/v2/public/get_ticker?instrument_name=" + symbol);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        return json.getJSONObject("result").getDouble("last_price");
    }
}
