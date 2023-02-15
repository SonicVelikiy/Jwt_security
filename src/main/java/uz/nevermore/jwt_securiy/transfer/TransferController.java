package uz.nevermore.jwt_securiy.transfer;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;
    @PostMapping("/transfer")
    public HttpEntity<?>transfer(@RequestBody TransferRequest transferRequest, HttpServletRequest request){
       return ResponseEntity.ok(transferService.transfer(transferRequest,request));


    }
}
