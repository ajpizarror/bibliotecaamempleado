package cl.bibliotecaam.empleado.empleado;

import cl.bibliotecaam.empleado.empleado.model.Empleado;
import cl.bibliotecaam.empleado.empleado.repository.EmpleadoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public void run(String... args) throws Exception{

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            Empleado empleado = new Empleado();
            empleado.setNumrunEmp((long) faker.number().numberBetween(10000000,99999999));
            String opcionesDv = "0123456789K";
            int indice = faker.number().numberBetween(0, opcionesDv.length());
            String dvAleatorio = String.valueOf(opcionesDv.charAt(indice));
            empleado.setDvrunEmp(dvAleatorio);
            empleado.setPnombreEmp(faker.name().firstName());
            empleado.setSnombreEmp(faker.name().firstName());
            empleado.setAppaternoEmp(faker.name().lastName());
            empleado.setApmaternoEmp(faker.name().lastName());
            empleado.setFecContrato(faker.timeAndDate().birthday());
            empleado.setSueldo((long)faker.number().numberBetween(550000,5000000));

            empleadoRepository.save(empleado);

        }
    }
}