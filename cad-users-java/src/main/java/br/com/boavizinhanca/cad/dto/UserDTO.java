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

    @ApiModelProperty(notes = "Login", required = true, name = "login", example = "matheus0.02.0@icloud.com")
    private String login;

    @ApiModelProperty(notes = "Senha", required = true, name = "id", example = "8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92")
    private String password;

}
