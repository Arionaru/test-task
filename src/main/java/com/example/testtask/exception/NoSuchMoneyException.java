package com.example.testtask.exception;

public class NoSuchMoneyException extends RuntimeException {
    public NoSuchMoneyException() {
        super("Нет таких денег!");
    }
}
