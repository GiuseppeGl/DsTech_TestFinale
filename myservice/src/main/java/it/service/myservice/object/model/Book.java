package it.service.myservice.object.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String autore;

    @Column(name = "anno_pubblicazione", nullable = false)
    private Integer annoPubblicazione;

    @Column(nullable = false)
    @Builder.Default
    private Boolean disponibile = true;
}