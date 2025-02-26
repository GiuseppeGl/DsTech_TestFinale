package it.service.myservice.tools;

import it.service.myservice.object.dto.UtenteDTO;
import it.service.myservice.object.dto.OrdineDTO;
import it.service.myservice.object.dto.ProdottoDTO;
import it.service.myservice.object.dto.DettaglioOrdineDTO;
import it.service.myservice.object.entity.Utente;
import it.service.myservice.object.entity.Ordine;
import it.service.myservice.object.entity.Prodotto;
import it.service.myservice.object.entity.DettaglioOrdine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DevTools {

    // Conversioni Utente
    public static UtenteDTO convertToDTO(Utente utente) {
        if (utente == null) return null;
        UtenteDTO dto = new UtenteDTO();
        dto.setId(utente.getId());
        dto.setNome(utente.getNome());
        dto.setEmail(utente.getEmail());

        if (utente.getOrdini() != null) {
            dto.setOrdini(utente.getOrdini().stream()
                    .map(DevTools::convertToDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static Utente convertToEntity(UtenteDTO dto) {
        if (dto == null) return null;
        return Utente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .email(dto.getEmail())
                .build();
    }

    public static List<UtenteDTO> convertToUtenteDTOList(List<Utente> utenti) {
        if (utenti == null) return new ArrayList<>();
        return utenti.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }

    // Conversioni Ordine
    public static OrdineDTO convertToDTO(Ordine ordine) {
        if (ordine == null) return null;
        OrdineDTO dto = new OrdineDTO();
        dto.setId(ordine.getId());
        dto.setData(ordine.getData());
        dto.setStato(ordine.getStato());
        dto.setTotale(ordine.getTotale());

        if (ordine.getUtente() != null) {
            dto.setUtenteId(ordine.getUtente().getId());
        }

        if (ordine.getDettagli() != null) {
            dto.setDettagli(ordine.getDettagli().stream()
                    .map(DevTools::convertToDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static Ordine convertToEntity(OrdineDTO dto) {
        if (dto == null) return null;
        return Ordine.builder()
                .id(dto.getId())
                .data(dto.getData())
                .stato(dto.getStato())
                .totale(dto.getTotale())
                .build();
    }

    public static List<OrdineDTO> convertToOrdineDTOList(List<Ordine> ordini) {
        if (ordini == null) return new ArrayList<>();
        return ordini.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }

    // Conversioni Prodotto
    public static ProdottoDTO convertToDTO(Prodotto prodotto) {
        if (prodotto == null) return null;
        ProdottoDTO dto = new ProdottoDTO();
        dto.setId(prodotto.getId());
        dto.setNome(prodotto.getNome());
        dto.setPrezzo(prodotto.getPrezzo());

        return dto;
    }

    public static Prodotto convertToEntity(ProdottoDTO dto) {
        if (dto == null) return null;
        return Prodotto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .prezzo(dto.getPrezzo())
                .build();
    }

    public static List<ProdottoDTO> convertToProdottoDTOList(List<Prodotto> prodotti) {
        if (prodotti == null) return new ArrayList<>();
        return prodotti.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }

    // Conversioni DettaglioOrdine
    public static DettaglioOrdineDTO convertToDTO(DettaglioOrdine dettaglio) {
        if (dettaglio == null) return null;
        DettaglioOrdineDTO dto = new DettaglioOrdineDTO();
        dto.setId(dettaglio.getId());
        dto.setQuantita(dettaglio.getQuantita());
        dto.setPrezzoTotale(dettaglio.getPrezzoTotale());

        if (dettaglio.getProdotto() != null) {
            dto.setProdottoId(dettaglio.getProdotto().getId());
            dto.setNomeProdotto(dettaglio.getProdotto().getNome());
        }

        return dto;
    }

    public static DettaglioOrdine convertToEntity(DettaglioOrdineDTO dto) {
        if (dto == null) return null;
        return DettaglioOrdine.builder()
                .id(dto.getId())
                .quantita(dto.getQuantita())
                .prezzoTotale(dto.getPrezzoTotale())
                .build();
    }

    public static List<DettaglioOrdineDTO> convertToDettaglioDTOList(List<DettaglioOrdine> dettagli) {
        if (dettagli == null) return new ArrayList<>();
        return dettagli.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }
}