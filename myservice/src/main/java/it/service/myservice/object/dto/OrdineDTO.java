package it.service.myservice.object.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdineDTO {
    private Long id;
    private LocalDate data;
    private String stato;
    private Double totale;
    private Long utenteId;
    private List<DettaglioOrdineDTO> dettagli;
}