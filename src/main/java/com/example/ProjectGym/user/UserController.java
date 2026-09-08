package com.example.ProjectGym.user;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/rejestracja")
    public ResponseEntity<UserResponse> zarejestruj(@Valid @RequestBody RejestrowanieUseraDTO request){
        UserResponse userResponse = userService.zarejestruj(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> pobierzProfil(@PathVariable UUID id){
        return ResponseEntity.ok(userService.pobierzProfil(id));
    }
    @PostMapping("/login")
    private ResponseEntity<UserResponse> zaloguj(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(userService.zaloguj(request));
    }
}
