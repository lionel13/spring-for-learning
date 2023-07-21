package fr.varex13.inputport;

public class AuthentificationRuntimeException extends RuntimeException {
    public AuthentificationRuntimeException() {
        super("Non authentifié");
    }
}
