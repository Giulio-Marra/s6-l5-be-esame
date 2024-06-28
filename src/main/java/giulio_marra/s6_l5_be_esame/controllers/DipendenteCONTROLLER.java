package giulio_marra.s6_l5_be_esame.controllers;

import giulio_marra.s6_l5_be_esame.entites.Dipendente;
import giulio_marra.s6_l5_be_esame.exceptions.BadRequestException;
import giulio_marra.s6_l5_be_esame.payloads.DipendenteDTO;
import giulio_marra.s6_l5_be_esame.payloads.DipendenteResponseDTO;
import giulio_marra.s6_l5_be_esame.services.DipendenteSERVICES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{dipendenteId}")
    public Dipendente getDipendenteById(@PathVariable long dipendenteId) {
        return dipendenteSERVICES.getDipendente(dipendenteId);
    }

    @GetMapping
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteSERVICES.getDipendenti(page, size, sortBy);
    }

    @DeleteMapping("/{dipendenteId}")
    public void getAndDeleteDipendente(@PathVariable long dipendenteId) {
        dipendenteSERVICES.getAndDeleteDipendente(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente getAndUpdateDipendente(@PathVariable long dipendenteId, @RequestBody DipendenteDTO body) {
        return dipendenteSERVICES.getAndUpdateDipendente(dipendenteId, body);
    }
}
