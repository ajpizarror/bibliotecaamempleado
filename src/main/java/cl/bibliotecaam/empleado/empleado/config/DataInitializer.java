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

        Empleado operario = empleadoRepository.save(
                new Empleado(null,17752427L,"1","Alfonso","Jose","Pizarro","Ramirez", LocalDate.of(1990,11,15), 280000L));
        Empleado gerente = empleadoRepository.save(
                new Empleado(null,12345678L,"K","Martina","Soledad","Pizarro","Ramirez",LocalDate.of(1988,9,21),800000L));
        Empleado paladin = empleadoRepository.save(
                new Empleado(null,798465132L,"K","Miguel","Angel","Arguello","Quintana",LocalDate.of(2000,7,20),777777L));
        Empleado picaro = empleadoRepository.save(
                new Empleado(null,165488552L,"8","Ludwig","W","Wittgenstein","X",LocalDate.of(1920,6,30),1000000L));
        Empleado hechicero = empleadoRepository.save(
                new Empleado(null,6565652L,"7","Hernan","X","Pizarro","Fernandez",LocalDate.of(1950,7,20),400550L));

    }
}
