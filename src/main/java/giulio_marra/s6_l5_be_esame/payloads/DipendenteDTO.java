package giulio_marra.s6_l5_be_esame.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record DipendenteDTO(
        @NotEmpty(message = "Inserisci un nome")
        String nome,
        @NotEmpty(message = "inserisci un cognome")
        String cognome,
        @NotEmpty(message = "inserisci uno username")
        String username,
        @Email(message = "inserisci una mail")
        String email
) {
}
