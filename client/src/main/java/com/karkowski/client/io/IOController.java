package com.karkowski.client.io;

import java.util.Scanner;

/**
 * @author Michal on 13.05.2021
 */

public class IOController {
    private final static Scanner scanner = new Scanner(System.in);

    public static String getLine() {
        return scanner.nextLine();
    }
    public static void drawBoard(final char[][] board) {
        for (final char[] row : board) {
            for (final char sign : row) {
                System.out.print(sign);
            }
            System.out.println();
        }
    }

    public static String getKeyboardInput() {
        do {
            if (scanner.hasNext()) {
                return scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Enter a valid value");
            }
        } while (true);
    }


}
