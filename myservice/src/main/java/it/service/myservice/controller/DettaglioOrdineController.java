package it.service.myservice.controller;

import it.service.myservice.object.dto.DettaglioOrdineCreateDTO;
import it.service.myservice.object.dto.DettaglioOrdineDTO;
import it.service.myservice.service.DettaglioOrdineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dettagli-ordine")
@RequiredArgsConstructor
public class DettaglioOrdineController {

    private final DettaglioOrdineService dettaglioOrdineService;

    @PostMapping
    public ResponseEntity<DettaglioOrdineDTO> aggiungiProdottoAOrdine(
            @RequestBody DettaglioOrdineCreateDTO dettaglioOrdineCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dettaglioOrdineService.aggiungiProdottoAOrdine(dettaglioOrdineCreateDTO));
    }
}