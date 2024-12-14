package com.hexagon.gameservice.interfaces.rest;

import com.hexagon.gameservice.application.commandservices.GameCommandService;
import com.hexagon.gameservice.application.queryservices.GameQueryService;
import com.hexagon.gameservice.domain.projections.GameAuditLogProjection;
import com.hexagon.gameservice.domain.projections.GameProjection;
import com.hexagon.gameservice.interfaces.rest.resources.*;
import com.hexagon.gameservice.interfaces.rest.transform.EditGameCommandFromResourceAssembler;
import com.hexagon.gameservice.interfaces.rest.transform.RegisterGameCommandFromResourceAssembler;
import com.hexagon.gameservice.interfaces.rest.transform.GameResourceFromCommandAssembler;
import com.hexagon.gameservice.shared.interfaces.rest.Pagination;
import io.hypersistence.tsid.TSID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Games", description = "Game Management Endpoints")
public class GamesController {
    private final GameCommandService gameCommandService;
    private final GameQueryService gameQueryService;

    public GamesController(GameCommandService gameCommandService, GameQueryService gameQueryService) {
        this.gameCommandService = gameCommandService;
        this.gameQueryService = gameQueryService;
    }

    @Operation(summary = "Register a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register Game received successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<RegisterGameResponseResource> register(@RequestBody RegisterGameResource resource) {
        try {
            Long id = TSID.Factory.getTsid().toLong();
            resource = resource.withId(id);
            var command = RegisterGameCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = gameCommandService.register(command);
            if (notification.hasErrors()) {
                var response = new RegisterGameResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var gameResource = GameResourceFromCommandAssembler.toResourceFromRegisterGame(command);
            var responseResource = new RegisterGameResponseResource(gameResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new RegisterGameResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Edit a game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edit Game received successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EditGameResponseResource> edit(@PathVariable("id") Long id, @RequestBody EditGameResource resource) {
        try {
            resource = resource.withId(id);
            var command = EditGameCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = gameCommandService.edit(command);
            if (notification.hasErrors()) {
                var response = new EditGameResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var gameResource = GameResourceFromCommandAssembler.toResourceFromEditGame(command);
            var responseResource = new EditGameResponseResource(gameResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new EditGameResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/{page}/limit/{limit}")
    @Operation(summary = "Get games")
    public ResponseEntity<GetGamesResponseResource> getGames(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        try {
            List<GameProjection> games = gameQueryService.getGames(page, limit);
            var response = new GetGamesResponseResource(games, null);
            HttpHeaders headers = Pagination.createPaginationHeaders(games.size(), page, limit);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GetGamesResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/audit-logs")
    @Operation(summary = "Get game audit logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GameAuditLogResponseResource> getAuditLogsByGameId(@PathVariable("id") Long gameId) {
        try {
            List<GameAuditLogProjection> auditLog = gameQueryService.getAuditLogsByGameId(gameId);
            var response = new GameAuditLogResponseResource(auditLog, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GameAuditLogResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
