package peaksoft.appplaza_springboot.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza_springboot.model.User;
import peaksoft.appplaza_springboot.model.dto.RegistrationRequest;
import peaksoft.appplaza_springboot.model.dto.RegistrationResponse;
import peaksoft.appplaza_springboot.model.dto.UserResponse;

import java.time.LocalDate;

@Component
public class UserMapper {

    public User mapToEntity(RegistrationRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());
        user.setCreateDate(LocalDate.now());
        user.setSubscribe(request.isSubscribe());
        return user;
    }
    public RegistrationResponse mapToResponse(User user){
        return RegistrationResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .createDate(LocalDate.now())
                .subscribe(user.isSubscribe())
                .response("Success Registered")
                .build();
    }
    public UserResponse mapToUserResponse(User user){
        return UserResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .createDate(user.getCreateDate())
                .subscribe(user.isSubscribe())
                .build();
    }
}
