package tutorial;

import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private final Map<Long, Double> userSums = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "Controle Financeiro Bot";
    }

    @Override
    public String getBotToken() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();

        if (msg.getText().equals("/start")) {
            String msgInicial = "Bem-vindo ao Controle Financeiro BOT.";
            sendText(user.getId(), msgInicial);
            userSums.put(user.getId(), 0.0);
        } else if (msg.getText().equals("/total")) {
            Double total = userSums.getOrDefault(user.getId(), 0.0);
            sendText(user.getId(), "O total acumulado é: " + String.format("%.2f", total));
        } else if (msg.getText().equals("/limpar")) {
            userSums.put(user.getId(), 0.0);
            sendText(user.getId(), "Os valores acumulados foram limpos.");
        } else {
            String texto = msg.getText().replace(",", ".");
            if (isNumeric(texto)) {
                double valor = Double.parseDouble(texto);
                userSums.put(user.getId(), userSums.getOrDefault(user.getId(), 0.0) + valor);
                sendText(user.getId(), "Valor adicionado. O total atual é: " + String.format("%.2f", userSums.get(user.getId())));
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