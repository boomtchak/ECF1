package fr.cda.java.Exceptions;

public class MandatoryDataException extends RuntimeException {


    public MandatoryDataException(String champs) {


        super(new StringBuilder("Tous les champs sont obligatoires  : ").append("le champs ").append(champs).append(" n'est pas renseigné").toString()) ;
    }



}
