package giulio_marra.s6_l5_be_esame.repositories;

import giulio_marra.s6_l5_be_esame.entites.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteREPO extends JpaRepository<Dipendente, Long> {

    boolean existsByEmail(String email);

}
