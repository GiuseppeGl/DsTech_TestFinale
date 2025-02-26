package it.service.myservice.controller;

import it.service.myservice.object.dto.ProdottoDTO;
import it.service.myservice.service.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
@RequiredArgsConstructor
public class ProdottoController {

    private final ProdottoService prodottoService;

    @GetMapping
    public ResponseEntity<List<ProdottoDTO>> getAllProdotti() {
        return ResponseEntity.ok(prodottoService.getAllProdotti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoDTO> getProdottoById(@PathVariable Long id) {
        return ResponseEntity.ok(prodottoService.getProdottoById(id));
    }

    @PostMapping
    public ResponseEntity<ProdottoDTO> createProdotto(@RequestBody ProdottoDTO prodottoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prodottoService.createProdotto(prodottoDTO));
    }
}