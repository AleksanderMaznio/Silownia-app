package com.example.ProjectGym.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String imie,
        String nazwisko,
        String email,
        String telefon,
        Rola rola,
        LocalDateTime dataUtworzenia
        ) { }
