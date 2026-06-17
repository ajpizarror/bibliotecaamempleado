package cl.bibliotecaam.empleado.empleado.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import cl.bibliotecaam.empleado.empleado.controller.EmpleadoController;
import cl.bibliotecaam.empleado.empleado.dto.EmpleadoResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoModelAssembler implements RepresentationModelAssembler<EmpleadoResponseDTO, EntityModel<EmpleadoResponseDTO>> {
    @Override
    public EntityModel<EmpleadoResponseDTO> toModel(EmpleadoResponseDTO empleadoDto){
        return EntityModel.of(empleadoDto,
                linkTo(methodOn(EmpleadoController.class).obtenerPorId(empleadoDto.getId_emp())).withSelfRel(),
                linkTo(methodOn(EmpleadoController.class).obtenerTodos()).withRel("empleados"));
    }
}
