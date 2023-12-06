package peaksoft.appplaza_springboot.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza_springboot.model.MailSender;

import peaksoft.appplaza_springboot.model.dto.MailSenderRequest;
import peaksoft.appplaza_springboot.model.dto.MailSenderResponse;
import java.time.LocalDate;

@Component
public class MailSenderMapper {
    public MailSender mapToEntity(MailSenderRequest request){
        MailSender mailSender = new MailSender();
        mailSender.setSender(request.getSender());
        mailSender.setText(request.getText());
        mailSender.setCreateDate(LocalDate.now());
        return mailSender;
    }

    public MailSenderResponse mapToResponse(MailSender mailSender){
        return MailSenderResponse
                .builder()
                .id(mailSender.getId())
                .sender(mailSender.getSender())
                .text(mailSender.getText())
                .createDate(mailSender.getCreateDate())
                .build();
    }
    public MailSenderResponse mapToMailSenderResponse(MailSender mailSender){
        return MailSenderResponse
                .builder()
                .id(mailSender.getId())
                .sender(mailSender.getSender())
                .text(mailSender.getText())
                .createDate(mailSender.getCreateDate())
                .build();
    }
}
