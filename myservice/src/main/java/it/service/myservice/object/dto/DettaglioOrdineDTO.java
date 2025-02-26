package it.service.myservice.object.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DettaglioOrdineDTO {
    private Long id;
    private Integer quantita;
    private Double prezzoTotale;
    private Long prodottoId;
    private String nomeProdotto;
}