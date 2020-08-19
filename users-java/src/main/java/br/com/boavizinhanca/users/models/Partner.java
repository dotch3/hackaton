package br.com.boavizinhanca.users.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARTNER")
@ApiModel(value = "Modelo Entidade PARTNER")
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "DOCUMENT")
    @Id
    @ApiModelProperty(notes = "Identificação do parceiro", required = true, value = "Documento", name = "document")
    private String document;

    @Column(name = "COMPANY")
    @ApiModelProperty(notes = "Nome da empresa", required = true, value = "Empresa", name = "company")
    private String company;

    @Column(name = "TRADE")
    @ApiModelProperty(notes = "Nome fantasia", required = true, value = "Nome", name = "trade")
    private String trade;

    @Column(name = "EMAIL")
    @ApiModelProperty(notes = "Email", required = true, value = "Email", name = "email")
    private String email;

    @Column(name = "phone")
    @ApiModelProperty(notes = "Número de telefone", required = true, value = "Telefone", name = "phone")
    private String phone;
}
