package peaksoft.appplaza_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza_springboot.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    @Query("select g from Genre g where g.name=:name")
   Genre name (@Param("name") String name);
}
