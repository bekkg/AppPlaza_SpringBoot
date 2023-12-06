package peaksoft.appplaza_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza_springboot.model.dto.*;
import peaksoft.appplaza_springboot.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/sign-up")
    public ResponseEntity<GenreResponse> add(@RequestBody GenreRequest request) {
        GenreResponse response = genreService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public GenreResponse findById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @GetMapping()
    public List<GenreResponse> findAll() {
        return genreService.findAll();
    }

    @PutMapping("/update/{id}")
    public GenreResponse update(@PathVariable("id") Long id, @RequestBody GenreRequest request) {
        return genreService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        genreService.delete(id);
        return "Genre with id: " + id + " successfully deleted";
    }
}
