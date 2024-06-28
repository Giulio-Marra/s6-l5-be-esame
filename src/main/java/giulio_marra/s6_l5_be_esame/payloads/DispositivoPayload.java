package giulio_marra.s6_l5_be_esame.payloads;

import giulio_marra.s6_l5_be_esame.enums.Stato_dispositivo;
import giulio_marra.s6_l5_be_esame.enums.Tipo_dispositivo;
import lombok.Getter;

@Getter
public class DispositivoPayload {
    private long id;
    private String identificativo;
    private Stato_dispositivo statoDispositivo;
    private Tipo_dispositivo tipoDispositivo;
    private long id_dipendente;
}
