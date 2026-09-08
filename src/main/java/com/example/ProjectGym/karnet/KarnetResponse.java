package com.example.ProjectGym.karnet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record KarnetResponse(
        UUID id,
        UUID userId,
        RodzajKarnetu rodzajKarnetu,
        BigDecimal cena,
        LocalDate dataOd,
        LocalDate dataDo,
        StatusKarnetu statusKarnetu

) {
}
