package giulio_marra.s6_l5_be_esame.controllers;

import giulio_marra.s6_l5_be_esame.exceptions.BadRequestException;
import giulio_marra.s6_l5_be_esame.payloads.DipendenteDTO;
import giulio_marra.s6_l5_be_esame.payloads.DipendenteResponseDTO;
import giulio_marra.s6_l5_be_esame.services.DipendenteSERVICES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteCONTROLLER {
    @Autowired
    private DipendenteSERVICES dipendenteSERVICES;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO saveDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult valResult) {
        if (valResult.hasErrors()) {
            System.out.println(valResult.getAllErrors());
            throw new BadRequestException(valResult.getAllErrors().toString());
        }
        return new DipendenteResponseDTO(dipendenteSERVICES.saveDipendente(body).getId());
    }
}
