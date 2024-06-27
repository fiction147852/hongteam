package com.son.app.payment.service;

import lombok.Data;

@Data
public class PaymentVO {
    private Integer parentNumber;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String birth;
}
