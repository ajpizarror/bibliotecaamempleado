package cl.bibliotecaam.empleado.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

/*
* * * * * * NOTA A FUTURO
------> El Authenticator puede ser un MS aparte, a pesar de eso, vamos a dejarlo simple para
* Que la logica indique que se esta autenticando principalmente al MS de Empleado.
* En la evolucion futura, debemos separarlo y vincularlo con los MS que necesiten su token.
 */
@EnableDiscoveryClient
public class EmpleadoApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmpleadoApplication.class, args);
	}


}
