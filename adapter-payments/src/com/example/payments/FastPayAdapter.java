// import com.example.payments.FastPayClient;
// import com.example.payments.PaymentGateway;
package com.example.payments;


public class FastPayAdapter implements PaymentGateway {
    FastPayClient fastPayClient;

    FastPayAdapter(FastPayClient fastPayClient) {
        this.fastPayClient = fastPayClient;
    }

    @Override
    public String charge(String customerId, int amountCents) {
        return fastPayClient.payNow(customerId, amountCents);
    }
}
