package it.service.myservice.config;

import it.service.myservice.object.entity.DettaglioOrdine;
import it.service.myservice.object.entity.Ordine;
import it.service.myservice.object.entity.Prodotto;
import it.service.myservice.object.entity.Utente;
import it.service.myservice.repository.DettaglioOrdineRepository;
import it.service.myservice.repository.OrdineRepository;
import it.service.myservice.repository.ProdottoRepository;
import it.service.myservice.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsabile dell'inizializzazione dei dati di esempio nell'applicazione.
 * Viene eseguita solo nel profilo "dev" per evitare di inserire dati di test in produzione.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile("dev") // Esegue questa configurazione solo nel profilo di sviluppo
public class DataInitializer {

    private final UtenteRepository utenteRepository;
    private final ProdottoRepository prodottoRepository;
    private final OrdineRepository ordineRepository;
    private final DettaglioOrdineRepository dettaglioOrdineRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Verifica se i dati sono già stati inizializzati
            if (utenteRepository.count() > 0) {
                log.info("Il database contiene già dei dati, l'inizializzazione viene saltata.");
                return;
            }

            log.info("Inizializzazione dei dati di esempio...");

            // Creazione e salvataggio degli utenti
            List<Utente> utenti = createUtenti();
            utenteRepository.saveAll(utenti);
            log.info("Utenti salvati: {}", utenti.size());

            // Creazione e salvataggio dei prodotti
            List<Prodotto> prodotti = createProdotti();
            prodottoRepository.saveAll(prodotti);
            log.info("Prodotti salvati: {}", prodotti.size());

            // Creazione e salvataggio degli ordini
            List<Ordine> ordini = createOrdini(utenti);
            ordineRepository.saveAll(ordini);
            log.info("Ordini salvati: {}", ordini.size());

            // Creazione e salvataggio dei dettagli ordine
            List<DettaglioOrdine> dettagliOrdine = createDettagliOrdine(ordini, prodotti);
            dettaglioOrdineRepository.saveAll(dettagliOrdine);
            log.info("Dettagli ordine salvati: {}", dettagliOrdine.size());

            // Aggiornamento dei totali degli ordini
            updateOrdiniTotali(ordini);
            ordineRepository.saveAll(ordini);
            log.info("Totali ordini aggiornati");

