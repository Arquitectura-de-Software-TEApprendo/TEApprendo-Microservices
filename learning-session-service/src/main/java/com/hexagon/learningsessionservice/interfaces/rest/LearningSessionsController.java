package com.hexagon.learningsessionservice.interfaces.rest;

import com.hexagon.learningsessionservice.application.commandservices.LearningSessionCommandService;
import com.hexagon.learningsessionservice.application.queryservices.LearningSessionQueryService;
import com.hexagon.learningsessionservice.domain.projections.LearningSessionAuditLogProjection;
import com.hexagon.learningsessionservice.domain.projections.LearningSessionProjection;
import com.hexagon.learningsessionservice.interfaces.rest.resources.*;
import com.hexagon.learningsessionservice.interfaces.rest.transform.EditLearningSessionCommandFromResourceAssembler;
import com.hexagon.learningsessionservice.interfaces.rest.transform.RegisterLearningSessionCommandFromResourceAssembler;
import com.hexagon.learningsessionservice.interfaces.rest.transform.LearningSessionResourceFromCommandAssembler;
import com.hexagon.learningsessionservice.shared.interfaces.rest.Pagination;
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
@RequestMapping(value = "/learning-sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "LearningSessions", description = "Learning Session Management Endpoints")
public class LearningSessionsController {
    private final LearningSessionCommandService learningSessionCommandService;
    private final LearningSessionQueryService learningSessionQueryService;

    public LearningSessionsController(LearningSessionCommandService learningSessionCommandService, LearningSessionQueryService learningSessionQueryService) {
        this.learningSessionCommandService = learningSessionCommandService;
        this.learningSessionQueryService = learningSessionQueryService;
    }

    @Operation(summary = "Register a new learning session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register Learning Session received successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<RegisterLearningSessionResponseResource> register(@RequestBody RegisterLearningSessionResource resource) {
        try {
            Long id = TSID.Factory.getTsid().toLong();
            resource = resource.withId(id);
            var command = RegisterLearningSessionCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = learningSessionCommandService.register(command);
            if (notification.hasErrors()) {
                var response = new RegisterLearningSessionResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var learningSessionResource = LearningSessionResourceFromCommandAssembler.toResourceFromRegisterLearningSession(command);
            var responseResource = new RegisterLearningSessionResponseResource(learningSessionResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new RegisterLearningSessionResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Edit a learningSession")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edit LearningSession received successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EditLearningSessionResponseResource> edit(@PathVariable("id") Long id, @RequestBody EditLearningSessionResource resource) {
        try {
            resource = resource.withId(id);
            var command = EditLearningSessionCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = learningSessionCommandService.edit(command);
            if (notification.hasErrors()) {
                var response = new EditLearningSessionResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var learningSessionResource = LearningSessionResourceFromCommandAssembler.toResourceFromEditLearningSession(command);
            var responseResource = new EditLearningSessionResponseResource(learningSessionResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new EditLearningSessionResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/{page}/limit/{limit}")
    @Operation(summary = "Get learning sessions")
    public ResponseEntity<GetLearningSessionsResponseResource> getLearningSessions(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        try {
            List<LearningSessionProjection> learningSessions = learningSessionQueryService.getLearningSessions(page, limit);
            var response = new GetLearningSessionsResponseResource(learningSessions, null);
            HttpHeaders headers = Pagination.createPaginationHeaders(learningSessions.size(), page, limit);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GetLearningSessionsResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/audit-logs")
    @Operation(summary = "Get learning session audit logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LearningSessionAuditLogResponseResource> getAuditLogsByLearningSessionId(@PathVariable("id") Long learningSessionId) {
        try {
            List<LearningSessionAuditLogProjection> auditLog = learningSessionQueryService.getAuditLogsByLearningSessionId(learningSessionId);
            var response = new LearningSessionAuditLogResponseResource(auditLog, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new LearningSessionAuditLogResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
