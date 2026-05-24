package br.com.rota_verde.rota_verde.exception;

public class ContainerNotFoundException extends RuntimeException {
    public ContainerNotFoundException(String message) {
        super(message);
    }
}
