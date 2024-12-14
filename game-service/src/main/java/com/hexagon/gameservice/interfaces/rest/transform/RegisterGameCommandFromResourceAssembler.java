package com.hexagon.gameservice.interfaces.rest.transform;

import com.hexagon.gameservice.domain.commands.RegisterGame;
import com.hexagon.gameservice.interfaces.rest.resources.RegisterGameResource;

public class RegisterGameCommandFromResourceAssembler {
    public static RegisterGame toCommandFromResource(RegisterGameResource resource) {
        return new RegisterGame(
                resource.id(),
                resource.name(),
                resource.type(),
                resource.difficulty(),
                resource.topic(),
                resource.createdAt());
    }
}
