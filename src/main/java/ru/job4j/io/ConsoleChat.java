package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> listAnswers = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean status = true;
        boolean run = true;
        while (run) {
            String answerBot = "";
            System.out.print("Выбрать: ");
            String answer = input.nextLine();
            if (!answer.equals(OUT)) {
                System.out.println("Пользователь ввел: " + answer);
                if (answer.equals(STOP)) {
                    status = !status;
                    answerBot = "Пойду отдохну !";
                }
                if (answer.equals(CONTINUE)) {
                    status = !status;
                    answerBot = " Я вернулся !";
                }
                if (status) {
                    int index = (int) (Math.random() * listAnswers.size());
                    answerBot = listAnswers.get(index);
                }
                System.out.println("Бот ответил: " + answerBot);
                log.add("Пользователь ввел: " + answer + " " + "Бот ответил: " + answerBot);
            } else {
                run = false;
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(botAnswers, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./data/text.txt";
        String botAnswers = "./data/bot_answers.txt";
        ConsoleChat consoleChat = new ConsoleChat(path, botAnswers);
        consoleChat.run();
    }
}