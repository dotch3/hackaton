package br.com.boavizinhanca.cad.responses;

import br.com.boavizinhanca.cad.entities.Partner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponsePartner {

    private Partner data;
}
