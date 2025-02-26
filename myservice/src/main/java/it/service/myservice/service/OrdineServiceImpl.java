package it.service.myservice.service;

import it.service.myservice.exception.ResourceNotFoundException;
import it.service.myservice.mapper.OrdineMapper;
import it.service.myservice.object.dto.OrdineCreateDTO;
import it.service.myservice.object.dto.OrdineDTO;
import it.service.myservice.object.dto.OrdineUpdateStatoDTO;
import it.service.myservice.object.entity.Ordine;
import it.service.myservice.object.entity.Utente;
import it.service.myservice.repository.OrdineRepository;
import it.service.myservice.repository.UtenteRepository;
import it.service.myservice.service.OrdineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdineServiceImpl implements OrdineService {

    private final OrdineRepository ordineRepository;
    private final UtenteRepository utenteRepository;
    private final OrdineMapper ordineMapper;

    @Override
    public List<OrdineDTO> getAllOrdini() {
        return ordineMapper.toDtoList(ordineRepository.findAll());
    }

    @Override
    public OrdineDTO getOrdineById(Long id) {
        return ordineRepository.findById(id)
                .map(ordineMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + id));
    }

    @Override
    @Transactional
    public OrdineDTO createOrdine(OrdineCreateDTO ordineCreateDTO) {
        Utente utente = utenteRepository.findById(ordineCreateDTO.getUtenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id: " + ordineCreateDTO.getUtenteId()));

        Ordine ordine = ordineMapper.toEntity(ordineCreateDTO);
        ordine.setUtente(utente);
        ordine.setData(LocalDate.now());
        ordine.setStato("IN_ATTESA");
        ordine.setTotale(0.0);

        ordine = ordineRepository.save(ordine);
        return ordineMapper.toDto(ordine);
    }

    @Override
    @Transactional
    public OrdineDTO updateStatoOrdine(Long id, OrdineUpdateStatoDTO ordineUpdateStatoDTO) {
        Ordine ordine = ordineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + id));

        // Validazione stato
        String stato = ordineUpdateStatoDTO.getStato();
        if (!stato.equals("IN_ATTESA") && !stato.equals("SPEDITO") && !stato.equals("CONSEGNATO")) {
            throw new IllegalArgumentException("Stato non valido. Valori ammessi: IN_ATTESA, SPEDITO, CONSEGNATO");
        }

        ordineMapper.updateStato(ordineUpdateStatoDTO, ordine);
        ordine = ordineRepository.save(ordine);
        return ordineMapper.toDto(ordine);
    }

    @Override
    @Transactional
    public void calcolaTotaleOrdine(Long ordineId) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + ordineId));

        double totale = ordine.getDettagli().stream()
                .mapToDouble(dettaglio -> dettaglio.getPrezzoTotale())
                .sum();

        ordine.setTotale(totale);
        ordineRepository.save(ordine);
    }
}