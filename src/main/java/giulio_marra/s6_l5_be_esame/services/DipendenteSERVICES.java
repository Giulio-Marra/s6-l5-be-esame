package giulio_marra.s6_l5_be_esame.services;

import giulio_marra.s6_l5_be_esame.entites.Dipendente;
import giulio_marra.s6_l5_be_esame.payloads.DipendenteDTO;
import giulio_marra.s6_l5_be_esame.repositories.DipendenteREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteSERVICES {
    @Autowired
    private DipendenteREPO dipendenteREPO;

    public Dipendente saveDipendente(DipendenteDTO body) {
        if (dipendenteREPO.existsByEmail(body.email())) {
            throw new RuntimeException("Dipendente con questa mail esiste gia");
        }
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(body.nome());
        dipendente.setCognome(body.cognome());
        dipendente.setEmail(body.email());
        dipendente.setUsername(body.username());

        return dipendenteREPO.save(dipendente);
    }
}
