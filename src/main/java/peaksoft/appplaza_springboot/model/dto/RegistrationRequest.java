package peaksoft.appplaza_springboot.model.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
    private int age;
    private boolean subscribe;


}
