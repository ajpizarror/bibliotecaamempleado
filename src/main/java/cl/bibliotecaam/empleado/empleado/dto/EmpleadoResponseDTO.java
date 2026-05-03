package cl.bibliotecaam.empleado.empleado.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoResponseDTO {
    private Long id_emp;
    private Long numrun_emp;
    private String dvrun_emp;
    private String pnombre_emp;
    private String snombre_emp;
    private String appaterno_emp;
    private String apmaterno_emp;
    private LocalDate fec_contrato;
    private Long sueldo;



}