            log.info("Inizializzazione dei dati completata con successo!");
        };
    }

    /**
     * Crea una lista di utenti di esempio.
     *
     * @return lista di utenti
     */
    private List<Utente> createUtenti() {
        List<Utente> utenti = new ArrayList<>();

        utenti.add(Utente.builder()
                .nome("Mario Rossi")
                .email("mario.rossi@example.com")
                .build());

        utenti.add(Utente.builder()
                .nome("Luca Bianchi")
                .email("luca.bianchi@example.com")
                .build());

        utenti.add(Utente.builder()
                .nome("Giulia Verdi")
                .email("giulia.verdi@example.com")
                .build());

        return utenti;
    }

    /**
     * Crea una lista di prodotti di esempio.
     *
     * @return lista di prodotti
     */
    private List<Prodotto> createProdotti() {
        List<Prodotto> prodotti = new ArrayList<>();

        prodotti.add(Prodotto.builder()
                .nome("Laptop")
                .prezzo(1200.00)
                .build());

        prodotti.add(Prodotto.builder()
                .nome("Mouse Wireless")
                .prezzo(25.00)
                .build());

        prodotti.add(Prodotto.builder()
                .nome("Tastiera Meccanica")
                .prezzo(80.00)
                .build());

        prodotti.add(Prodotto.builder()
                .nome("Monitor 24\"")
                .prezzo(200.00)
                .build());

        prodotti.add(Prodotto.builder()
                .nome("Cuffie Bluetooth")
                .prezzo(50.00)
                .build());

        return prodotti;
    }

    /**
     * Crea una lista di ordini di esempio.
     *
     * @param utenti lista degli utenti a cui associare gli ordini
     * @return lista di ordini
     */
    private List<Ordine> createOrdini(List<Utente> utenti) {
        List<Ordine> ordini = new ArrayList<>();

        // Ordine 1: Mario Rossi
        ordini.add(Ordine.builder()
                .data(LocalDate.of(2024, 2, 1))
                .stato("IN_ATTESA")
                .totale(1225.00) // Verrà ricalcolato in base ai dettagli
                .utente(utenti.get(0))
                .dettagli(new ArrayList<>())
                .build());

        // Ordine 2: Luca Bianchi
        ordini.add(Ordine.builder()
                .data(LocalDate.of(2024, 2, 3))
                .stato("SPEDITO")
                .totale(80.00) // Verrà ricalcolato in base ai dettagli
                .utente(utenti.get(1))
                .dettagli(new ArrayList<>())
                .build());

        // Ordine 3: Giulia Verdi
        ordini.add(Ordine.builder()
                .data(LocalDate.of(2024, 2, 5))
                .stato("CONSEGNATO")
                .totale(250.00) // Verrà ricalcolato in base ai dettagli
                .utente(utenti.get(2))
                .dettagli(new ArrayList<>())
                .build());

        // Ordine 4: Mario Rossi
        ordini.add(Ordine.builder()
                .data(LocalDate.of(2024, 2, 7))
                .stato("CONSEGNATO")
                .totale(200.00) // Verrà ricalcolato in base ai dettagli
                .utente(utenti.get(0))
                .dettagli(new ArrayList<>())
                .build());

        // Ordine 5: Luca Bianchi
        ordini.add(Ordine.builder()
                .data(LocalDate.of(2024, 2, 9))
                .stato("IN_ATTESA")
                .totale(1050.00) // Verrà ricalcolato in base ai dettagli
                .utente(utenti.get(1))
                .dettagli(new ArrayList<>())
                .build());

        return ordini;
    }

    /**
     * Crea una lista di dettagli ordine di esempio.
     *
     * @param ordini lista degli ordini a cui associare i dettagli
     * @param prodotti lista dei prodotti da utilizzare
     * @return lista di dettagli ordine
     */
    private List<DettaglioOrdine> createDettagliOrdine(List<Ordine> ordini, List<Prodotto> prodotti) {
        List<DettaglioOrdine> dettagli = new ArrayList<>();

        // Dettagli Ordine 1: Mario Rossi
        DettaglioOrdine dettaglio1 = DettaglioOrdine.builder()
                .ordine(ordini.get(0))
                .prodotto(prodotti.get(0)) // Laptop
                .quantita(1)
                .prezzoTotale(1200.00)
                .build();
        dettagli.add(dettaglio1);
        ordini.get(0).getDettagli().add(dettaglio1);

        DettaglioOrdine dettaglio2 = DettaglioOrdine.builder()
                .ordine(ordini.get(0))
                .prodotto(prodotti.get(1)) // Mouse Wireless
                .quantita(1)
                .prezzoTotale(25.00)
                .build();
        dettagli.add(dettaglio2);
        ordini.get(0).getDettagli().add(dettaglio2);

        // Dettagli Ordine 2: Luca Bianchi
        DettaglioOrdine dettaglio3 = DettaglioOrdine.builder()
                .ordine(ordini.get(1))
                .prodotto(prodotti.get(2)) // Tastiera Meccanica
                .quantita(1)
                .prezzoTotale(80.00)
                .build();
        dettagli.add(dettaglio3);
        ordini.get(1).getDettagli().add(dettaglio3);

        // Dettagli Ordine 3: Giulia Verdi
        DettaglioOrdine dettaglio4 = DettaglioOrdine.builder()
                .ordine(ordini.get(2))
                .prodotto(prodotti.get(3)) // Monitor 24"
                .quantita(1)
                .prezzoTotale(200.00)
                .build();
        dettagli.add(dettaglio4);
        ordini.get(2).getDettagli().add(dettaglio4);

        DettaglioOrdine dettaglio5 = DettaglioOrdine.builder()
                .ordine(ordini.get(2))
                .prodotto(prodotti.get(4)) // Cuffie Bluetooth
                .quantita(1)
                .prezzoTotale(50.00)
                .build();
        dettagli.add(dettaglio5);
        ordini.get(2).getDettagli().add(dettaglio5);

        // Dettagli Ordine 4: Mario Rossi
        DettaglioOrdine dettaglio6 = DettaglioOrdine.builder()
                .ordine(ordini.get(3))
                .prodotto(prodotti.get(3)) // Monitor 24"
                .quantita(1)
                .prezzoTotale(200.00)
                .build();
        dettagli.add(dettaglio6);
        ordini.get(3).getDettagli().add(dettaglio6);

        // Dettagli Ordine 5: Luca Bianchi
        DettaglioOrdine dettaglio7 = DettaglioOrdine.builder()
                .ordine(ordini.get(4))
                .prodotto(prodotti.get(0)) // Laptop
                .quantita(1)
                .prezzoTotale(1000.00) // Prezzo potenzialmente scontato
                .build();
        dettagli.add(dettaglio7);
        ordini.get(4).getDettagli().add(dettaglio7);

        DettaglioOrdine dettaglio8 = DettaglioOrdine.builder()
                .ordine(ordini.get(4))
                .prodotto(prodotti.get(4)) // Cuffie Bluetooth
                .quantita(1)
                .prezzoTotale(50.00)
                .build();
        dettagli.add(dettaglio8);
        ordini.get(4).getDettagli().add(dettaglio8);

        return dettagli;
    }

    /**
     * Aggiorna i totali degli ordini in base ai dettagli associati.
     *
     * @param ordini lista degli ordini da aggiornare
     */
    private void updateOrdiniTotali(List<Ordine> ordini) {
        for (Ordine ordine : ordini) {
            double totale = ordine.getDettagli().stream()
                    .mapToDouble(DettaglioOrdine::getPrezzoTotale)
                    .sum();
            ordine.setTotale(totale);
        }
    }
}