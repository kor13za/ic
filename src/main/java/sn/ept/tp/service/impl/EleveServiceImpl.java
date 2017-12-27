package sn.ept.tp.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sn.ept.tp.entities.Eleve;
import sn.ept.tp.repositories.IEleveRepository;
import sn.ept.tp.service.IEleveService;

/**
 *
 * @author ISENE
 */
@Service
public class EleveServiceImpl implements IEleveService {

    @Autowired
    IEleveRepository repository;
    private static final Logger LOGGER = Logger.getLogger(EleveServiceImpl.class.getName());

    /**
     * Cette methode permet de liste tous les élèves avec un système de pagination
     * @param page
     * @param size
     * @return la liste des élèves trouvés
     */
    @Override
    public Page<Eleve> findAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }

    /**
     * Cette methode permet de trouver en élève connaisant son code
     * @param code le code de l"élève à rechercher
     * @return L'élèvre trouvé
     */
    @Override
    public Eleve findByCode(String code) {
        return repository.findByCode(code);
    }
/**
 * Cette methode permet d'ajouter un élève
 * @param e l'élève à ajouter
 * @return  l'élève ajouté
 */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Eleve add(Eleve e) {
        try {
            return repository.saveAndFlush(e);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "***[EleveServiceImpl][add] : Exception {0}", ex);
            return e;
        }
    }
/**
 * Cette methode pemet de mettre à jour les d'un élève
 * @param e l'élèvre à mettre à jour
 * @return  l'élève mis à jour
 */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Eleve update(Eleve e) {
        try {
            Eleve eleve = repository.findByCode(e.getCode());
            eleve.setNom(e.getNom());
            eleve.setPrenom(e.getPrenom());
            return repository.saveAndFlush(eleve);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "***[EleveServiceImpl][update] : Exception {0}", ex);
            return e;
        }
    }

    /**
     * Cette methode peret de tester l'existence d'un code
     * @param code le code à teste
     * @return  true si le code existe, false sinon
     */
    @Override
    public boolean exists(String code) {
        try {
            return repository.exists(code) > 0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "***[EleveServiceImpl][exists] : Exception {0}", e);
            return false;
        }
    }
/**
 * Cette methode permet de flter la lister des élèves suivant le nom et/ou le prenom
 * @param nom nom ou pertie du nom
 * @param prenom prénom ou pertie du prénom
 * @param page le numero de la page
 * @param size le nombre maximum de resultats
 * @return   la liste filtrée
 */
    @Override
    public Page<Eleve> filter(String nom, String prenom, int page, int size) {
        return repository.filter("%"+nom+"%", "%"+prenom+"%", new PageRequest(page, size));
    }

}
