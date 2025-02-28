package it.service.myservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Gestore delle eccezioni specifiche per le operazioni sugli ordini.
 * Estende il gestore globale delle eccezioni con gestioni personalizzate.
 */
@ControllerAdvice
public class OrdineExceptionHandler {

    /**
     * Gestisce errori di formato nelle date per le richieste di intervallo
     *
     * @param ex eccezione di mismatch del tipo di un argomento
     * @return ResponseEntity con errore formattato
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Formato parametro non valido");

        String paramName = ex.getName();
        String message = "Il parametro '" + paramName + "' deve essere nel formato corretto";

        if (paramName.equals("startDate") || paramName.equals("endDate")) {
            message = "La data deve essere nel formato YYYY-MM-DD";
        }

        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gestisce errori di parametri mancanti nelle richieste
     *
     * @param ex eccezione di parametro mancante
     * @return ResponseEntity con errore formattato
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Parametro mancante");

        String paramName = ex.getParameterName();
        String message = "Il parametro '" + paramName + "' è obbligatorio";

        if (paramName.equals("startDate")) {
            message = "La data di inizio è obbligatoria";
        } else if (paramName.equals("endDate")) {
            message = "La data di fine è obbligatoria";
        }

        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}