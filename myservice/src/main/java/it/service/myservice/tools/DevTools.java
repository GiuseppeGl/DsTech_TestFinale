package it.service.myservice.tools;

import it.service.myservice.object.dto.BookDTO;
import it.service.myservice.object.model.Book;
import java.util.List;
import java.util.stream.Collectors;

public class DevTools {

    public static BookDTO convertToDTO(Book book) {
        if (book == null) return null;
        return new BookDTO(
                book.getId(),
                book.getTitolo(),
                book.getAutore(),
                book.getAnnoPubblicazione(),
                book.getDisponibile()
        );
    }

    public static Book convertToEntity(BookDTO bookDTO) {
        if (bookDTO == null) return null;
        return Book.builder()
                .id(bookDTO.getId())
                .titolo(bookDTO.getTitolo())
                .autore(bookDTO.getAutore())
                .annoPubblicazione(bookDTO.getAnnoPubblicazione())
                .disponibile(bookDTO.getDisponibile())
                .build();
    }

    public static List<BookDTO> convertToBookDTOList(List<Book> books) {
        return books.stream()
                .map(DevTools::convertToDTO)
                .collect(Collectors.toList());
    }
}