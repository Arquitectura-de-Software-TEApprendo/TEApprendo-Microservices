package com.hexagon.gameservice.interfaces.rest.transform;

import com.hexagon.gameservice.domain.commands.EditGame;
import com.hexagon.gameservice.domain.commands.RegisterGame;
import com.hexagon.gameservice.interfaces.rest.resources.GameResource;

public class GameResourceFromCommandAssembler {
    public static GameResource toResourceFromRegisterGame(RegisterGame command) {
        return new GameResource(
                command.getId(),
                command.getName(),
                command.getType(),
                command.getDifficulty(),
                command.getTopic());
    }

    public static GameResource toResourceFromEditGame(EditGame command) {
        return new GameResource(
                command.getId(),
                command.getName(),
                command.getType(),
                command.getDifficulty(),
                command.getTopic());
    }
}
