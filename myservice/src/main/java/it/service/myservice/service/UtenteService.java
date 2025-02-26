package it.service.myservice.service;

import it.service.myservice.object.dto.UtenteCreateDTO;
import it.service.myservice.object.dto.UtenteDTO;

import java.util.List;

public interface UtenteService {
    List<UtenteDTO> getAllUtenti();
    UtenteDTO getUtenteById(Long id);
    UtenteDTO createUtente(UtenteCreateDTO utenteCreateDTO);
}