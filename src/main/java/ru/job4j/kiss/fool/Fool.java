package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        Fool fool = new Fool();
        System.out.println("Игра FizzBuzz.");
        Integer startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            if (startAt % 3 == 0 && startAt % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (startAt % 3 == 0) {
                System.out.println("Fizz");
            } else if (startAt % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(startAt);
            }
            startAt++;
            String answer = input.nextLine();

            if (startAt % 3 == 0 && startAt % 5 == 0) {
                startAt = fool.getStartAt(startAt, answer, "FizzBuzz");
            } else if (startAt % 3 == 0) {
                startAt = fool.getStartAt(startAt, answer, "Fizz");
            } else if (startAt % 5 == 0) {
                startAt = fool.getStartAt(startAt, answer, "Buzz");
            } else {
                startAt = fool.getStartAt(startAt, answer, String.valueOf(startAt));
            }
            startAt++;
        }
    }

    public int getStartAt(int startAt, String answer, String word) {
        if (!word.equals(answer)) {
            System.out.println("Ошибка. Начинай снова.");
            startAt = 0;
        } else {
            if (startAt % 2 != 0) {
                System.out.println(answer);
            }
        }
        return startAt;
    }
}
