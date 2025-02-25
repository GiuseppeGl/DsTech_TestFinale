package it.service.myservice.object.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String titolo;
    private String autore;
    private Integer annoPubblicazione;
    private Boolean disponibile;
}