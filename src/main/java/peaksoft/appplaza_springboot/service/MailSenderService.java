package peaksoft.appplaza_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.appplaza_springboot.mapper.MailSenderMapper;
import peaksoft.appplaza_springboot.model.MailSender;
import peaksoft.appplaza_springboot.model.dto.MailSenderRequest;
import peaksoft.appplaza_springboot.model.dto.MailSenderResponse;
import peaksoft.appplaza_springboot.repository.MailSenderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final MailSenderMapper mailSenderMapper;
    private final MailSenderRepository repository;

    public MailSenderResponse save(MailSenderRequest request) {
        MailSender mailSender = mailSenderMapper.mapToEntity(request);
        repository.save(mailSender);
        return mailSenderMapper.mapToResponse(mailSender);
    }

    public MailSenderResponse findById(Long id) {
        MailSender mailSender = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(" MailSender not found: " + id));
        return mailSenderMapper.mapToMailSenderResponse(mailSender);
    }

    public List<MailSenderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mailSenderMapper::mapToMailSenderResponse).toList();
    }

    public void delete(Long mailId) {
        repository.deleteById(mailId);
    }

    public MailSenderResponse update(Long mailId, MailSenderRequest request) {
        MailSender oldMail = repository.findById(mailId)
                .orElseThrow(() -> new RuntimeException(" MailSender not found by id: " + mailId));
        oldMail.setText(request.getText());
        oldMail.setSender(request.getSender());
        return mailSenderMapper.mapToMailSenderResponse(oldMail);
    }
}
