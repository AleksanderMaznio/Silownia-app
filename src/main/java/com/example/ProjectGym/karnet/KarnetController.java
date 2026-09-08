package com.example.ProjectGym.karnet;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/karnety")
public class KarnetController {
    private final KarnetService karnetService;

    @PostMapping("/kup")
    public ResponseEntity<KarnetResponse> kupKarnet(@Valid @RequestBody KupKarnetRequest request){
        KarnetResponse response = karnetService.kupKarnet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/uzytkownik/{userId}")
    public ResponseEntity<List<KarnetResponse>> pobierzHistorie(@PathVariable UUID userId){
        return ResponseEntity.ok(karnetService.pobierzHistorieKarnetow(userId));
    }
}
