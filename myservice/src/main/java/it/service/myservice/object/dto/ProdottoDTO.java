package it.service.myservice.object.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdottoDTO {
    private Long id;
    private String nome;
    private Double prezzo;
}