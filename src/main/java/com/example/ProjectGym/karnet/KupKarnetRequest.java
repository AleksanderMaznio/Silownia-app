package com.example.ProjectGym.karnet;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record KupKarnetRequest(
        @NotNull(message = "Id użytkownika jest wymagane")
        UUID userId,
        @NotNull(message = "Wybierz rodzaj karnetu")
        RodzajKarnetu rodzajKarnetu
) {
}
