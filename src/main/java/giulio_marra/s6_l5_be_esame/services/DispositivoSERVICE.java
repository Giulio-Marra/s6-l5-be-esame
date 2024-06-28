package giulio_marra.s6_l5_be_esame.services;

import giulio_marra.s6_l5_be_esame.entites.Dipendente;
import giulio_marra.s6_l5_be_esame.entites.Dispositivo;
import giulio_marra.s6_l5_be_esame.enums.Stato_dispositivo;
import giulio_marra.s6_l5_be_esame.enums.Tipo_dispositivo;
import giulio_marra.s6_l5_be_esame.exceptions.BadRequestException;
import giulio_marra.s6_l5_be_esame.payloads.DispositivoDTO;
import giulio_marra.s6_l5_be_esame.payloads.DispositivoPayload;
import giulio_marra.s6_l5_be_esame.repositories.DispositivoREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DispositivoSERVICE {
    @Autowired
    private DispositivoREPO dispositivoREPO;

    @Autowired
    private DipendenteSERVICES dipendenteSERVICES;

    public Dispositivo saveDispositivo(DispositivoDTO body) {
        if (dispositivoREPO.existsByNome(body.identificativo_dispositivo())) {
            throw new RuntimeException("Dispositivo con questo identificativo gia esistente");
        }
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setNome(body.identificativo_dispositivo());
        dispositivo.setTipo_dispositivo(Tipo_dispositivo.valueOf(body.tipo_dispositivo()));
        dispositivo.setStato_dispositivo(Stato_dispositivo.valueOf(body.stato_dispositivo()));

        return dispositivoREPO.save(dispositivo);
    }

    public Dispositivo getDispositivo(long id) {
        Optional<Dispositivo> optionalDispositivo = dispositivoREPO.findById(id);
        if (optionalDispositivo.isPresent()) {
            return optionalDispositivo.get();
        } else {
            throw new BadRequestException("Dispositivo non trovato per l'ID specificato");
        }
    }

    public Page<Dispositivo> getDispositivi(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return dispositivoREPO.findAll(pageable);
    }

    public void getAndDeleteDispositivo(long id) {
        Dispositivo dispositivo = getDispositivo(id);

        dispositivoREPO.delete(dispositivo);
    }

    public Dispositivo update(long id, DispositivoPayload body) {
        Dispositivo dispositivo = getDispositivo(id);

        dispositivo.setStato_dispositivo(body.getStatoDispositivo());
        dispositivo.setTipo_dispositivo(body.getTipoDispositivo());
        dispositivo.setNome(body.getIdentificativo());

        if (dispositivo.getDipendente() == null || dispositivo.getDipendente().getId() != body.getId_dipendente()) {
            if (body.getId_dipendente() != 0) {
                Dipendente dipendente = dipendenteSERVICES.getDipendente(body.getId_dipendente());
                dispositivo.setDipendente(dipendente);
            } else {
                dispositivo.setDipendente(null);
            }
        }
        return dispositivoREPO.save(dispositivo);
    }
}
