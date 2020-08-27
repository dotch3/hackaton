package br.com.boavizinhanca.cad.responses;

import br.com.boavizinhanca.cad.enums.UserTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseAuthenticate {
    private AuthenticateResponse data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthenticateResponse {

        @ApiModelProperty(notes = "Número do documento do cliente", required = true, name = "document", example = "83019814073", position = 1)
        private String document;

        @ApiModelProperty(notes = "Tipo de usuário", required = true, name = "userType", example = "CUSTOMER", position = 2)
        private UserTypeEnum userType;
    }
}