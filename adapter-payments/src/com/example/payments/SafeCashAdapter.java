package com.example.payments;


public class SafeCashAdapter implements PaymentGateway {
    SafeCashClient safeCashClient;

    SafeCashAdapter(SafeCashClient safeCashClient) {
        this.safeCashClient = safeCashClient;
    }
    @Override
    public String charge(String customerId, int amountCents) {
        SafeCashPayment safeCashPayment = safeCashClient.createPayment(amountCents, customerId);
        return safeCashPayment.confirm();
    }  

}
