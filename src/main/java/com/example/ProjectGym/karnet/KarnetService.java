package com.example.ProjectGym.karnet;

import com.example.ProjectGym.user.User;
import com.example.ProjectGym.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class KarnetService {
    private final KarnetRepository karnetRepository;
    private final UserRepository userRepository;

    @Transactional
    public KarnetResponse kupKarnet(KupKarnetRequest request){
        User user = userRepository.findById(request.userId()).orElseThrow(()->new IllegalArgumentException("nie znaleziono użytkownika z takim Id"));

        LocalDate dzis = LocalDate.now();
        RodzajKarnetu rodzajKarnetu= request.rodzajKarnetu();

        LocalDate dataOd = karnetRepository.findAktywnyKarnet(user,dzis).map(aktywny ->aktywny.getDataDo().plusDays(1)).orElse(dzis);

        LocalDate dataDo = dataOd.plusDays(rodzajKarnetu.getDniWaznosci());

        Karnet karnet = Karnet.builder()
                .user(user)
                .rodzajKarnetu(rodzajKarnetu)
                .cena(rodzajKarnetu.getDomyslnaCena())
                .dataOd(dataOd)
                .dataDo(dataDo)
                .build();
        Karnet zapisany = karnetRepository.save(karnet);
        return mapToResponse(zapisany);
    }
    @Transactional(readOnly = true)
    public List<KarnetResponse> pobierzHistorieKarnetow(UUID userId){
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Nie znaleziono użytkownika o taki id"));
        return karnetRepository.findByUserOrderByDataZakupuDesc(user).stream().map(this::mapToResponse).toList();
    }
    private KarnetResponse mapToResponse(Karnet karnet){
        return new KarnetResponse(
                karnet.getId(),
                karnet.getUser().getId(),
                karnet.getRodzajKarnetu(),
                karnet.getCena(),
                karnet.getDataOd(),
                karnet.getDataDo(),
                karnet.getStatus()
        );

    }
}
