package br.com.boavizinhanca.cad.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PARTNER")
@ApiModel(value = "Modelo Entidade PARTNER")
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID_PARTNER")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificação da empresa parceira", required = true, name = "id", example = "1", position = 1)
    private int id;

    @Column(name = "ID_USER")
    @ApiModelProperty(notes = "Identificação do usuário", required = true, name = "idUser", example = "1", position = 2)
    private int idUser;

    @Column(name = "NR_DOCUMENTO")
    @ApiModelProperty(notes = "Número do documento da empresa parceira", required = true, name = "document", example = "00992115000290", position = 3)
    private String document;

    @Column(name = "NOME_EMPRESA")
    @ApiModelProperty(notes = "Nome da empresa parceira", required = true, name = "trade", example = "Multicoisas", position = 4)
    private String trade;

    @Column(name = "RAZAO_SOCIAL")
    @ApiModelProperty(notes = "Razão Social da empresa paceira", required = true, name = "company", example = "MULTICOISAS FRANQUIAS LTDA", position = 5)
    private String company;

    @Column(name = "LOGRADOURO")
    @ApiModelProperty(notes = "Endereço da empresa parceira", required = true, name = "address", example = "Av. Dr. Chucri Zaidan, 902", position = 6)
    private String address;

    @Column(name = "COMPLEMENTO")
    @ApiModelProperty(notes = "Complemento do endereço da empresa parceira", name = "address2", example = "Loja 3", position = 7)
    private String address2;

    @Column(name = "CEP")
    @ApiModelProperty(notes = "CEP do endereço da empresa parceira", required = true, name = "zipCode", example = "04795100", position = 8)
    private String zipCode;

    @Column(name = "TELEFONE")
    @ApiModelProperty(notes = "Número de telefone da empresa parceira", required = true, name = "phone", example = "1151810915", position = 9)
    private String phone;
}
