package sn.ept.tp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sn.ept.tp.entities.Eleve;
import sn.ept.tp.repositories.IEleveRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TpApplicationTests {

    @Autowired
    IEleveRepository repository;
    Eleve e;

    @Before
    public void init() {
        e = new Eleve("prenom", "nom", "code");
    }

    @Test
    public void add() {
        e = repository.saveAndFlush(e);
        Assert.assertTrue("**** Ajout impossible", null != e.getId());
    }

    @Test
    public void update() {
        e = repository.findOne(1L);
        e.setNom("update");
        e = repository.saveAndFlush(e);
        Assert.assertTrue("**** Mise àjour impossible", e.getNom().equals("update"));
    }

    @Test
    public void all() {
        Assert.assertTrue("**** Impossible de trouver la liste", repository.findAll().size() > 0);
    }

}
