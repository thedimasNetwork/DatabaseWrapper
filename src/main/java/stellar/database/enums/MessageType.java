package stellar.database.enums;

public enum MessageType {
    chat("public"),
    direct("private"),
    team("team"),
    admin("admin");

    private final String displayName;
    MessageType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
