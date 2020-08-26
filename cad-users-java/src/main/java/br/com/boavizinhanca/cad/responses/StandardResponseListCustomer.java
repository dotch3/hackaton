package br.com.boavizinhanca.cad.responses;

import br.com.boavizinhanca.cad.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponseListCustomer {

    private List<Customer> data;
}
