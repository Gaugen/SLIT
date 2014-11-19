package no.uia.slit.beans;

import no.uia.slit.ejb.ModulEJB;
import no.uia.slit.entity.Modul;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Tor
 */
@Named("modullistbean")
@RequestScoped
public class ModulListBean {

    @EJB
    ModulEJB modulEjb;

    public List<Modul> getModuler() {
        return modulEjb.findAll();
    }

}
