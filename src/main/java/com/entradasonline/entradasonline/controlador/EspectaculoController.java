package com.entradasonline.entradasonline.controlador;

import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.negocio.dto.EspectaculoDTO;
import com.entradasonline.entradasonline.negocio.dto.mapper.EspectaculoMapper;
import com.entradasonline.entradasonline.servicio.EspectaculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/shows")
public class EspectaculoController {

    private final EspectaculoService service;

    public EspectaculoController(EspectaculoService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<EspectaculoDTO>> getAllShows(){
        List<Espectaculo> espectaculos = this.service.getAll();
        List<EspectaculoDTO> espectaculoDTOList = espectaculos
                .stream()
                .map(EspectaculoMapper::entityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(espectaculoDTOList);
    }

    @GetMapping("/dtos")
    public ResponseEntity<List<EspectaculoDTO>> getAllShowsDto(){

        List<Espectaculo> espectaculos = this.service.getAll();
        List<EspectaculoDTO> dtos = espectaculos
                .stream()
                .map(EspectaculoMapper::entityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/banners")
    public ResponseEntity<List<EspectaculoDTO>> getBannerShows(){
        List<Espectaculo> espectaculos = this.service.getBanners();
        List<EspectaculoDTO> dtos = espectaculos
                .stream()
                .map(EspectaculoMapper::entityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/")
    public ResponseEntity<?> crearShow(@Valid @RequestBody Espectaculo espectaculo){
        Espectaculo eCreado;
        try {
            eCreado = service.save(espectaculo);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + " : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Show creado correctamente.\n" + eCreado.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarShow(@PathVariable int id, @RequestBody Espectaculo espectaculo){
        Espectaculo uEspectaculo;
        try {
            uEspectaculo = service.update(id, espectaculo);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + " : " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Show editado con éxito.\n" + uEspectaculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarShow(@PathVariable int id){
        try {
            this.service.delete(id);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Borrado exitoso del show con ID: " + id);
    }
}
