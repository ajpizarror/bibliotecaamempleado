package cl.bibliotecaam.empleado.empleado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_emp;
    @Column(nullable = false, length = 8, unique = true)
    private Long numrun_emp;
    @Column(nullable = false, length = 1, unique = false)
    private String dvrun_emp;
    @Column(nullable = false, length = 30, unique = false)
    private String pnombre_emp;
    @Column(nullable = true, length = 30, unique = false)
    private String snombre_emp;
    @Column(nullable = false, length = 30, unique = false)
    private String appaterno_emp;
    @Column(nullable = false, length = 30, unique = false)
    private String apmaterno_emp;
    @Column(nullable = false)
    private LocalDate fec_contrato;
    @Column(nullable = false, length = 7, unique = false)
    private Long sueldo;


}
