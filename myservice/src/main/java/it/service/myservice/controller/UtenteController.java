package it.service.myservice.controller;

import it.service.myservice.object.dto.UtenteCreateDTO;
import it.service.myservice.object.dto.UtenteDTO;
import it.service.myservice.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteService utenteService;

    @GetMapping
    public ResponseEntity<List<UtenteDTO>> getAllUtenti() {
        return ResponseEntity.ok(utenteService.getAllUtenti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getUtenteById(@PathVariable Long id) {
        return ResponseEntity.ok(utenteService.getUtenteById(id));
    }

    @PostMapping
    public ResponseEntity<UtenteDTO> createUtente(@RequestBody UtenteCreateDTO utenteCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(utenteService.createUtente(utenteCreateDTO));
    }
}