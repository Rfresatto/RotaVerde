package br.com.rota_verde.rota_verde.exception;

public class AlertNotFoundException extends RuntimeException {
    public AlertNotFoundException(String message) {
        super(message);
    }
}
