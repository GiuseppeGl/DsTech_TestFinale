package it.service.myservice.service;

import it.service.myservice.object.dto.OrdineCreateDTO;
import it.service.myservice.object.dto.OrdineDTO;
import it.service.myservice.object.dto.OrdineUpdateStatoDTO;

import java.util.List;

public interface OrdineService {
    List<OrdineDTO> getAllOrdini();
    OrdineDTO getOrdineById(Long id);
    OrdineDTO createOrdine(OrdineCreateDTO ordineCreateDTO);
    OrdineDTO updateStatoOrdine(Long id, OrdineUpdateStatoDTO ordineUpdateStatoDTO);
    void calcolaTotaleOrdine(Long ordineId);
}