package br.com.rota_verde.rota_verde.exception;

public class CollectionNotFoundException extends RuntimeException {
    public CollectionNotFoundException(String message) {
        super(message);
    }
}
