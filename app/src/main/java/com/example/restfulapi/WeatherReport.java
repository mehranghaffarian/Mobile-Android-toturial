package com.example.restfulapi;

public class WeatherReport {
        private int id;
           private String weather_state_name;
            private String weather_state_abbr;
            private String wind_direction_compass;
            private String created;
            private String applicable_date;
            private double min_temp;
            private double max_temp;
            private double the_temp;
            private double wind_speed;
            private double wind_direction;
            private int air_pressure;
            private int humidity;
            private double visibility;
            private int predictability;

    public WeatherReport(int id, String weather_state_name, String weather_state_abbr, String wind_direction_compass, String created, String applicable_date, double min_temp, double max_temp, double the_temp, double wind_speed, double wind_direction, int air_pressure, int humidity, double visibility, int predictability) {
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.created = created;
        this.applicable_date = applicable_date;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.the_temp = the_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    public WeatherReport() {
    }

    public int getId() {
        return id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public String getCreated() {
        return created;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public double getMin_temp() {
        return min_temp;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public double getThe_temp() {
        return the_temp;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_direction() {
        return wind_direction;
    }

    public int getAir_pressure() {
        return air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public int getPredictability() {
        return predictability;
    }
}
