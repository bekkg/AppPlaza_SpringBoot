package peaksoft.appplaza_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.appplaza_springboot.model.MailSender;

public interface MailSenderRepository extends JpaRepository<MailSender,Long> {
}
