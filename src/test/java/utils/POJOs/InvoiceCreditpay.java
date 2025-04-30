package utils.POJOs;

public class InvoiceCreditpay {
    private int policy_id;
    private String received_on;
    private String payment_source;
    private double amount;
    private String payer_name;
    private String payer_email;
    private String remarks;
    private long epay_transaction_id;

    public InvoiceCreditpay(int policy_id, String received_on, String payment_source, double amount,
                         String payer_name, String payer_email, String remarks, long epay_transaction_id) {
        this.policy_id = policy_id;
        this.received_on = received_on;
        this.payment_source = payment_source;
        this.amount = amount;
        this.payer_name = payer_name;
        this.payer_email = payer_email;
        this.remarks = remarks;
        this.epay_transaction_id = epay_transaction_id;
    }
    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public String getPayer_email() {
        return payer_email;
    }

    public void setPayer_email(String payer_email) {
        this.payer_email = payer_email;
    }

    public long getEpay_transaction_id() {
        return epay_transaction_id;
    }

    public void setEpay_transaction_id(long epay_transaction_id) {
        this.epay_transaction_id = epay_transaction_id;
    }
}
