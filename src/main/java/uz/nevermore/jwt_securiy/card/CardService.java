package uz.nevermore.jwt_securiy.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nevermore.jwt_securiy.user.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final UserRepository userRepository;

    private final CardRepository cardRepository;

    public Card addCard(AddCardRequest addCardRequest) {

        var card=Card.builder()
                .user(userRepository.findById(addCardRequest.getUserId()).get())
                .active(true)
                .balance(0)
                .number(getCardNumber())
                .expireDate(getExpireDate())
                .build();

        return cardRepository.save(card);
    }

    private String getExpireDate() {
        Calendar expiredTime=Calendar.getInstance();
        expiredTime.add(Calendar.YEAR,4);
        Date expiredDate=expiredTime.getTime();
        SimpleDateFormat formatter=new SimpleDateFormat("MM/yy");
        return formatter.format(expiredDate);
    }

    private String getCardNumber() {
        String companyNumber = "9860";
        int min=0;
        int max=9;
        String twelve="";
        String cardNumber="";
        while (twelve.length()<13){
        int randomNumber= (int)(Math.random()*(max-min+1)+min);
       twelve+=randomNumber;
       cardNumber=twelve;
        }
        return companyNumber+cardNumber;
    }

    public Card editCard(Long id, EditCardRequest editCardRequest) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()){
            Card editingCard = cardOptional.get();
            editingCard.setBalance(editCardRequest.getBalance());
//            editingCard.setNumber(editCardRequest.getNumber());
//            editingCard.setActive(editCardRequest.isActive());
//            editingCard.setExpireDate(editCardRequest.getExpireDate());

    return cardRepository.save(editingCard);

    }
        return null;
}
}
