package br.com.boavizinhanca.cad.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "TB_USER")
@ApiModel(value = "Modelo Entidade User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID_USER")
    @Id
    @JsonIgnore
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificação do usuário", required = true, value = "Identificação", name = "id")
    private int id;

    @Column(name = "EMAIL")
    @ApiModelProperty(notes = "Email", required = true, value = "Email", name = "login")
    private String login;

    @Column(name = "SENHA")
    @ApiModelProperty(notes = "Senha de acesso", required = true, value = "Senha", name = "password")
    private String password;
}
