package net.dosaki.l.utils.exceptions;

public class MissingHeaderException extends Exception {
    public MissingHeaderException(String missingHeader) {
        super(String.format("Header '%s' is missing", missingHeader));
    }
}
