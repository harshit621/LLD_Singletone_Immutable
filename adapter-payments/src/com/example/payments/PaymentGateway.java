package com.example.payments;

public interface PaymentGateway {
    public String charge(String customerId, int amountCents);
}
