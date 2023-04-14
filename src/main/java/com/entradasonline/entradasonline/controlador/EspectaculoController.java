package com.entradasonline.entradasonline.controlador;

import com.entradasonline.entradasonline.entidad.Espectaculo;
import com.entradasonline.entradasonline.exception.ErrorProcessException;
import com.entradasonline.entradasonline.negocio.dto.EspectaculoDTO;
import com.entradasonline.entradasonline.negocio.dto.mapper.EspectaculoMapper;
import com.entradasonline.entradasonline.servicio.EspectaculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class EspectaculoController {

    private final EspectaculoService service;

    @GetMapping("")
    public ResponseEntity<List<EspectaculoDTO>> getAllShows() throws ErrorProcessException {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/banners")
    public ResponseEntity<List<EspectaculoDTO>> getBannerShows() throws ErrorProcessException {
        return ResponseEntity.ok(service.getBanners());
    }

    @PostMapping("/")
    public ResponseEntity<EspectaculoDTO> addShow(@Valid @RequestBody EspectaculoDTO espectaculoDTO)
            throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(espectaculoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarShow(@PathVariable int id, @RequestBody EspectaculoDTO espectaculoDTO)
            throws ErrorProcessException{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, espectaculoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarShow(@PathVariable int id) throws ErrorProcessException{
            service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Borrado exitoso del show con ID: " + id);
    }
}
