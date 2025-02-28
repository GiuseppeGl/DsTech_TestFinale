package it.service.myservice.controller;

import it.service.myservice.object.dto.OrdineCreateDTO;
import it.service.myservice.object.dto.OrdineDTO;
import it.service.myservice.object.dto.OrdineUpdateStatoDTO;
import it.service.myservice.service.OrdineService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller REST per gestire le operazioni sugli ordini.
 * Espone endpoint per creare, recuperare e aggiornare ordini.
 */
@RestController
@RequestMapping("/api/ordini")
@RequiredArgsConstructor
public class OrdineController {

    private final OrdineService ordineService;

    /**
     * Recupera tutti gli ordini.
     *
     * @return ResponseEntity con lista di tutti gli ordini
     */
    @GetMapping
    public ResponseEntity<List<OrdineDTO>> getAllOrdini() {
        return ResponseEntity.ok(ordineService.getAllOrdini());
    }

    /**
     * Recupera un ordine specifico tramite ID.
     *
     * @param id ID dell'ordine da recuperare
     * @return ResponseEntity con i dettagli dell'ordine
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdineDTO> getOrdineById(@PathVariable Long id) {
        return ResponseEntity.ok(ordineService.getOrdineById(id));
    }

    /**
     * Crea un nuovo ordine.
     *
     * @param ordineCreateDTO dati per la creazione dell'ordine
     * @return ResponseEntity con i dettagli dell'ordine creato
     */
    @PostMapping
    public ResponseEntity<OrdineDTO> createOrdine(@RequestBody OrdineCreateDTO ordineCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ordineService.createOrdine(ordineCreateDTO));
    }

    /**
     * Aggiorna lo stato di un ordine esistente.
     *
     * @param id ID dell'ordine da aggiornare
     * @param ordineUpdateStatoDTO nuovo stato dell'ordine
     * @return ResponseEntity con i dettagli dell'ordine aggiornato
     */
    @PutMapping("/{id}/stato")
    public ResponseEntity<OrdineDTO> updateStatoOrdine(@PathVariable Long id,
                                                       @RequestBody OrdineUpdateStatoDTO ordineUpdateStatoDTO) {
        return ResponseEntity.ok(ordineService.updateStatoOrdine(id, ordineUpdateStatoDTO));
    }

    /**
     * Endpoint per ottenere il totale speso da un utente.
     *
     * @param userId ID dell'utente per cui calcolare il totale speso
     * @return ResponseEntity contenente il totale speso dall'utente
     */
    @GetMapping("/totale-speso/{userId}")
    public ResponseEntity<Map<String, Object>> getTotaleSpeso(@PathVariable Long userId) {
        Double totale = ordineService.getTotaleSpesoDaUtente(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("utenteId", userId);
        response.put("totaleSpeso", totale);

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint per ottenere gli ordini in un intervallo di date.
     *
     * @param startDate Data di inizio dell'intervallo (formato YYYY-MM-DD)
     * @param endDate Data di fine dell'intervallo (formato YYYY-MM-DD)
     * @return ResponseEntity contenente la lista degli ordini nell'intervallo
     */
    @GetMapping("/intervallo")
    public ResponseEntity<List<OrdineDTO>> getOrdiniInIntervallo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<OrdineDTO> ordini = ordineService.getOrdiniInIntervalloDiDate(startDate, endDate);
        return ResponseEntity.ok(ordini);
    }
}