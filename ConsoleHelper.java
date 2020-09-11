package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() {
        while (true) {
            try {
                String string = reader.readLine();
                return string;
            } catch (IOException e) {
                writeMessage("An error occurred while trying to enter text. try again.");
            }
        }
    }

    public static int readInt(){
        while (true) {
            try {
                String input = readString();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                writeMessage("An error occurred while trying to enter a number. try again.");
            }
        }
    }


}
