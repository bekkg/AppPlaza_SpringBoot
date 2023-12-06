package peaksoft.appplaza_springboot.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class GenreResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate creatDate;
}
