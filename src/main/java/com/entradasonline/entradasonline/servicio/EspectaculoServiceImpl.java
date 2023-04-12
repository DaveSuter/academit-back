package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.negocio.dto.EspectaculoDTO;
import com.entradasonline.entradasonline.negocio.dto.mapper.EspectaculoMapper;
import com.entradasonline.entradasonline.repositorio.EspectaculoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
    public List<Espectaculo> getBanners() {
        //List<Espectaculo> allBanners = this.repository.getBanners();
        //return allBanners.subList(0, 3);
        List<Espectaculo> banners = this.repository.findTop3ByImgBanerContaining("banner");
        return banners;
    }

    @Override
    public Optional<Espectaculo> findById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<Espectaculo> findByNombre(String nombre) {
        return this.repository.buscarPorNombre(nombre);
    }

    @Override
    public boolean showExist(String nombre) {
        return this.repository.buscarPorNombre(nombre).isPresent();
    }

    @Transactional
    @Override
    public Espectaculo save(Espectaculo espectaculo) {
        if (this.showExist(espectaculo.getNombre())){
            throw new RuntimeException("Ya existe un show con el nombre ingresado.");
        }
        return this.repository.save(espectaculo);
    }

    @Transactional
    @Override
    public Espectaculo update(int id, Espectaculo espectaculo) {
        Espectaculo espectaculoUpdate;
        Optional<Espectaculo> oEspectaculo = this.repository.findById(id);

        if (oEspectaculo.isPresent()){
            espectaculoUpdate = oEspectaculo.get();
        } else {
            throw new RuntimeException("No se ha encontrado el show que desea modificar.");
        }

        espectaculoUpdate.setNombre(espectaculo.getNombre());
        espectaculoUpdate.setDescripcion(espectaculo.getDescripcion());
        espectaculoUpdate.setImgCard(espectaculo.getImgCard());
        espectaculoUpdate.setFecha(espectaculo.getFecha());
        espectaculoUpdate.setImgBaner(espectaculo.getImgBaner());
        espectaculoUpdate.setGenero(espectaculo.getGenero());
        espectaculoUpdate.setRecinto(espectaculo.getRecinto());
        return this.repository.save(espectaculoUpdate);
    }

    @Transactional
    @Override
    public void delete(int id) {
        Espectaculo espectaculo;
        Optional<Espectaculo> oEspectaculo = this.repository.findById(id);
        if (oEspectaculo.isPresent()){
            espectaculo = oEspectaculo.get();
        }else {
            throw new RuntimeException("No se ha encontrado el show que desea eliminar.");
        }
        this.repository.deleteById(espectaculo.getIdshow());
    }

}
