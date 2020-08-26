package br.com.boavizinhanca.cad.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerSaveDTO {

    @ApiModelProperty(notes = "Login", required = true, name = "login", example = "empresa_contato@empresa.com", position = 1)
    private String login;

    @ApiModelProperty(notes = "Senha", required = true, name = "id", example = "SenhaDificil123", position = 2)
    private String password;

    @ApiModelProperty(notes = "Número do documento da empresa parceira", required = true, name = "document", example = "00992115000290", position = 3)
    private String document;

    @ApiModelProperty(notes = "Nome da empresa parceira", required = true, name = "trade", example = "Multicoisas", position = 4)
    private String trade;

    @ApiModelProperty(notes = "Razão Social da empresa paceira", required = true, name = "company", example = "MULTICOISAS FRANQUIAS LTDA", position = 5)
    private String company;

    @ApiModelProperty(notes = "Endereço da empresa parceira", required = true, name = "address", example = "Av. Dr. Chucri Zaidan, 902", position = 6)
    private String address;

    @ApiModelProperty(notes = "Complemento do endereço da empresa parceira", name = "address2", example = "Loja 3", position = 7)
    private String address2;

    @ApiModelProperty(notes = "CEP do endereço da empresa parceira", required = true, name = "zipCode", example = "04795100", position = 8)
    private String zipCode;

    @ApiModelProperty(notes = "Número de telefone da empresa parceira", required = true, name = "phone", example = "1151810915", position = 9)
    private String phone;
}
