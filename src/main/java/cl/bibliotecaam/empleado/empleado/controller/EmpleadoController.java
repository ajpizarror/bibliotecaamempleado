package cl.bibliotecaam.empleado.empleado.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import cl.bibliotecaam.empleado.empleado.assembler.EmpleadoModelAssembler;
import cl.bibliotecaam.empleado.empleado.dto.EmpleadoRequestDTO;
import cl.bibliotecaam.empleado.empleado.dto.EmpleadoResponseDTO;
import cl.bibliotecaam.empleado.empleado.model.Empleado;
import cl.bibliotecaam.empleado.empleado.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/bibliotecaam/empleado")
@RequiredArgsConstructor
@Tag(name = "Empleados", description = "Operaciones asociadas a empleados.")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    private EmpleadoModelAssembler assembler;
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas los empleados", description = "Obtiene una lista de todos los empleados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<CollectionModel<EntityModel<EmpleadoResponseDTO>>> obtenerTodos(){
        List<EntityModel<EmpleadoResponseDTO>> empleados = empleadoService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(empleados,
                linkTo(methodOn(EmpleadoController.class).obtenerTodos()).withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener empleado por id", description = "Obtiene un empleado acorde a una id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<EntityModel<EmpleadoResponseDTO>> obtenerPorId(@PathVariable Long id){
        return empleadoService.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/run/{run}")
    @Operation(summary = "Obtener empleado por run", description = "Obtiene un empleado acorde a su run.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<EntityModel<EmpleadoResponseDTO>> obtenerPorRun(@PathVariable Long run){
        return empleadoService.obtenerPorRun(run)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/apellido/{appaterno}")
    @Operation(summary = "Obtener empleados por apellido paterno", description = "Obtiene empleados acorde a apellido paterno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Asistencia no encontrada")
    })
    public ResponseEntity<CollectionModel<EntityModel<EmpleadoResponseDTO>>> obtenerPorAppaterno(@PathVariable String appaterno){
        List<EntityModel<EmpleadoResponseDTO>> empleados = empleadoService.listarPorAppaterno(appaterno).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(empleados,
                linkTo(methodOn(EmpleadoController.class).obtenerPorAppaterno(appaterno)).withSelfRel()));
    }

    @GetMapping("/sueldo/{sueldo}")
    @Operation(summary = "Obtener empleados por sueldo", description = "Obtiene empleados acorde a su sueldo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    public ResponseEntity<CollectionModel<EntityModel<EmpleadoResponseDTO>>> obtenerPorSueldo(@PathVariable Long sueldo){
        List<EntityModel<EmpleadoResponseDTO>> empleados = empleadoService.listarPorSueldo(sueldo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(empleados,
                linkTo(methodOn(EmpleadoController.class).obtenerPorSueldo(sueldo)).withSelfRel()));
    }

    @GetMapping("/sueldomenora/{monto}")
    public ResponseEntity<Optional<List<EmpleadoResponseDTO>>> obtenerSueldoMenorA(@PathVariable Long monto){
        return ResponseEntity.ok(empleadoService.obtenerPorMonto(monto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado", description = "Elimina a un empleado o empleada acorde a una id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "¡Empleado eliminado con exito!"),
            @ApiResponse(responseCode = "404",description = "ERROR: ¡El id de el empleado ingresado no existe!")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (empleadoService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        empleadoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Guardar un empleado", description = "Guarda una empleado acorde a lo ingresado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa."),
            @ApiResponse(responseCode = "400", description = "Error al ingresar parametros. Revise si ingreso todos los parametros solicitados."),
            @ApiResponse(responseCode = "403", description = "No tienes permiso para hacer el cambio.")
    })
    public ResponseEntity<EmpleadoResponseDTO> guardar(@Valid @RequestBody EmpleadoRequestDTO doto){
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.guardar(doto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado", description = "Actualiza un empleado acorde a un id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asistencia actualizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empleado.class))),
            @ApiResponse(responseCode = "404", description = "El id de la asistencia no existe.")
    })
    public ResponseEntity<EmpleadoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EmpleadoRequestDTO doto){
        return empleadoService.actualizar(id, doto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}