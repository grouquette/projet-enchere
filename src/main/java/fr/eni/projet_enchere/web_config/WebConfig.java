package fr.eni.projet_enchere.web_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.eni.projet_enchere.converter.StringToCategorieConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Autowired
    private StringToCategorieConverter stringToCategorieConverter;
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToCategorieConverter);
    }
}