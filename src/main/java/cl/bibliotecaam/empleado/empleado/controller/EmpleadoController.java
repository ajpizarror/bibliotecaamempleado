package cl.bibliotecaam.empleado.empleado.controller;

import cl.bibliotecaam.empleado.empleado.dto.EmpleadoRequestDTO;
import cl.bibliotecaam.empleado.empleado.dto.EmpleadoResponseDTO;
import cl.bibliotecaam.empleado.empleado.model.Empleado;
import cl.bibliotecaam.empleado.empleado.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bibliotecaam/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(empleadoService.listarTodos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmpleadoResponseDTO> obtenerPorId(@PathVariable Long id){
        return empleadoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/run/{run}")
    public ResponseEntity<EmpleadoResponseDTO> obtenerPorRun(@PathVariable Long run){
        return empleadoService.obtenerPorRun(run)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/apellido/{appaterno}")
    public ResponseEntity<List<EmpleadoResponseDTO>> obtenerPorApellido(@PathVariable String appaterno){
        return ResponseEntity.ok(empleadoService.listarPorAppaterno(appaterno));
    }

    @GetMapping("/sueldo/{sueldo}")
    public ResponseEntity<List<EmpleadoResponseDTO>> obtenerPorSueldo(@PathVariable Long sueldo){
        return ResponseEntity.ok(empleadoService.listarPorSueldo(sueldo));
    }

    @GetMapping("/sueldomenora/{monto}")
    public ResponseEntity<Optional<List<EmpleadoResponseDTO>>> obtenerSueldoMenorA(@PathVariable Long monto){
        return ResponseEntity.ok(empleadoService.obtenerPorMonto(monto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (empleadoService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        empleadoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> guardar(@Valid @RequestBody EmpleadoRequestDTO empleado){
        return ResponseEntity.status(201).body(empleadoService.guardar(empleado));    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EmpleadoRequestDTO doto){
        return empleadoService.actualizar(id, doto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
