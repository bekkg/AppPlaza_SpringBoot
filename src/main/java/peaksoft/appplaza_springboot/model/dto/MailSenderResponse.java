package peaksoft.appplaza_springboot.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class MailSenderResponse {
    private Long id;
    private String sender;
    private String text;
    private LocalDate createDate;

}
