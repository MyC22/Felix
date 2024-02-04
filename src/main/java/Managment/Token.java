package Managment;

import lombok.Getter;
import lombok.SneakyThrows;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Token {

    @Getter private static String token =" ";

    public Token(){
        setToken();
    }

    @SneakyThrows
    static public void setToken(){
        token = Files.readAllLines(Paths.get("C:\\Users\\CK\\Documents\\MyCkel\\Data\\Hidden\\Token.txt")).get(0);
    }
}
