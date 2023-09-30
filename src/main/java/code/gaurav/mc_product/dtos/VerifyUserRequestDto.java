package code.gaurav.mc_product.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerifyUserRequestDto {
    private String userName;
    private String password;
}
