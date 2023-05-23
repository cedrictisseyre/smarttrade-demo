package com.smarttrade.demo.infrastructure;

import javax.inject.Named;

@Named
public class SmsSender {
    public void send(String phoneNumber, String message){
        System.out.printf("SMS sent to %s with message %s%n", phoneNumber, message);
    }
}
