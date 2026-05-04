package cl.bibliotecaam.empleado.empleado.service;

import cl.bibliotecaam.empleado.empleado.dto.EmpleadoRequestDTO;
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
                empleado.getIdEmp(),
                empleado.getNumrunEmp(),
                empleado.getDvrunEmp(),
                empleado.getPnombreEmp(),
                empleado.getSnombreEmp(),
                empleado.getAppaternoEmp(),
                empleado.getApmaternoEmp(),
                empleado.getFecContrato(),
                empleado.getSueldo()
        );
    }

    public List<EmpleadoResponseDTO> listarTodos(){
        return empleadoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmpleadoResponseDTO> obtenerPorId(Long id){
        return empleadoRepository.findById(id).map(this::mapToDTO);
    }

    public Optional<EmpleadoResponseDTO> obtenerPorRun(Long numrun){
        return empleadoRepository.findByNumrunEmp(numrun)
                .stream().map(this::mapToDTO).findFirst();
    }

    public List<EmpleadoResponseDTO> listarPorAppaterno(String appaterno){
        return empleadoRepository.findByAppaternoEmp(appaterno)
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

    public Empleado guardar(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public void eliminarPorId(Long id){
        empleadoRepository.deleteById(id);
    }

    public Optional<EmpleadoResponseDTO> actualizar(Long id, EmpleadoRequestDTO doto){
        return empleadoRepository.findById(id).map(existente -> {
            existente.setNumrunEmp(doto.getNumrun_emp());
            existente.setDvrunEmp(doto.getDvrun_emp());
            existente.setPnombreEmp(doto.getPnombre_emp());
            existente.setSnombreEmp(doto.getSnombre_emp());
            existente.setAppaternoEmp(doto.getAppaterno_emp());
            existente.setApmaternoEmp(doto.getApmaterno_emp());
            existente.setFecContrato(doto.getFec_contrato());
            existente.setSueldo(doto.getSueldo());
            return mapToDTO(empleadoRepository.save(existente));
        });
    }

    public Optional<List<EmpleadoResponseDTO>> obtenerPorMonto(Long monto){
        return Optional
                .of(empleadoRepository.findEmpleadoBySueldo(monto)
                        .stream()
                        .map((this::mapToDTO))
                        .collect(Collectors.toList()));
    }
}
