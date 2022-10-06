package net.euskadi.osakidetza.apps.m999.d001_echo.controller;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicios REST de echo
 */
@Slf4j
@RestController
@RequestMapping(value = "/echo")
public class EchoController {

    @Bean
    public GroupedOpenApi busquedaHistoriaApi() {
        return GroupedOpenApi.builder().group("echo").pathsToMatch("/echo/**").build();
    }

    @Operation(summary = "Hace echo")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK") })
    @GetMapping("/echo/{input}")
    public String echo(@PathVariable(value = "input") String input) {
        log.info("/echo/{} controller", input);
        return input;
    }

}