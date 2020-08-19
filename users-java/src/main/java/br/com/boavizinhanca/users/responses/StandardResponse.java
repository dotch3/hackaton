package br.com.boavizinhanca.users.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {

    @JsonProperty("data")
    private Object data = null;

    public StandardResponse data(Object object) {
        this.data = object;
        return this;
    }
}
