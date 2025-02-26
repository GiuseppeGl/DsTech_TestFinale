package it.service.myservice.controller;

import it.service.myservice.object.dto.OrdineCreateDTO;
import it.service.myservice.object.dto.OrdineDTO;
import it.service.myservice.object.dto.OrdineUpdateStatoDTO;
import it.service.myservice.service.OrdineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordini")
@RequiredArgsConstructor
public class OrdineController {

    private final OrdineService ordineService;

    @GetMapping
    public ResponseEntity<List<OrdineDTO>> getAllOrdini() {
        return ResponseEntity.ok(ordineService.getAllOrdini());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdineDTO> getOrdineById(@PathVariable Long id) {
        return ResponseEntity.ok(ordineService.getOrdineById(id));
    }

    @PostMapping
    public ResponseEntity<OrdineDTO> createOrdine(@RequestBody OrdineCreateDTO ordineCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ordineService.createOrdine(ordineCreateDTO));
    }

    @PutMapping("/{id}/stato")
    public ResponseEntity<OrdineDTO> updateStatoOrdine(@PathVariable Long id,
                                                       @RequestBody OrdineUpdateStatoDTO ordineUpdateStatoDTO) {
        return ResponseEntity.ok(ordineService.updateStatoOrdine(id, ordineUpdateStatoDTO));
    }
}