package it.service.myservice.service;

import it.service.myservice.exception.ResourceAlreadyExistsException;
import it.service.myservice.exception.ResourceNotFoundException;
import it.service.myservice.mapper.UtenteMapper;
import it.service.myservice.object.dto.UtenteCreateDTO;
import it.service.myservice.object.dto.UtenteDTO;
import it.service.myservice.object.entity.Utente;
import it.service.myservice.repository.UtenteRepository;
import it.service.myservice.service.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository utenteRepository;
    private final UtenteMapper utenteMapper;

    @Override
    public List<UtenteDTO> getAllUtenti() {
        return utenteMapper.toDtoList(utenteRepository.findAll());
    }

    @Override
    public UtenteDTO getUtenteById(Long id) {
        return utenteRepository.findById(id)
                .map(utenteMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id: " + id));
    }

    @Override
    @Transactional
    public UtenteDTO createUtente(UtenteCreateDTO utenteCreateDTO) {
        if (utenteRepository.existsByEmail(utenteCreateDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Utente già esistente con email: " + utenteCreateDTO.getEmail());
        }

        Utente utente = utenteMapper.toEntity(utenteCreateDTO);
        utente = utenteRepository.save(utente);
        return utenteMapper.toDto(utente);
    }
}