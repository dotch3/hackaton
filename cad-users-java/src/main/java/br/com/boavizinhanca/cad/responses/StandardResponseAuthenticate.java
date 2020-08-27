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

        @ApiModelProperty(notes = "Identificação do usuário", required = true, name = "idUser", example = "1", position = 1)
        private int idUser;

        @ApiModelProperty(notes = "Número do documento do cliente", required = true, name = "document", example = "83019814073", position = 2)
        private String document;

        @ApiModelProperty(notes = "Tipo de usuário", required = true, name = "userType", example = "CUSTOMER", position = 3)
        private UserTypeEnum userType;
    }
}