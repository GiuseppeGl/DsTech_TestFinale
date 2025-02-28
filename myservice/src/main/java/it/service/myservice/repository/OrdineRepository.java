package it.service.myservice.repository;

import it.service.myservice.object.entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository per l'accesso ai dati degli ordini nel database.
 * Estende JpaRepository per fornire metodi CRUD di base.
 */
@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    /**
     * Trova tutti gli ordini associati a un utente specifico.
     *
     * @param utenteId ID dell'utente
     * @return lista di ordini dell'utente
     */
    List<Ordine> findByUtenteId(Long utenteId);

    /**
     * Trova tutti gli ordini in un intervallo di date specificato.
     *
     * @param startDate data di inizio dell'intervallo (inclusa)
     * @param endDate data di fine dell'intervallo (inclusa)
     * @return lista di ordini nell'intervallo di date
     */
    List<Ordine> findByDataBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Calcola il totale speso da un utente sommando i totali di tutti i suoi ordini.
     *
     * @param utenteId ID dell'utente
     * @return totale speso dall'utente
     */
    @Query("SELECT SUM(o.totale) FROM Ordine o WHERE o.utente.id = :utenteId")
    Double calculateTotaleSpesoDaUtente(@Param("utenteId") Long utenteId);
}