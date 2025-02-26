package it.service.myservice.object.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DettaglioOrdineCreateDTO {
    private Long ordineId;
    private Long prodottoId;
    private Integer quantita;
}