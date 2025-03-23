package com.example.demo.Controller;

import lombok.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
@Tag(name = "Weather API", description = "Obtenir des informations météo")
public class WeatherController {


    private static final String API_KEY = "f60c00e8d4925eb1ba31d07dbc3416f6";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={apiKey}&units=metric";

    @GetMapping("/{city}")
    @Operation(summary = "Récupère la météo d'une ville") //Décrit une route spéciique de mn API
    public Map<String, Object> getWeather(@PathVariable String city) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(WEATHER_API_URL, Map.class, city, API_KEY);
    }
}
