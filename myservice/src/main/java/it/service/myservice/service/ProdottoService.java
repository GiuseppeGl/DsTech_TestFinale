package it.service.myservice.service;

import it.service.myservice.object.dto.ProdottoDTO;

import java.util.List;

public interface ProdottoService {
    List<ProdottoDTO> getAllProdotti();
    ProdottoDTO getProdottoById(Long id);
    ProdottoDTO createProdotto(ProdottoDTO prodottoDTO);
}