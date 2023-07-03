package fr.varex13.usecases;

public class AuthentificationRuntimeException extends RuntimeException {
    public AuthentificationRuntimeException() {
        super("Non authentifié");
    }
}
