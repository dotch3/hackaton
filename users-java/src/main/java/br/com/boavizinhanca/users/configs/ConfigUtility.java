package br.com.boavizinhanca.users.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Getter
@Setter
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class ConfigUtility {

    @Autowired
    private Environment environment;

    public String getProperty(String pPropertyKey) {
        return this.environment.getProperty(pPropertyKey);
    }

    public String getProperty(String pPropertyKey, String pDefault) {
        return this.environment.getProperty(pPropertyKey, pDefault);
    }
}
