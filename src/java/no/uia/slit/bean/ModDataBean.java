package no.uia.slit.bean;

import no.uia.slit.ejb.ModuleEJB;
import no.uia.slit.entity.Module;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tor
 */
@Named("moddatabean")
public class ModDataBean implements Serializable {

    private static final long serialVersionUID = 42L;
    @Inject
    private Conversation conv;
    @EJB
    private ModuleEJB modEjb;

    private Module mod;
    private boolean updating;

    public ModDataBean() {
        updating = false;
        mod = null;
    }

    public boolean isUpdating() {
        return updating;
    }

    public long getModule() {
        if (null == mod) {
            return 0;
        } else {
            return mod.getModuleNo();
        }
    }

    public void setModule(long modId) {
        if (conv.isTransient()) {
            conv.begin();
        }
        mod = modEjb.find(modId);
        if (null == mod) {
            mod = new Module();
            updating = false;
        } else {
            updating = true;
        }
    }

}
