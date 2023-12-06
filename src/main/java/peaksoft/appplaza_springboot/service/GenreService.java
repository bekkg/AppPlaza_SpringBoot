package peaksoft.appplaza_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.appplaza_springboot.mapper.GenreMapper;
import peaksoft.appplaza_springboot.model.Genre;
import peaksoft.appplaza_springboot.model.dto.GenreRequest;
import peaksoft.appplaza_springboot.model.dto.GenreResponse;
import peaksoft.appplaza_springboot.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreMapper genreMapper;
    private final GenreRepository repository;

    public GenreResponse save(GenreRequest request) {
        Genre genre = genreMapper.mapToEntity(request);
        repository.save(genre);
        return genreMapper.mapToResponse(genre);
    }

    public GenreResponse findById(Long id) {
        Genre genre = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Genre not found: " + id));
        return genreMapper.mapToGenreResponse(genre);
    }

    public List<GenreResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(genreMapper::mapToGenreResponse).toList();
    }

    public void delete(Long genreId) {
        repository.deleteById(genreId);
    }

    public GenreResponse update(Long genreId, GenreRequest request) {
        Genre olGenre = repository.findById(genreId)
                .orElseThrow(() -> new RuntimeException(" Genre not found by id: " + genreId));
        olGenre.setName(request.getName());
        olGenre.setDescription(request.getDescription());
        repository.save(olGenre);
        return genreMapper.mapToGenreResponse(olGenre);
    }
}
