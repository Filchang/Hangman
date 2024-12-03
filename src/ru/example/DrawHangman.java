package ru.example;

public class DrawHangman {

    private static final String[] STAGES = {
            """
         """,
         """
         +---+
         |   |
             |
             |
             |
             |
        =========
        """,
            """
         +---+
         |   |
         O   |
             |
             |
             |
        =========
        """,
            """
         +---+
         |   |
         O   |
         |   |
             |
             |
        =========
        """,
            """
         +---+
         |   |
         O   |
        /|   |
             |
             |
        =========
        """,
            """
         +---+
         |   |
         O   |
        /|\\  |
             |
             |
        =========
        """,
            """
         +---+
         |   |
         O   |
        /|\\  |
        /    |
             |
        =========
        """,
            """
         +---+
         |   |
         O   |
        /|\\  |
        / \\  |
             |
        =========
        """
    };


    public void printHangman(int incorrectGuesses) {
        if (incorrectGuesses < 0 || incorrectGuesses >= STAGES.length) {
            incorrectGuesses = Math.min(incorrectGuesses, STAGES.length - 1);
        }
        System.out.println(STAGES[incorrectGuesses]);
    }
}
