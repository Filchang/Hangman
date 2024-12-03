package ru.example;

import java.util.Scanner;

public class StartStop{

    public void Menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Добро пожаловать в игру 'Виселица'!");
            System.out.println("1. Начать новую игру");
            System.out.println("2. Выйти из приложения");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    startNewGame();
                    break;
                case "2":
                    System.out.println("Спасибо за игру! До встречи.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    private void startNewGame() {
        Hangman hangman = new Hangman(); // Инициализация игры
        hangman.startGame();
    }
}