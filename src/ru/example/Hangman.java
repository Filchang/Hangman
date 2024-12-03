package ru.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Hangman {
    private final String word = getRandomWord();
    private List<Character> guessedLetters = new ArrayList<>();
    private char letter;

    // Метод для получения случайного слова
    public String getRandomWord() {
        String word = null;
        Random myRand = new Random();
        try {
            word = Files.lines(Paths.get("Dictionary.txt"))
                    .map(String::trim)
                    .filter(w -> w.length() > 3)
                    .collect(Collectors.collectingAndThen(Collectors.toList(),
                            list -> {
                                if (list.isEmpty()) {
                                    return "Словарь пуст";
                                }
                                return list.get(myRand.nextInt(list.size()));
                            }));

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return word;
    }

    // Метод для получения корректного ввода буквы
    public char getValidLetterInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите букву:");
            String input = scanner.nextLine().trim();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0)) || input.matches(".*[a-zA-Z].*")) {
                System.out.println("Ошибка! Пожалуйста, введите одну русскую букву.");
                continue;
            }

            char letter = Character.toLowerCase(input.charAt(0));

            if (guessedLetters.contains(letter)) {
                System.out.println("Эта буква уже была введена. Попробуйте другую.");
                continue;
            }

            return letter;
        }
    }

    public boolean checkLetterOccurancy(){
        boolean contains = false;
        for (Character c : word.toCharArray()) {
            if (c.equals(letter)) {
                contains = true;
                guessedLetters.add(letter);
                break;
            }
        }

        return contains;
    }


    public String outputProgressWord() {
            StringBuilder progress = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (guessedLetters.contains(c)) {
                    progress.append(c);
                } else {
                    progress.append('_');
                }
            }
            return progress.toString();

    }


    public boolean isGameWon() {
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public void startGame() {
        int incorrectGuesses = 0;

        while (true) {

            letter = getValidLetterInput();


            if (checkLetterOccurancy()) {
                System.out.println("Буква угадана!");
            } else {
                incorrectGuesses++;
                DrawHangman drawHangman = new DrawHangman();
                drawHangman.printHangman(incorrectGuesses);
                System.out.println("Такой буквы нет.");
                System.out.println("Количество ошибок:" + " " + incorrectGuesses);
            }
            String progress = outputProgressWord();
            System.out.println(progress);

            if (incorrectGuesses  == 7) {
                System.out.println("Вы проиграли.\n Загаданное слово: " + word);
                break;
            }

            if (isGameWon()) {
                System.out.println("Поздравляю, вы выиграли! Слово: " + word);
                break;
            }




        }
    }
}




