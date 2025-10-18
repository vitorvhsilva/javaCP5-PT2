package br.com.leroymarcel.store.exception;

public class FerramentaNaoEncontradaException extends RuntimeException {
    public FerramentaNaoEncontradaException(String message) {
        super(message);
    }
}