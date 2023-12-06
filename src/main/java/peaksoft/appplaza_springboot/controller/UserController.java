package peaksoft.appplaza_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza_springboot.model.dto.*;
import peaksoft.appplaza_springboot.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponse> add(@RequestBody RegistrationRequest request) {
        System.out.println("sign-up");
        RegistrationResponse response = userService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public LoginResponse login (@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping()
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @PutMapping("/update/{id}")
    public UserResponse update(@PathVariable("id") Long id, @RequestBody RegistrationRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return " User with id: " + id + " successfully deleted.";
    }

    @GetMapping("/profile")
    public UserResponse profile(Principal principal) {
        System.out.println("USER CONTROLLER PROFILE");
        return userService.profile(principal);
    }
}
