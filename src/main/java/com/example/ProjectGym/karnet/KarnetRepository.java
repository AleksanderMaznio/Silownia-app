package com.example.ProjectGym.karnet;

import com.example.ProjectGym.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KarnetRepository extends JpaRepository<Karnet, UUID> {

    // Historia karnetów użytkownika od najnowszego
    List<Karnet> findByUserOrderByDataZakupuDesc(User user);

    // Sprawdzenie, czy user ma aktywny karnet na dany dzień
    @Query("SELECT COUNT(k) > 0 FROM Karnet k " +
            "WHERE k.user = :user AND :dzien BETWEEN k.dataOd AND k.dataDo")
    boolean maAktywnyKarnet(@Param("user") User user, @Param("dzien") LocalDate dzien);

    @Query("SELECT k FROM Karnet k WHERE k.user = :user AND :dzien BETWEEN k.dataOd AND k.dataDo")
    Optional<Karnet> findAktywnyKarnet(@Param("user") User user, @Param("dzien") LocalDate dzien);
}