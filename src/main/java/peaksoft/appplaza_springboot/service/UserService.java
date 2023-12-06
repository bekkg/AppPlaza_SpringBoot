package peaksoft.appplaza_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.appplaza_springboot.mapper.LoginMapper;
import peaksoft.appplaza_springboot.mapper.UserMapper;
import peaksoft.appplaza_springboot.model.Application;
import peaksoft.appplaza_springboot.model.Role;
import peaksoft.appplaza_springboot.model.User;
import peaksoft.appplaza_springboot.model.dto.*;
import peaksoft.appplaza_springboot.repository.ApplicationRepository;
import peaksoft.appplaza_springboot.repository.RoleRepository;
import peaksoft.appplaza_springboot.repository.UserRepository;
import peaksoft.appplaza_springboot.security.jwt.JwtUtil;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ApplicationRepository appRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;
    private final LoginMapper loginMapper;

    public RegistrationResponse save(RegistrationRequest request) {
        User user = userMapper.mapToEntity(request);
        if (user.getName().length() < 2) {
            throw new RuntimeException(" User дин аты 2 сандан коп болсун ");
        }
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException(" email' де @ символ камтыщы керек ");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (repository.findAll().isEmpty()) {
            Role role = roleRepository.findByName("ADMIN");
            if (role == null) {
                role = new Role("ADMIN");
            }
            roles.add(role);
        } else {
            Role role = roleRepository.findByName("USER");
            if (role == null) {
                role = new Role("USER");
//                role.setRoleName("USER");
            }
            roles.add(role);
        }
        user.setRoles(roles);
        repository.save(user);
        return userMapper.mapToResponse(user);
    }

    public LoginResponse login (LoginRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("  not found "));
        String jwt = jwtUtil.generateToken(user);
        return loginMapper.mapToResponse(jwt, user.getRoles().toString());
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(" User not found " + id));
        return userMapper.mapToUserResponse(user);
    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::mapToUserResponse).toList();
    }

    public UserResponse update(Long userId, RegistrationRequest request) {
        User oldUser = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException(" User not found by id: " + userId));
        oldUser.setName(request.getName());
        oldUser.setEmail(request.getEmail());
        oldUser.setName(request.getName());
        oldUser.setName(request.getName());
        oldUser.setName(request.getName());
        oldUser.setName(request.getName());
        repository.save(oldUser);
        return userMapper.mapToUserResponse(oldUser);
    }

    public void delete(Long userId) {
        repository.deleteById(userId);
    }

    public UserResponse profile(Principal principal) {
        User user = repository.findByEmail(principal.getName()).get();
        return userMapper.mapToUserResponse(user);
    }


}
