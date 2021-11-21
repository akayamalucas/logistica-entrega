package br.com.akayama.logistica.exception.model;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RetornoErroFactory {

    public static RetornoErro criar(Integer status, LocalDateTime dataHora, String titulo, List<ObjectError> objectErrors, MessageSource messageSource) {
        List<Atributo> atributos = new ArrayList<>();

        objectErrors.forEach(error -> {
            FieldError fieldError = ((FieldError) error);
            atributos.add(new Atributo(fieldError.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        });

        return new RetornoErro(status, dataHora, titulo, atributos);
    }

    public static RetornoErro criar(Integer status, LocalDateTime dataHora, String titulo) {
        return new RetornoErro(status, dataHora, titulo);
    }
}
