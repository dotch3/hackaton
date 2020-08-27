package br.com.boavizinhanca.cad.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @ApiModelProperty(notes = "Login", required = true, name = "login", example = "empresa_contato@empresa.com")
    private String login;

    @ApiModelProperty(notes = "Senha", required = true, name = "id", example = "3B4402C849EB7467BF9CBE3A779289E31AEAA89ABBE6D711ECDB3C4B538A373F")
    private String password;

}
