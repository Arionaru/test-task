package com.example.testtask.exception;

public class NoSuchClanException extends RuntimeException {
    public NoSuchClanException() {
        super("Клан не найден!");
    }
}
