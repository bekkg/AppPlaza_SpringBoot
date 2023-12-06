package peaksoft.appplaza_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.appplaza_springboot.mapper.ApplicationMapper;
import peaksoft.appplaza_springboot.model.Application;
import peaksoft.appplaza_springboot.model.Genre;
import peaksoft.appplaza_springboot.model.dto.*;
import peaksoft.appplaza_springboot.repository.ApplicationRepository;
import peaksoft.appplaza_springboot.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository repository;
    private final GenreRepository genreRepository;

    public ApplicationResponse save(ApplicationRequest request) {
        Application application = applicationMapper.mapToEntity(request);
        Genre genre = genreRepository.findById(request.getGenreId()).get();
        application.setGenreName(genre.getName());
        repository.save(application);
        return applicationMapper.mapToResponse(application);
    }

    public ApplicationResponse findById(Long id) {
        Application application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Application not found" + id));
        return applicationMapper.mapToApplicationResponse(application);
    }

    public List<ApplicationResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(applicationMapper::mapToApplicationResponse).toList();
    }

    public void delete(Long appId) {
        repository.deleteById(appId);
    }

    public ApplicationResponse update(Long appId, ApplicationRequest request) {
        Application oldApp = repository.findById(appId)
                .orElseThrow(() -> new RuntimeException(" Application not found by id: " + appId));
        oldApp.setName(request.getName());
        oldApp.setDescription(request.getDescription());
        oldApp.setDeveloper(request.getDeveloper());
        oldApp.setVersion(request.getVersion());
        return applicationMapper.mapToApplicationResponse(oldApp);
    }



}
