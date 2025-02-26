package it.service.myservice.service;

import it.service.myservice.exception.ResourceNotFoundException;
import it.service.myservice.object.dto.DettaglioOrdineCreateDTO;
import it.service.myservice.object.dto.DettaglioOrdineDTO;
import it.service.myservice.object.entity.DettaglioOrdine;
import it.service.myservice.object.entity.Ordine;
import it.service.myservice.object.entity.Prodotto;
import it.service.myservice.repository.DettaglioOrdineRepository;
import it.service.myservice.repository.OrdineRepository;
import it.service.myservice.repository.ProdottoRepository;
import it.service.myservice.service.DettaglioOrdineService;
import it.service.myservice.service.OrdineService;
import it.service.myservice.tools.DevTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DettaglioOrdineServiceImpl implements DettaglioOrdineService {

    private final DettaglioOrdineRepository dettaglioOrdineRepository;
    private final OrdineRepository ordineRepository;
    private final ProdottoRepository prodottoRepository;
    private final OrdineService ordineService;

    @Override
    @Transactional
    public DettaglioOrdineDTO aggiungiProdottoAOrdine(DettaglioOrdineCreateDTO dettaglioOrdineCreateDTO) {
        Ordine ordine = ordineRepository.findById(dettaglioOrdineCreateDTO.getOrdineId())
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + dettaglioOrdineCreateDTO.getOrdineId()));

        Prodotto prodotto = prodottoRepository.findById(dettaglioOrdineCreateDTO.getProdottoId())
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto non trovato con id: " + dettaglioOrdineCreateDTO.getProdottoId()));

        DettaglioOrdine dettaglioOrdine = new DettaglioOrdine();
        dettaglioOrdine.setOrdine(ordine);
        dettaglioOrdine.setProdotto(prodotto);
        dettaglioOrdine.setQuantita(dettaglioOrdineCreateDTO.getQuantita());

        // Calcolo del prezzo totale del dettaglio
        double prezzoTotale = prodotto.getPrezzo() * dettaglioOrdineCreateDTO.getQuantita();
        dettaglioOrdine.setPrezzoTotale(prezzoTotale);

        dettaglioOrdine = dettaglioOrdineRepository.save(dettaglioOrdine);

        // Aggiorna il totale dell'ordine
        ordineService.calcolaTotaleOrdine(ordine.getId());

        return DevTools.convertToDTO(dettaglioOrdine);
    }
}