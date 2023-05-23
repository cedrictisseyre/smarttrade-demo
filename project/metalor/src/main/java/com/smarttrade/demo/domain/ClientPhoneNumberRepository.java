package com.smarttrade.demo.domain;


import io.vavr.control.Option;

public interface ClientPhoneNumberRepository {
    Option<ClientPhoneNumber> find(String clientName);
}
