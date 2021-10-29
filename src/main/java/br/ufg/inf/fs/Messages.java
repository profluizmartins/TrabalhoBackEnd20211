package br.ufg.inf.fs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Messages {
    public static Properties props = load();

    private static Properties load(){
        Properties props = new Properties();
        try{
            FileInputStream fs = new FileInputStream("src/main/resources/messages.properties");
            props.load(fs);
        } catch (IOException e){
            e.printStackTrace();
        }
        return props;
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
