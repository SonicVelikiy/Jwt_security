package uz.nevermore.jwt_securiy.transfer;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nevermore.jwt_securiy.card.Card;
import uz.nevermore.jwt_securiy.card.CardRepository;
import uz.nevermore.jwt_securiy.config.JwtService;
import uz.nevermore.jwt_securiy.income.Income;
import uz.nevermore.jwt_securiy.income.IncomeRepository;
import uz.nevermore.jwt_securiy.outcome.Outcome;
import uz.nevermore.jwt_securiy.outcome.OutcomeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final CardRepository cardRepository;
    private final IncomeRepository incomeRepository;
    private final OutcomeRepository outcomeRepository;
    private final JwtService jwtService;
    public TransferResponse transfer(TransferRequest transferRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenWithoutBearer=token.substring(7);
        String username = jwtService.extractUsername(tokenWithoutBearer);
        System.out.println(username);
        Optional<Card> optionalCard = cardRepository.findByUser_Username(username);
        if (optionalCard.isPresent()){
            Optional<Card> toCardOptional = cardRepository.findByNumber(transferRequest.getToCardNumber());
            if (toCardOptional.isPresent()){
                Card fromCard = optionalCard.get();
                Card toCard = toCardOptional.get();
                if (fromCard.getBalance()>transferRequest.getTransferAmount()){
                    double fromCardBalance = fromCard.getBalance()- transferRequest.getTransferAmount()+ (
                            transferRequest.getTransferAmount()*new Outcome().getCommissionAmount());
                    double toCardBalance = toCard.getBalance()+ transferRequest.getTransferAmount();
                    fromCard.setBalance(fromCardBalance);
                    toCard.setBalance(toCardBalance);
                    var outcome=Outcome.builder()
                            .amount(transferRequest.getTransferAmount())
                            .toCard(toCard)
                            .fromCard(fromCard)
                            .build();
                    outcomeRepository.save(outcome);
                    var income= Income.builder()
                            .amount(transferRequest.getTransferAmount())
                            .toCard(toCard)
                            .fromCard(fromCard)
                            .build();
                    incomeRepository.save(income);
                    return new TransferResponse("Transfer occur successfully",true);
                }
                return new TransferResponse("there is no enough money in your card",false);
            }
            return new TransferResponse("The Card with this number not found",false);
        }


        return new TransferResponse("Problem with you card",false);
    }
}
