package com.excel.reader.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RestController
@RequestMapping("/feign-demo")
public class FeignDemoController {

    private static final String STOCK_API_KEY = "9876";
    private static final String WEATHER_API_KEY = "1234";

    private final Random random = new Random();

    @GetMapping("/weather-client/weather")
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestParam("q") String city,
            @RequestParam("units") String units) {

        if (!WEATHER_API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        double temperature = round(random.nextDouble() * 40, 1); // 0.0 to 40.0
        int humidity = random.nextInt(101); // 0 to 100

        WeatherResponse weather = new WeatherResponse(
                new WeatherResponse.Main(temperature, humidity),
                city
        );

        return ResponseEntity.ok(weather);
    }

    @GetMapping("/stock-client/stock")
    public ResponseEntity<StockResponse> getStockPrice(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestParam String symbol) {

        if (!STOCK_API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        double price = round(50 + (500 - 50) * random.nextDouble(), 2); // $50 to $500
        StockResponse.GlobalQuote quote = new StockResponse.GlobalQuote(String.valueOf(price), symbol);
        StockResponse response = new StockResponse(quote);

        return ResponseEntity.ok(response);
    }
    private double round(double value, int places) {
        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}



@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class StockResponse {
    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GlobalQuote {
        @JsonProperty("05. price")
        private String price;
        @JsonProperty("01. symbol")
        private String symbol;
    }
}


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class WeatherResponse {
    private Main main;
    private String name;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {
        private double temp;
        private int humidity;
    }

}