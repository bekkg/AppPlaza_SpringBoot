package peaksoft.appplaza_springboot.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza_springboot.model.Genre;
import peaksoft.appplaza_springboot.model.dto.GenreRequest;

import peaksoft.appplaza_springboot.model.dto.GenreResponse;

import java.time.LocalDate;

@Component
public class GenreMapper {
    public Genre mapToEntity(GenreRequest request){
        Genre genre = new Genre();
        genre.setName(request.getName());
        genre.setDescription(request.getDescription());
        genre.setCreatDate(LocalDate.now());
        return genre;
    }
    public GenreResponse mapToResponse(Genre genre){
        return GenreResponse
                .builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .creatDate(genre.getCreatDate())
                .build();
    }
    public GenreResponse mapToGenreResponse(Genre genre){
          return GenreResponse
                .builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .creatDate(genre.getCreatDate())
                .build();
    }
}
