package uz.nevermore.jwt_securiy.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransferRequest {
    private String toCardNumber;
    private double transferAmount;
}
