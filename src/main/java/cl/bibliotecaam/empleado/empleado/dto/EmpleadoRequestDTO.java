package cl.bibliotecaam.empleado.empleado.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoRequestDTO {

    @NotNull(message = "El run es obligatorio.")
    private Long numrun_emp;

    @NotBlank(message = "El dv es obligatorio.")
    private String dvrun_emp;

    @NotBlank(message = "El primer nombre no puede estar vacío.")
    private String pnombre_emp;

    @NotBlank(message = "El segundo nombre no puede estar vacío.")
    private String snombre_emp;

    @NotBlank(message = "El apellido paterno no puede estar vacío.")
    private String appaterno_emp;

    @NotBlank(message = "El apellido materno no puede estar vacío.")
    private String apmaterno_emp;

    @NotNull(message = "La fecha no puede estar vacía.")
    private LocalDate fec_contrato;

    @NotNull(message = "El sueldo no puede estar vacío.")
    private Long sueldo;

}
