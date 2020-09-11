package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("I'm a bot. I understand the commands: date, day, month, year, time, hour, minutes, seconds.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            if (message != null) {
                ConsoleHelper.writeMessage(message);
                SimpleDateFormat format = null;
                if (message.contains(": ")) {
                    String[] massiv = message.split(": ");
                    if (massiv.length == 2 && massiv[1] != null) {
                        String name = massiv[0];
                        String text = massiv[1];
                        switch (text) {
                            case "date":
                                format = new SimpleDateFormat("d.MM.YYYY");
                                break;
                            case "day":
                                format = new SimpleDateFormat("d");
                                break;
                            case "mounth":
                                format = new SimpleDateFormat("MMMM");
                                break;
                            case "year":
                                format = new SimpleDateFormat("YYYY");
                                break;
                            case "time":
                                format = new SimpleDateFormat("H:mm:ss");
                                break;
                            case "hour":
                                format = new SimpleDateFormat("H");
                                break;
                            case "minutes":
                                format = new SimpleDateFormat("m");
                                break;
                            case "seconds":
                                format = new SimpleDateFormat("s");
                                break;

                        }
                        if (format != null) {
                            sendTextMessage(String.format("Information for %s: %s", name, format.format(Calendar.getInstance().getTime())));
                        }
                    }
                }
            }
        }
    }


    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        String botName = "date_bot_" + ((int) (Math.random() * 100));
        return botName;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        System.out.println(botClient.getUserName());
        botClient.run();
    }
}
