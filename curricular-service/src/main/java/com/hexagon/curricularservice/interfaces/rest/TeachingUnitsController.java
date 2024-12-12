package com.hexagon.curricularservice.interfaces.rest;

import com.hexagon.curricularservice.application.commandservices.TeachingUnitCommandService;
import com.hexagon.curricularservice.application.queryservices.TeachingUnitQueryService;
import com.hexagon.curricularservice.domain.projections.TeachingUnitAuditLogProjection;
import com.hexagon.curricularservice.domain.projections.TeachingUnitProjection;
import com.hexagon.curricularservice.interfaces.rest.resources.*;
import com.hexagon.learningsessionservice.interfaces.rest.resources.*;
import com.hexagon.curricularservice.interfaces.rest.transform.EditTeachingUnitCommandFromResourceAssembler;
import com.hexagon.curricularservice.interfaces.rest.transform.RegisterTeachingUnitCommandFromResourceAssembler;
import com.hexagon.curricularservice.interfaces.rest.transform.TeachingUnitResourceFromCommandAssembler;
import com.hexagon.curricularservice.shared.interfaces.rest.Pagination;
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
@RequestMapping(value = "/curricular/teaching-units", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "TeachingUnits", description = "Teaching Unit Management Endpoints")
public class TeachingUnitsController {
    private final TeachingUnitCommandService teachingUnitCommandService;
    private final TeachingUnitQueryService teachingUnitQueryService;

    public TeachingUnitsController(TeachingUnitCommandService teachingUnitCommandService, TeachingUnitQueryService teachingUnitQueryService) {
        this.teachingUnitCommandService = teachingUnitCommandService;
        this.teachingUnitQueryService = teachingUnitQueryService;
    }

    @Operation(summary = "Register a new teaching unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register Teaching Unit received successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<RegisterTeachingUnitResponseResource> register(@RequestBody RegisterTeachingUnitResource resource) {
        try {
            Long id = TSID.Factory.getTsid().toLong();
            resource = resource.withId(id);
            var command = RegisterTeachingUnitCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = teachingUnitCommandService.register(command);
            if (notification.hasErrors()) {
                var response = new RegisterTeachingUnitResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var teachingUnitResource = TeachingUnitResourceFromCommandAssembler.toResourceFromRegisterTeachingUnit(command);
            var responseResource = new RegisterTeachingUnitResponseResource(teachingUnitResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new RegisterTeachingUnitResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Edit a teachingUnit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edit TeachingUnit received successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EditTeachingUnitResponseResource> edit(@PathVariable("id") Long id, @RequestBody EditTeachingUnitResource resource) {
        try {
            resource = resource.withId(id);
            var command = EditTeachingUnitCommandFromResourceAssembler.toCommandFromResource(resource);
            var notification = teachingUnitCommandService.edit(command);
            if (notification.hasErrors()) {
                var response = new EditTeachingUnitResponseResource(null, notification.getErrors());
                return ResponseEntity.badRequest().body(response);
            }
            var teachingUnitResource = TeachingUnitResourceFromCommandAssembler.toResourceFromEditTeachingUnit(command);
            var responseResource = new EditTeachingUnitResponseResource(teachingUnitResource, null);
            return new ResponseEntity<>(responseResource, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new EditTeachingUnitResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page/{page}/limit/{limit}")
    @Operation(summary = "Get teaching units")
    public ResponseEntity<GetTeachingUnitsResponseResource> getTeachingUnits(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        try {
            List<TeachingUnitProjection> teachingUnits = teachingUnitQueryService.getTeachingUnits(page, limit);
            var response = new GetTeachingUnitsResponseResource(teachingUnits, null);
            HttpHeaders headers = Pagination.createPaginationHeaders(teachingUnits.size(), page, limit);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GetTeachingUnitsResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/audit-logs")
    @Operation(summary = "Get teaching unit audit logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TeachingUnitAuditLogResponseResource> getAuditLogsByTeachingUnitId(@PathVariable("id") Long teachingUnitId) {
        try {
            List<TeachingUnitAuditLogProjection> auditLog = teachingUnitQueryService.getAuditLogsByTeachingUnitId(teachingUnitId);
            var response = new TeachingUnitAuditLogResponseResource(auditLog, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new TeachingUnitAuditLogResponseResource(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
