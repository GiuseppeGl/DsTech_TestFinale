package it.service.myservice.service;

import it.service.myservice.exception.ResourceNotFoundException;
import it.service.myservice.object.dto.ProdottoDTO;
import it.service.myservice.object.entity.Prodotto;
import it.service.myservice.repository.ProdottoRepository;
import it.service.myservice.service.ProdottoService;
import it.service.myservice.tools.DevTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProdottoServiceImpl implements ProdottoService {

    private final ProdottoRepository prodottoRepository;

    @Override
    public List<ProdottoDTO> getAllProdotti() {
        return DevTools.convertToProdottoDTOList(prodottoRepository.findAll());
    }

    @Override
    public ProdottoDTO getProdottoById(Long id) {
        return prodottoRepository.findById(id)
                .map(DevTools::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto non trovato con id: " + id));
    }

    @Override
    @Transactional
    public ProdottoDTO createProdotto(ProdottoDTO prodottoDTO) {
        Prodotto prodotto = DevTools.convertToEntity(prodottoDTO);
        prodotto = prodottoRepository.save(prodotto);
        return DevTools.convertToDTO(prodotto);
    }
}