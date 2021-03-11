package dev.vatuu.backbone.exceptions;

public class MapStateMissingException extends BackboneException {

    public MapStateMissingException(int id) {
        super("MapState with the ID %d does not exist!", id);
    }
}
