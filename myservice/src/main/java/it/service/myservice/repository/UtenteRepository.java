package it.service.myservice.repository;


import it.service.myservice.object.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmail(String email);
}