package com.entradasonline.entradasonline.servicio;

import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.entidad.Usuario;
import com.entradasonline.entradasonline.repositorio.EspectaculoJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspectaculoServiceImpl implements EspectaculoService {
    private final EspectaculoJpaRepository repository;

    public EspectaculoServiceImpl(EspectaculoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Espectaculo> getAll() {
        return this.repository.getAll();
    }

    @Override
    public List<Espectaculo> getBanners() {
        List<Espectaculo> allBanners = this.repository.getBanners();
        return allBanners.subList(0, 3);
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

    @Override
    public Espectaculo save(Espectaculo espectaculo) {
        if (this.showExist(espectaculo.getNombre())){
            throw new RuntimeException("Ya existe un show con el nombre ingresado.");
        }
        return this.repository.save(espectaculo);
    }

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
