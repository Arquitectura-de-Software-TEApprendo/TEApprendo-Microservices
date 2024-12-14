package com.hexagon.gameservice.interfaces.rest.transform;

import com.hexagon.gameservice.domain.commands.EditGame;
import com.hexagon.gameservice.interfaces.rest.resources.EditGameResource;

public class EditGameCommandFromResourceAssembler {
    public static EditGame toCommandFromResource(EditGameResource resource) {
        return new EditGame(
                resource.id(),
                resource.name(),
                resource.type(),
                resource.difficulty(),
                resource.topic(),
                resource.updatedAt());
    }
}
