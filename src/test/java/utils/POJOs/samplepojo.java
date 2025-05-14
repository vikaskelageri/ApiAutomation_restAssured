package utils.POJOs;

public class samplepojo {
    private int policy_id;


    private String received_on;
    private String payment_source;

    public samplepojo(int policy_id, String received_on, String payment_source) {
        this.policy_id = policy_id;
        this.received_on = received_on;
        this.payment_source = payment_source;
    }

    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
    }

    public String getReceived_on() {
        return received_on;
    }

    public void setReceived_on(String received_on) {
        this.received_on = received_on;
    }

    public String getPayment_source() {
        return payment_source;
    }

    public void setPayment_source(String payment_source) {
        this.payment_source = payment_source;
    }
}
