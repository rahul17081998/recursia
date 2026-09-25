package com.demo.enumExample;

public class Run {
    public static void main(String[] args) {
        Day today = Day.SATURDAY;
        System.out.println(today.name());
        System.out.println("Normal temp of saturday is: "+ Day.SATURDAY.getNormalizedTemp()+" weather is : "+Day.SATURDAY.getWeather());


        Day[] allDays = Day.values();
        for (int i=0; i<allDays.length; i++){
            System.out.println(allDays[i]);
        }
    }
}
