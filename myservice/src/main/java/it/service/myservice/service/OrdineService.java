package it.service.myservice.service;

import it.service.myservice.object.dto.OrdineCreateDTO;
import it.service.myservice.object.dto.OrdineDTO;
import it.service.myservice.object.dto.OrdineUpdateStatoDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaccia del servizio per la gestione degli ordini.
 * Definisce le operazioni disponibili per gli ordini.
 */
public interface OrdineService {
    /**
     * Recupera tutti gli ordini dal database.
     *
     * @return lista di tutti gli ordini
     */
    List<OrdineDTO> getAllOrdini();

    /**
     * Recupera un ordine specifico tramite il suo ID.
     *
     * @param id ID dell'ordine da recuperare
     * @return i dettagli dell'ordine
     */
    OrdineDTO getOrdineById(Long id);

    /**
     * Crea un nuovo ordine nel sistema.
     *
     * @param ordineCreateDTO dati per la creazione dell'ordine
     * @return i dettagli dell'ordine creato
     */
    OrdineDTO createOrdine(OrdineCreateDTO ordineCreateDTO);

    /**
     * Aggiorna lo stato di un ordine esistente.
     *
     * @param id ID dell'ordine da aggiornare
     * @param ordineUpdateStatoDTO nuovo stato dell'ordine
     * @return i dettagli dell'ordine aggiornato
     */
    OrdineDTO updateStatoOrdine(Long id, OrdineUpdateStatoDTO ordineUpdateStatoDTO);

    /**
     * Calcola il totale di un ordine in base ai suoi dettagli.
     *
     * @param ordineId ID dell'ordine per cui calcolare il totale
     */
    void calcolaTotaleOrdine(Long ordineId);

    /**
     * Calcola il totale speso da un utente in tutti i suoi ordini.
     *
     * @param utenteId ID dell'utente
     * @return totale speso dall'utente
     */
    Double getTotaleSpesoDaUtente(Long utenteId);

    /**
     * Recupera tutti gli ordini effettuati in un intervallo di date.
     *
     * @param startDate data di inizio dell'intervallo
     * @param endDate data di fine dell'intervallo
     * @return lista degli ordini nell'intervallo specificato
     */
    List<OrdineDTO> getOrdiniInIntervalloDiDate(LocalDate startDate, LocalDate endDate);
}