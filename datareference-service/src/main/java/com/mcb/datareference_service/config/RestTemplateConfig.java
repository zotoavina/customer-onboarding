package com.mcb.datareference_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mcb.datareference_service.dto.Country;
import com.mcb.datareference_service.utils.CountryDeserializer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        var mapper = new ObjectMapper();
        var module = new SimpleModule();

        module.addDeserializer(Country.class, new CountryDeserializer());
        mapper.registerModule(module);

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(mapper);

        return new RestTemplateBuilder()
                .messageConverters(messageConverter)
                .build();
    }

}
