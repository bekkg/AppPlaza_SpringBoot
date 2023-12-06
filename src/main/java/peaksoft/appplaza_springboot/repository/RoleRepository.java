package peaksoft.appplaza_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.appplaza_springboot.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role r where r.roleName=:Name")
    Role findByName(@Param("Name") String roleName);
}
