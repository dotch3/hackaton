package br.com.boavizinhanca.cad.responses;

import br.com.boavizinhanca.cad.entities.Partner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseListPartner {

    private List<Partner> data;
}
