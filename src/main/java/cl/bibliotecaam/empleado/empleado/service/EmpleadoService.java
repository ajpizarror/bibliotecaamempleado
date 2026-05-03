package cl.bibliotecaam.empleado.empleado.service;

import cl.bibliotecaam.empleado.empleado.dto.EmpleadoResponseDTO;
import cl.bibliotecaam.empleado.empleado.model.Empleado;
import cl.bibliotecaam.empleado.empleado.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    private EmpleadoResponseDTO mapToDTO(Empleado empleado){
        return new EmpleadoResponseDTO(
                empleado.getId_emp(),
                empleado.getNumrun_emp(),
                empleado.getDvrun_emp(),
                empleado.getPnombre_emp(),
                empleado.getSnombre_emp(),
                empleado.getAppaterno_emp(),
                empleado.getApmaterno_emp(),
                empleado.getFec_contrato(),
                empleado.getSueldo()
        );
    }

    public List<EmpleadoResponseDTO> listarTodos(){
        return empleadoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmpleadoResponseDTO> listarPorRun(Long numrun){
        return empleadoRepository.findByNumrun_emp(numrun)
                .stream().map(this::mapToDTO).findFirst();
    }

    public List<EmpleadoResponseDTO> listarPorAppaterno(String appaterno){
        return empleadoRepository.findByAppaterno_emp(appaterno)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<EmpleadoResponseDTO> listarPorSueldo(Long sueldo){
        return empleadoRepository.findBySueldo(sueldo)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
