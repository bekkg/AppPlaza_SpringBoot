package peaksoft.appplaza_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza_springboot.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
}
