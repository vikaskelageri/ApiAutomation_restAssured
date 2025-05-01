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

    public static String getQuoteList() {
        return BASE + "/quote/manage";
    }

    public static String getLocation() {
        return BASE + "/quote/get_tiv";
    }

    public static String createCreditPay(int id) {
        return BASE + "/invoices/policy/"+id+"/mark_as_paid";
    }

    public static String login() {

        return BASE + "/user/login";
    }
    public static String deleteQuote(int id) {
        return BASE + "/quote/" + id + "/update";
    }
    public static String deleteInvoice(int id) {
        return BASE + "/quote/" + id + "/update";
    }



}
