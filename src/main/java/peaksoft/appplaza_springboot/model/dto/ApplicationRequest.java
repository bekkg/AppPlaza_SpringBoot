package peaksoft.appplaza_springboot.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ApplicationRequest {


    private String name;
    private String description;
    private String developer;
    private String version;
    private Long genreId;
}
