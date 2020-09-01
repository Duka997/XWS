package com.demo.confuguration;

import com.demo.client.VoziloClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.demo.generated");
        return marshaller;
    }

    @Bean
    public VoziloClient voziloClient(Jaxb2Marshaller marshaller) {
        VoziloClient voziloClient = new VoziloClient();
        voziloClient.setDefaultUri("http://localhost:8083/vozilo-soap");
        voziloClient.setMarshaller(marshaller);
        voziloClient.setUnmarshaller(marshaller);
        return voziloClient;
    }
}