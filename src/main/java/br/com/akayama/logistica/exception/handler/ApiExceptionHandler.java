package br.com.akayama.logistica.exception.handler;

import br.com.akayama.logistica.exception.RequisicaoInvalidaException;
import br.com.akayama.logistica.exception.RecursoNaoEncontradoException;
import br.com.akayama.logistica.exception.model.RetornoErro;
import br.com.akayama.logistica.exception.model.RetornoErroFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RetornoErro retornoErro = RetornoErroFactory.criar(
                status.value(),
                LocalDateTime.now(),
                "Problema na validação dos atributos",
                ex.getAllErrors(),
                messageSource);

        return super.handleExceptionInternal(ex, retornoErro, headers, status, request);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RetornoErro retornoErro = RetornoErroFactory.criar(
                status.value(),
                LocalDateTime.now(),
                ex.getMessage());

        return super.handleExceptionInternal(ex, retornoErro, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(RequisicaoInvalidaException.class)
    public ResponseEntity<Object> handleDomainException(RequisicaoInvalidaException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RetornoErro retornoErro = RetornoErroFactory.criar(
                status.value(),
                LocalDateTime.now(),
                ex.getMessage());

        return super.handleExceptionInternal(ex, retornoErro, new HttpHeaders(), status, webRequest);
    }
}
