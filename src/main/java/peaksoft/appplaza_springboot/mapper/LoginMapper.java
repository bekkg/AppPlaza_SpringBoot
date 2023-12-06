package peaksoft.appplaza_springboot.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza_springboot.model.dto.LoginResponse;

@Component
public class LoginMapper {
    public LoginResponse mapToResponse(String token, String roleName){
        return LoginResponse.builder()
                .token(token)
                .roleName(roleName)
                .build();
    }

}
