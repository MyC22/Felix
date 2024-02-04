package Commands;

import lombok.Getter;
import lombok.Setter;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Command {

    private DiscordApi api;
    private TextChannel canal;
    private Message mensaje;
    private MessageAuthor mensajeAutor;
    private User usuario;
    private Server servidor;
    private String key;
    private String comando;
    private List<String> arg;

    public Command(DiscordApi api){
        this.api = api;

        api.addMessageCreateListener(event -> {
           mensaje = event.getMessage();
           canal = event.getChannel();
           servidor = event.getServer().get();
           mensajeAutor = event.getMessageAuthor();
           key = mensaje.getContent().split("\\s")[0];
            arg = new ArrayList<>();
        });
    }

    public boolean obCommand(DiscordApi api, Message mensaje, Server servidor , ArrayList arg){
        comando = Thread.currentThread().getStackTrace()[3].getClassName().split("\\.")[1];
        api.addMessageCreateListener(event -> {
            setServidor(event.getServer().get());
        });

        for (String argumento: mensaje.getContent().split("\\s")) {
            if (argumento.equals(key)){
                continue;
            }
            arg.add(argumento);
        }

        return true;
    }

    public boolean obCommand(){
        return obCommand(this.api, this.mensaje, this.servidor, (ArrayList) this.arg);
    }


}
