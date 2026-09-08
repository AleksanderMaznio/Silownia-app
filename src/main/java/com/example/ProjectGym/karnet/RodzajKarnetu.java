package com.example.ProjectGym.karnet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Getter
@RequiredArgsConstructor
public enum RodzajKarnetu {
    MIESIECZNY(new BigDecimal("150.00"),30),
    ROCZNY(new BigDecimal("1600.00"),365),
    STUDENCKI(new BigDecimal("130.00"),30);

    private final BigDecimal domyslnaCena;
    private final int dniWaznosci;
}
