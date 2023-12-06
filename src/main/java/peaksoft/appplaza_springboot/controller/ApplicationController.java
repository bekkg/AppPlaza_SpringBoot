package peaksoft.appplaza_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza_springboot.model.dto.*;
import peaksoft.appplaza_springboot.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApplicationResponse> add(@RequestBody ApplicationRequest request) {
        System.out.println("application sign-in.");
        ApplicationResponse response = applicationService.save(request);
        System.out.println(" 2 application sign-in.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ApplicationResponse findById(@PathVariable Long id) {
        return applicationService.findById(id);
    }

    @GetMapping()
    public List<ApplicationResponse> findAll() {
        return applicationService.findAll();
    }

    @PutMapping("/update/{id}")
    public ApplicationResponse update(@PathVariable("id") Long id, @RequestBody ApplicationRequest request) {
        return applicationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        applicationService.delete(id);
        return " Application with id: " + id + " successfully deleted";
    }

 
}
