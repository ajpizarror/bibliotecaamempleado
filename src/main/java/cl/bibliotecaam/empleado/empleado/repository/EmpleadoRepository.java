package cl.bibliotecaam.empleado.empleado.repository;

import cl.bibliotecaam.empleado.empleado.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findAll();
    List<Empleado> findByNumrun_emp(Long numrun);
    List<Empleado> findByAppaterno_emp(String appaterno);
    List<Empleado> findBySueldo(Long sueldo);
    void deleteByRun();
}
