package com.demo.enumExample;

public enum Day {
    MONDAY(26, "324"),
    TUESDAY(21, "321"),
    WEDNESDAY(22, "768"),
    THRUSDAY(23, "442"),
    FRIDAY(24, "982"),
    SATURDAY(21.7, "873"),
    SUNDAY(23.1, "741");

    private final double temp;
    private final String weather;

    Day(double temp, String weather){
        this.temp=temp;
        this.weather=weather;
    }

    // Method on enum
    double getNormalizedTemp() {
        return temp-2;
    }

    String getWeather(){
        return weather;
    }

}


