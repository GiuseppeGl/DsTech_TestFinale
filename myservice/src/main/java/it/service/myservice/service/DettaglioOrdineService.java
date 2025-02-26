package it.service.myservice.service;

import it.service.myservice.object.dto.DettaglioOrdineCreateDTO;
import it.service.myservice.object.dto.DettaglioOrdineDTO;

public interface DettaglioOrdineService {
    DettaglioOrdineDTO aggiungiProdottoAOrdine(DettaglioOrdineCreateDTO dettaglioOrdineCreateDTO);
}