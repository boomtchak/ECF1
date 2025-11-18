package fr.cda.java.Exceptions;

public class UniciteException extends RuntimeException {

    public UniciteException(String raisonSociale) {
        super( String.format("Les raisons sociales sont uniques pour tous les types de sociétés. (%s)", raisonSociale));
    }
}
