package fr.varex13.student.inputport;

public class AuthentificationRuntimeException extends RuntimeException {
    public AuthentificationRuntimeException() {
        super("Non authentifié");
    }
}
