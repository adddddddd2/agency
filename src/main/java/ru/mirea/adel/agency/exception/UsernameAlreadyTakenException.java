package ru.mirea.adel.agency.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String s) {
        super(s);
    }
}
