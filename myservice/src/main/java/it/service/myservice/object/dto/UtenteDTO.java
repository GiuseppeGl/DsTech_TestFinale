package it.service.myservice.object.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteDTO {
    private Long id;
    private String nome;
    private String email;
    private List<OrdineDTO> ordini;
}