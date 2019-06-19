package com.fzzz.interview.t27;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-18
 * update:
 */
public class T27 {

    public static void changeString(String weather) {
        weather = "sunny";
    }

    public static void changeArray(String[] rainyDays) {
        rainyDays[1] = "Sunday";
    }

    public static void changeObject(ForeCast foreCast) {
        foreCast.temperature = 35;
    }

    public static void main(String[] args) {
        String weather = "rainy";
        changeString(weather);
        System.out.println("the weather is: " + weather);

        String[] rainyDays = {"Monday", "Friday"};
        changeArray(rainyDays);
        System.out.println("the rainy days were on: " + rainyDays[0] + " and " + rainyDays[1]);

        ForeCast foreCast = new ForeCast();
        foreCast.pressure = 700;
        foreCast.temperature = 20;
        changeObject(foreCast);
        System.out.println("the temprature is: " + foreCast.temperature);
    }
}

class ForeCast {
    public int temperature;
    public int pressure;
}
