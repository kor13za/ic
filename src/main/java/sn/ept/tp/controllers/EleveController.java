package sn.ept.tp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sn.ept.tp.entities.Eleve;
import sn.ept.tp.service.IEleveService;

/**
 *
 * @author ISENE
 */
@RestController
public class EleveController {

    @Autowired
    IEleveService service;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Page<Eleve> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size) {
        return service.findAll(Math.abs(page), Math.abs(size));
    }

    @RequestMapping(value = "filter", method = RequestMethod.GET)
    public Page<Eleve> filter(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "nom", required = false, defaultValue = "") String nom,
            @RequestParam(name = "prenom", required = false, defaultValue = "") String prenom) {
        return service.filter(nom, prenom, Math.abs(page), Math.abs(size));
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public Eleve add(Eleve e) {
        if (!service.exists(e.getCode())) {
            e = service.add(e);
        }
        return e;
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
    public Eleve update(Eleve e) {
        if (service.exists(e.getCode())) {
            e = service.update(e);
        }
        return e;
    }
}
