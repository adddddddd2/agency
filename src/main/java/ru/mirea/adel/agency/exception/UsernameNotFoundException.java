package ru.mirea.adel.agency.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String s) {
        super(s);
    }
}
