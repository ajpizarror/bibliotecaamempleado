package cl.bibliotecaam.empleado.empleado.controller;

import cl.bibliotecaam.empleado.empleado.dto.EmpleadoResponseDTO;
import cl.bibliotecaam.empleado.empleado.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("sueldo/{sueldo}")
    public ResponseEntity<List<EmpleadoResponseDTO>> obtenerPorSueldo(@PathVariable Long sueldo){
        return ResponseEntity.ok(empleadoService.listarPorSueldo(sueldo));
    }



}
