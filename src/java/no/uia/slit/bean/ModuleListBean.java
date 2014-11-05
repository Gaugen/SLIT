package no.uia.slit.bean;

import no.uia.slit.ejb.ModuleEJB;
import no.uia.slit.entity.Module;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("modulelistbean")
@RequestScoped
public class ModuleListBean {

    @EJB
    ModuleEJB modEjb;

    public List<Module> getModules() {
        return modEjb.findAll();
    }

}
