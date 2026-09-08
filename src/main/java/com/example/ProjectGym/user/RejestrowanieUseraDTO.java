package com.example.ProjectGym.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RejestrowanieUseraDTO(
        @NotBlank(message = "Imie jest wymagane")
        String imie,
        @NotBlank(message = "Nazwisko jest wymagane")
        String nazwisko,
        @NotBlank(message = "telefon jest wymagany")
        @Size(min = 9, max = 9, message = "Telefon musi mieć dokładnie 9 znakow")
        String telefon,
        @NotBlank(message = "email jest wymagany")
        @Email(message = "nieprawidłowy emial")
        String email,
        @NotBlank(message = "Hasło jest wymagane")
        @Size(min = 8, message = "Hasło musi mieć przynajmniej 8 znaków")
        String haslo
) {
}
