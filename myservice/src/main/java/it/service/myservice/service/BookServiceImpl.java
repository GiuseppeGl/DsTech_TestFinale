package it.service.myservice.service;

import it.service.myservice.object.dto.BookDTO;
import it.service.myservice.object.model.Book;
import it.service.myservice.repository.BookRepository;
import it.service.myservice.tools.DevTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return DevTools.convertToBookDTOList(bookRepository.findAll());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(DevTools::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Libro non trovato"));
    }

    @Override
    @Transactional
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = DevTools.convertToEntity(bookDTO);
        book = bookRepository.save(book);
        return DevTools.convertToDTO(book);
    }

    @Override
    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro non trovato"));
        bookDTO.setId(id);
        return saveBook(bookDTO);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}