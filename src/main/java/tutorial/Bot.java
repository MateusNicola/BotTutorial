package tutorial;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private Map<Long, Double> userSums = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "TutorialBot";
    }

    @Override
    public String getBotToken() {
        return "6974849109:AAGbt5yaAq1ChEc_IxF3BaJ4FZLVa2EToXA";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();

        (msg.getText().equals("/total");

        if (msg.getText().equals("/start")) {
            String msgBoasVindas = "Bem-vindo ao bot calculadora, nele você envia valores e eu vou somando.";
            sendText(user.getId(), msgBoasVindas);
            userSums.put(user.getId(), 0.0); // Inicializa a soma para o usuário
        } else if (msg.getText().equals("/total")) {
            Double total = userSums.getOrDefault(user.getId(), 0.0);
            sendText(user.getId(), "O total acumulado é: " + total);
        } else {
            if (isNumeric(msg.getText())) {
                double valor = Double.parseDouble(msg.getText());
                userSums.put(user.getId(), userSums.getOrDefault(user.getId(), 0.0) + valor);
                sendText(user.getId(), "Valor adicionado. O total atual é: " + userSums.get(user.getId()));
            } else {
                sendText(user.getId(), "Não é um número válido.");
            }
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

}