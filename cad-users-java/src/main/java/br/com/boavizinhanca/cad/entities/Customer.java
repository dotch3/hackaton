package br.com.boavizinhanca.cad.entities;

import br.com.boavizinhanca.cad.enums.GenderEnum;
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
@Table(name = "TB_CUSTOMER")
@ApiModel(value = "Modelo Entidade CUSTOMER")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID_CUSTOMER")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificação do cliente", required = true, name = "id", example = "1", position = 1)
    private int id;

    @Column(name = "ID_USER")
    @ApiModelProperty(notes = "Identificação do usuário", required = true, name = "idUser", example = "1", position = 2)
    private int idUser;

    @Column(name = "NR_DOCUMENTO")
    @ApiModelProperty(notes = "Número do documento do cliente", required = true, name = "document", example = "83019814073", position = 3)
    private String document;

    @Column(name = "NOME")
    @ApiModelProperty(notes = "Nome do cliente", required = true, name = "trade", example = "Geraldo Almeida Batista Corrêa", position = 4)
    private String name;

    @Column(name = "SEXO")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Gênero do cliente", required = true, name = "company", example = "M", position = 5)
    private GenderEnum gender;

    @Column(name = "LOGRADOURO")
    @ApiModelProperty(notes = "Endereço do cliente", required = true, name = "address", example = "Rua Antônio Lopes Machado, 376", position = 6)
    private String address;

    @Column(name = "COMPLEMENTO")
    @ApiModelProperty(notes = "Complemento do endereço do cliente", name = "address2", example = "Casa 4", position = 7)
    private String address2;

    @Column(name = "CEP")
    @ApiModelProperty(notes = "CEP do endereço do cliente", required = true, name = "zipCode", example = "05894260", position = 8)
    private String zipCode;

    @Column(name = "TELEFONE")
    @ApiModelProperty(notes = "Número de telefone do cliente", required = true, name = "phone", example = "11970701010", position = 9)
    private String phone;
}
