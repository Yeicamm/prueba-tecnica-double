package com.prueba.pruebatecnica.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("El ticket con ID " + id + " no fue encontrado.");
    }
}