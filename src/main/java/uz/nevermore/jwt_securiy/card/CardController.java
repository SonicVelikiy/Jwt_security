package uz.nevermore.jwt_securiy.card;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService CardService;
    @PostMapping
    public HttpEntity<?> addCard(@RequestBody AddCardRequest addCardRequest){
        return ResponseEntity.ok(CardService.addCard(addCardRequest));
    }
    @PutMapping("/editCard/{id}")
    public HttpEntity<?>editCard(@RequestBody EditCardRequest editCardRequest,@PathVariable Long id){
        return ResponseEntity.ok(CardService.editCard(id,editCardRequest));
    }
}
