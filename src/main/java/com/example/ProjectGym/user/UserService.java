package com.example.ProjectGym.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse zarejestruj(RejestrowanieUseraDTO request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Użytkownik o tym adresie email już istnieje");
        }
        if (userRepository.existsByTelefon(request.telefon())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Użytkownik o tym telefonie już istnieje");
        }

        User user = User.builder()
                .imie(request.imie())
                .nazwisko(request.nazwisko())
                .email(request.email())
                .telefon(request.telefon())
                .haslo(passwordEncoder.encode(request.haslo()))
                .rola(Rola.USER)
                .build();

        User zapisany = userRepository.save(user);
        return mapToResponse(zapisany);
    }

    @Transactional(readOnly = true)
    public UserResponse pobierzProfil(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono użytkownika"));

        return mapToResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse zaloguj(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nieprawidłowy e-mail lub hasło"));

        if (!passwordEncoder.matches(request.haslo(), user.getHaslo())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nieprawidłowy e-mail lub hasło");
        }

        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getImie(),
                user.getNazwisko(),
                user.getEmail(),
                user.getTelefon(),
                user.getRola(),
                user.getDataUtworzenia()
        );
    }
}