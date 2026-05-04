package cl.bibliotecaam.empleado.empleado.repository;

import cl.bibliotecaam.empleado.empleado.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findByNumrunEmp(Long numrun);
    List<Empleado> findByAppaternoEmp(String appaterno);
    List<Empleado> findBySueldo(Long sueldo);

    @Query("SELECT e FROM Empleado e WHERE e.sueldo <= :monto ORDER BY e.sueldo DESC")
    List<Empleado> findEmpleadoBySueldo(@Param("monto") Long monto);
}
