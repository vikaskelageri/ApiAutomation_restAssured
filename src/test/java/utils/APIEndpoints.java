package utils;

public class APIEndpoints {

    public static final String BASE="/api/v1";

    public static String getAgentList() {
        return BASE + "/agent/list";
    }

    public static String createAgent() {
        return BASE + "/user/signup";
    }

    public static String updateAgent(int id) {
        return BASE + "/user/" + id + "/manage";
    }

    public static String getAgentById(int id) {
        return BASE + "/user/" + id + "/manage";
    }

    public static String deleteAgent(int id) {
        return BASE + "/user/" + id + "/manage";
    }
}
