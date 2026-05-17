package cl.bibliotecaam.empleado.empleado.config;

import cl.bibliotecaam.empleado.empleado.model.Empleado;
import cl.bibliotecaam.empleado.empleado.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final EmpleadoRepository empleadoRepository;

    @Override
    public void run(String... args){
        if (empleadoRepository.count()>0){
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga inicial.");
            return;
        }

        log.info(">>> DataInitializer: BD vacía detectada, insertando datos de prueba...");

        empleadoRepository.save(new Empleado(null,17752427L,"1","Alfonso","Jose","Pizarro","Ramirez", LocalDate.of(1990,11,15), 580000L));
        empleadoRepository.save(new Empleado(null,22321987L,"4","Miguel Angel","Gabriel","Arguello","Quintana",LocalDate.of(2006,7,20),500000L));

    }
}
