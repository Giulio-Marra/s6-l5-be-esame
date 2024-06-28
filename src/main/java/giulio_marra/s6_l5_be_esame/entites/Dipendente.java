package giulio_marra.s6_l5_be_esame.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dipendente {
    @Id
    @GeneratedValue
    private long id;

    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String immagine_profilo;

    public Dipendente(String nome, String cognome, String username, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.immagine_profilo = immagine_profilo;
    }
}
