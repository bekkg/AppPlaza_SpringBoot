package peaksoft.appplaza_springboot.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza_springboot.model.enums.SubscriptionStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDate createDate ;
    private boolean subscribe;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;
}
