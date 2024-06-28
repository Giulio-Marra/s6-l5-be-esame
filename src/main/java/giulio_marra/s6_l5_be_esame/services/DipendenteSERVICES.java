package giulio_marra.s6_l5_be_esame.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giulio_marra.s6_l5_be_esame.entites.Dipendente;
import giulio_marra.s6_l5_be_esame.exceptions.ErrorsPayload;
import giulio_marra.s6_l5_be_esame.payloads.DipendenteDTO;
import giulio_marra.s6_l5_be_esame.repositories.DipendenteREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DipendenteSERVICES {
    @Autowired
    private DipendenteREPO dipendenteREPO;

    @Autowired
    private Cloudinary cloudinary;

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

    public Dipendente getDipendente(long id) {
        Optional<Dipendente> optionalDipendente = dipendenteREPO.findById(id);
        if (optionalDipendente.isPresent()) {
            return optionalDipendente.get();
        } else {
            ErrorsPayload errorPayload = new ErrorsPayload("Dipendente non trovato per l'ID specificato", LocalDateTime.now());
            throw new RuntimeException(errorPayload.getMessage());
        }
    }

    public Page<Dipendente> getDipendenti(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return dipendenteREPO.findAll(pageable);
    }

    public void getAndDeleteDipendente(long id) {
        Dipendente dipendente = getDipendente(id);

        dipendenteREPO.delete(dipendente);
    }

    public Dipendente getAndUpdateDipendente(long id, DipendenteDTO updatedDipendenteDTO) {
        Dipendente dipendente = getDipendente(id);

        dipendente.setNome(updatedDipendenteDTO.nome());
        dipendente.setCognome(updatedDipendenteDTO.cognome());
        dipendente.setEmail(updatedDipendenteDTO.email());
        dipendente.setUsername(updatedDipendenteDTO.username());

        return dipendenteREPO.save(dipendente);
    }

    public String uploadImage(long id, MultipartFile file) throws IOException {
        Dipendente dipendente = getDipendente(id);
        String imageUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");

        dipendente.setImmagine_profilo(imageUrl);

        dipendenteREPO.save(dipendente);
        return imageUrl;
    }
}
