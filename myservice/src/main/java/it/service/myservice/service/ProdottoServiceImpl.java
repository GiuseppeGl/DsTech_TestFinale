package it.service.myservice.service;

import it.service.myservice.exception.ResourceNotFoundException;
import it.service.myservice.mapper.ProdottoMapper;
import it.service.myservice.object.dto.ProdottoDTO;
import it.service.myservice.object.entity.Prodotto;
import it.service.myservice.repository.ProdottoRepository;
import it.service.myservice.service.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProdottoServiceImpl implements ProdottoService {

    private final ProdottoRepository prodottoRepository;
    private final ProdottoMapper prodottoMapper;

    @Override
    public List<ProdottoDTO> getAllProdotti() {
        return prodottoMapper.toDtoList(prodottoRepository.findAll());
    }

    @Override
    public ProdottoDTO getProdottoById(Long id) {
        return prodottoRepository.findById(id)
                .map(prodottoMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto non trovato con id: " + id));
    }

    @Override
    @Transactional
    public ProdottoDTO createProdotto(ProdottoDTO prodottoDTO) {
        Prodotto prodotto = prodottoMapper.toEntity(prodottoDTO);
        prodotto = prodottoRepository.save(prodotto);
        return prodottoMapper.toDto(prodotto);
    }
}