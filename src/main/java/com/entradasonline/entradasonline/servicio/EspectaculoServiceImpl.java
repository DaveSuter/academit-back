package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.exception.BadRequestException;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.exception.NotFoundException;
import com.entradasonline.entradasonline.negocio.dto.EspectaculoDTO;
import com.entradasonline.entradasonline.negocio.dto.mapper.EspectaculoMapper;
import com.entradasonline.entradasonline.repositorio.EspectaculoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspectaculoServiceImpl implements EspectaculoService {

    private final String ERROR_NOT_FOUND = "An error occurred in the process: ";
    private final EspectaculoJpaRepository repository;

    @Override
    public List<EspectaculoDTO> getAll() throws ErrorProcessException {
        try {
            return repository.getAll().stream()
                    .map(EspectaculoMapper::entityToDto)
                    .collect(Collectors.toList());
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }
    }

    @Override
    public List<EspectaculoDTO> getBanners() throws ErrorProcessException {
        try {
            return repository.findTop3ByImgBanerContaining("banner")
                    .stream()
                    .map(EspectaculoMapper::entityToDto)
                    .collect(Collectors.toList());
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }
    }

    @Override
    public EspectaculoDTO findById(int id) throws ErrorProcessException {
        Espectaculo espectaculo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("this show cannot found in the database"));
        try {
            return EspectaculoMapper.entityToDto(espectaculo); //utilizo el mapper en lugar del builder
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }
    }

    @Override
    public EspectaculoDTO findByNombre(String nombre) throws ErrorProcessException {
        Espectaculo espectaculo = repository.findByNombre(nombre)
                .orElseThrow(() -> new NotFoundException("this show cannot found in the database"));
        try {
            return EspectaculoMapper.entityToDto(espectaculo);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }
    }

    @Override
    public boolean showExist(int id) throws ErrorProcessException {
        try {
            return repository.findByIdshow(id).isPresent();
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }
    }

    @Transactional
    @Override
    public EspectaculoDTO save(EspectaculoDTO espectaculoDTO) throws ErrorProcessException {
        Espectaculo espectaculo = repository.buscarPorNombre(espectaculoDTO.getNombre());
        if (espectaculo != null){
            throw new BadRequestException("this show already exist");
        }
        try {
            return EspectaculoMapper.entityToDto(repository.save(espectaculo));
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }
    }

    @Transactional
    @Override
    public Espectaculo update(int id, EspectaculoDTO espectaculoDto) throws ErrorProcessException {
        Espectaculo espectaculoUpdate = repository.findByIdshow(id)
                .orElseThrow(() -> new NotFoundException("this show cannot found in the database"));
        try{
            espectaculoUpdate.setNombre(espectaculoDto.getNombre());
            espectaculoUpdate.setDescripcion(espectaculoDto.getDescripcion());
            espectaculoUpdate.setImgCard(espectaculoDto.getImgcard());
            espectaculoUpdate.setFecha(espectaculoDto.getFecha());
            espectaculoUpdate.setImgBaner(espectaculoDto.getImgbaner());
            espectaculoUpdate.setGenero(espectaculoDto.getGenero());
            espectaculoUpdate.setRecinto(espectaculoDto.getRecinto());
            return repository.save(espectaculoUpdate);
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(int id) throws ErrorProcessException {
        Espectaculo espectaculo = repository.findByIdshow(id)
                .orElseThrow(() -> new NotFoundException("this show cannot found in the database"));
        try {
            repository.deleteById(espectaculo.getIdshow());
        } catch (RuntimeException ex){
            throw new ErrorProcessException(ERROR_NOT_FOUND + ex.getMessage());
        }

    }

}
