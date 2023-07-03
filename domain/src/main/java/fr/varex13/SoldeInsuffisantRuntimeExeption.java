package fr.varex13;

public class SoldeInsuffisantRuntimeExeption extends RuntimeException {
    public SoldeInsuffisantRuntimeExeption() {
        super("Solde insuffisant");
    }
}
