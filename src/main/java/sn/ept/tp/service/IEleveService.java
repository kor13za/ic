package sn.ept.tp.service;

import org.springframework.data.domain.Page;
import sn.ept.tp.entities.Eleve;

/**
 *
 * @author ISENE
 */
public interface IEleveService {

    public Page<Eleve> findAll(int page, int size);

    public Eleve findByCode(String code);

    public Eleve add(Eleve e);

    public Eleve update(Eleve e);

    public boolean exists(String code);

    public Page<Eleve> filter(String nom, String prenom, int page, int size);

}
