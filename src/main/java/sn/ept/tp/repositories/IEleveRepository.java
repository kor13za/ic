package sn.ept.tp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.ept.tp.entities.Eleve;

/**
 *
 * @author ISENE
 */
public interface IEleveRepository extends JpaRepository<Eleve, Long> {
    
    public Eleve findByCode(String code);

    @Query("SELECT COUNT(e) FROM Eleve e WHERE e.code=:code")
    public long exists(@Param("code") String code);

    @Query("SELECT e FROM Eleve e WHERE e.nom LIKE :nom AND e.prenom LIKE :prenom")
    public Page<Eleve> filter(@Param("nom") String nom, @Param("prenom") String prenom, Pageable p);
}
