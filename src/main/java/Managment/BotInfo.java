package Managment;

import lombok.Getter;
import lombok.Setter;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BotInfo {
    @Getter private static String ownerId;
    @Getter private static String botId;
    @Getter private static String botName;
    @Getter private static String botImageStr;
    @Getter private static String botActivity;
    @Getter private static Icon botImage;
    @Getter private static String botInvite;
    @Getter private static String botRepo;
    @Getter private static String tenorApiKey;
    @Getter @Setter private static int serverCount;
    @Getter @Setter private static int userCount;

    public BotInfo(DiscordApi api){
        ownerId = String.valueOf(api.getOwnerId());
        botId = api.getYourself().getIdAsString();
        botName = api.getYourself().getName();
        botImageStr = api.getYourself().getAvatar().getUrl().toString();
        botImage = api.getYourself().getAvatar();
        serverCount = api.getServers().size();

       // tenorApiKey = Files.readAllLines(Paths.get());
    }
}
