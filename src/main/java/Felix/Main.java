package Felix;

import ComandosUsuarios.Avatar;
import ComandosUsuarios.UserInfo;
import Listener.Ping;
import Listener.ToxicResponse;
import Managment.Token;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

public class Main {

    //public static String Prefix = "<";

    public static void main(String[] args)
    {
        DiscordApi api = new DiscordApiBuilder().setToken(new Token().getToken()).addIntents(Intent.MESSAGE_CONTENT).login().join();
        api.addListener(new Ping());

        ToxicResponse toxic = new ToxicResponse(api);
        UserInfo info = new UserInfo(api);

        Avatar avatar = new Avatar(api);

        System.out.println("Estoy conectado!");

    }

}
