package dev.vatuu.backbone.exceptions;

public class BackboneException extends Exception {

    public BackboneException(String format, Object... args) {
        super(String.format(format, args));
    }
}
