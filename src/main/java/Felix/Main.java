package Felix;

import Felix.Commands.Ping;
import Felix.soup.Token;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

public class Main {

    //public static String Prefix = "<";

    public static void main(String[] args)
    {
        DiscordApi api = new DiscordApiBuilder().setToken(Token.token).addIntents(Intent.MESSAGE_CONTENT).login().join();


        api.addListener(new Ping());

        System.out.println("Estoy conectado!");

    }

}
