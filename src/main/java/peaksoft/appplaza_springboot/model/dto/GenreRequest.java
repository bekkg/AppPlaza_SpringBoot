package peaksoft.appplaza_springboot.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenreRequest {

    private String name;
    private String description;
}
