package com.example.ProjectGym.karnet;

import com.example.ProjectGym.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "karnety")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Karnet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal cena;

    @Column(name = "data_od",nullable = false)
    private LocalDate dataOd;

    @Column(name = "data_do", nullable = false)
    private LocalDate dataDo;

    @Enumerated(EnumType.STRING)
    @Column(name = "rodzaj", nullable = false)
    private RodzajKarnetu rodzajKarnetu;
    @Column(name = "data_zakupu",nullable = false,updatable = false)
    private LocalDateTime dataZakupu;

    @PrePersist
    protected void onCreate(){
        this.dataZakupu = LocalDateTime.now();
    }
    public boolean isAktywny(){
        LocalDate now = LocalDate.now();
        return !now.isBefore(dataOd) && now.isAfter(dataDo) == false;
    }
    public StatusKarnetu getStatus(){
        LocalDate dzis = LocalDate.now();
        if (dzis.isBefore(dataOd)){
            return StatusKarnetu.OCZEKUJACY;
        }
        if (dzis.isAfter(dataDo)){
            return StatusKarnetu.WYGASLY;
        }
        return StatusKarnetu.AKTYWNY;
    }
}
