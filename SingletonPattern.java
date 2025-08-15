// SingletonPattern.java
class Config {
    private static Config instance;
    private String env = "prod";

    private Config() {}

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String get(String key) {
        if (key.equals("env")) return env;
        return null;
    }

    public void set(String key, String value) {
        if (key.equals("env")) env = value;
    }
}

public class SingletonPattern {
    public static void main(String[] args) {
        Config a = Config.getInstance();
        Config b = Config.getInstance();
        System.out.println(a == b); // true
        a.set("env", "dev");
        System.out.println(b.get("env")); // dev
    }
}
