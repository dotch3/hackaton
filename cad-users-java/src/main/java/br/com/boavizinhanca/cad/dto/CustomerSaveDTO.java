package br.com.boavizinhanca.cad.dto;

import br.com.boavizinhanca.cad.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSaveDTO {

    @ApiModelProperty(notes = "Login", required = true, name = "login", example = "geraldinho@outlook.com", position = 1)
    private String login;

    @ApiModelProperty(notes = "Senha", required = true, name = "id", example = "SenhaDificil123", position = 2)
    private String password;

    @ApiModelProperty(notes = "Número do documento do cliente", required = true, name = "document", example = "83019814073", position = 3)
    private String document;

    @ApiModelProperty(notes = "Nome do cliente", required = true, name = "trade", example = "Geraldo Almeida Batista Corrêa", position = 4)
    private String name;

    @ApiModelProperty(notes = "Gênero do cliente", required = true, name = "company", example = "M", position = 5)
    private GenderEnum gender;

    @ApiModelProperty(notes = "Endereço do cliente", required = true, name = "address", example = "Rua Antônio Lopes Machado, 376", position = 6)
    private String address;

    @ApiModelProperty(notes = "Complemento do endereço do cliente", name = "address2", example = "Casa 4", position = 7)
    private String address2;

    @ApiModelProperty(notes = "CEP do endereço do cliente", required = true, name = "zipCode", example = "05894260", position = 8)
    private String zipCode;

    @ApiModelProperty(notes = "Número de telefone do cliente", required = true, name = "phone", example = "11970701010", position = 9)
    private String phone;
}
