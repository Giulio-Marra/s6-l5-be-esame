package giulio_marra.s6_l5_be_esame.controllers;

import giulio_marra.s6_l5_be_esame.entites.Dispositivo;
import giulio_marra.s6_l5_be_esame.exceptions.BadRequestException;
import giulio_marra.s6_l5_be_esame.payloads.DispositivoDTO;
import giulio_marra.s6_l5_be_esame.payloads.DispositivoPayload;
import giulio_marra.s6_l5_be_esame.payloads.DispositivoRESPONSE;
import giulio_marra.s6_l5_be_esame.services.DispositivoSERVICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoCONTROLLER {
    @Autowired
    DispositivoSERVICE dispositivoSERVICE;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DispositivoRESPONSE saveDipendente(@RequestBody @Validated DispositivoDTO body, BindingResult valResult) {
        if (valResult.hasErrors()) {
            System.out.println(valResult.getAllErrors());
            throw new BadRequestException(valResult.getAllErrors().toString());
        }
        return new DispositivoRESPONSE(dispositivoSERVICE.saveDispositivo(body).getId());
    }

    @GetMapping("/{dispositivoId}")
    public Dispositivo getDispositivoById(@PathVariable long dispositivoId) {
        return dispositivoSERVICE.getDispositivo(dispositivoId);
    }

    @GetMapping
    public Page<Dispositivo> getAllDispositivi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return dispositivoSERVICE.getDispositivi(page, size, sortBy);
    }

    @DeleteMapping("/{dispositivoId}")
    public void getAndDeleteDipendente(@PathVariable long dispositivoId) {
        dispositivoSERVICE.getAndDeleteDispositivo(dispositivoId);
    }

    @PutMapping("/{dispositivoId}")
    public Dispositivo getAndUpdateDipendente(@PathVariable long dispositivoId, @RequestBody DispositivoPayload body) {
        return dispositivoSERVICE.update(dispositivoId, body);
    }
}
