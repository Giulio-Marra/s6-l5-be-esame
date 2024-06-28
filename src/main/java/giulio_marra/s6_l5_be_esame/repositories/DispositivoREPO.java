package giulio_marra.s6_l5_be_esame.repositories;

import giulio_marra.s6_l5_be_esame.entites.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoREPO extends JpaRepository<Dispositivo, Long> {
    boolean existsByNome(String nome);
}
