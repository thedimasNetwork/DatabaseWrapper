package stellar.database;

import java.io.IOException;
import java.util.Properties;
public class Version {
    public final String type;
    public final String number;
    public final String hash;

    public Version() {
        Properties properties = new Properties();
        try {
            properties.load(Version.class.getClassLoader().getResourceAsStream("version.properties"));
        } catch (IOException ignored) {
        } finally {
            type = properties.containsKey("type") ? properties.getProperty("type") : "unknown";
            number = properties.containsKey("number") ? properties.getProperty("number") : "0.0.0";
            hash = properties.getProperty("hash");
        }
    }

    public Version(String type, String number, String hash) {
        this.type = type;
        this.number = number;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", number, type, hash);
    }
}
