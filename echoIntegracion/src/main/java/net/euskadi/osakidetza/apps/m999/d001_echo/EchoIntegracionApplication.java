package net.euskadi.osakidetza.apps.m999.d001_echo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.Formatter;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.classic.util.ContextInitializer;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Clase aplicacion
 */
@SpringBootApplication
@ComponentScan(value = { "net.euskadi.osakidetza" })
@PropertySource("classpath:m999_d001_application.properties")
public class EchoIntegracionApplication extends SpringBootServletInitializer {

    /**
     * Metodo main
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "spring.config.name:m999_d001_logback.xml");
        new SpringApplicationBuilder(EchoIntegracionApplication.class).properties("spring.config.name:m999_d001_application").build().run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EchoIntegracionApplication.class);
    }

    @Bean
    public Formatter<LocalDateTime> localDateFormatter() {
        return new Formatter<LocalDateTime>() {

            @Override
            public String print(LocalDateTime date, Locale arg1) {
                return DateTimeFormatter.ISO_DATE_TIME.format(date);
            }

            @Override
            public LocalDateTime parse(String text, Locale arg1) {
                return LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
            }
        };
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Echo Integracion API").description("Echo Integracion application").version("v0.0.1"))
                .externalDocs(new ExternalDocumentation().description("Echo Wiki Documentation")
                        .url("https://www.ejie.euskadi.eus/images/webejie00-logo-aniversario.png"));
    }
}
