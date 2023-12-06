package peaksoft.appplaza_springboot.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza_springboot.model.enums.AppStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ApplicationResponse {

    private Long id ;
    private String name;
    private String description;
    private String developer;
    private String version;
    private LocalDate createDate;
    @Enumerated(EnumType.STRING)
    private AppStatus appStatus;
    private String genreName;
}
