package giulio_marra.s6_l5_be_esame.entites;

import giulio_marra.s6_l5_be_esame.enums.Stato_dispositivo;
import giulio_marra.s6_l5_be_esame.enums.Tipo_dispositivo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dispositivo {
    @Id
    @GeneratedValue
    private long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @Enumerated(EnumType.STRING)
    private Tipo_dispositivo tipo_dispositivo;

    @Enumerated(EnumType.STRING)
    private Stato_dispositivo stato_dispositivo;

    public Dispositivo(String nome, Tipo_dispositivo tipo_dispositivo, Stato_dispositivo stato_dispositivo) {
        this.nome = nome;
        this.tipo_dispositivo = tipo_dispositivo;
        this.stato_dispositivo = stato_dispositivo;
    }
}
