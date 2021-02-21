package com.kloia.atomikos.configuration;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonDateModifier() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

}
