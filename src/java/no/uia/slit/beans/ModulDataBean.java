package no.uia.slit.beans;

import no.uia.slit.ejb.ModulEJB;
import no.uia.slit.entity.Modul;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tor
 */
@Named("moduldatabean")
public class ModulDataBean implements Serializable {

    private static final long serialVersionUID = 42L;
    @Inject
    private Conversation conv;
    @EJB
    private ModulEJB modulEjb;

    private Modul modul;
    private boolean updating;

    public ModulDataBean() {
        updating = false;
        modul = null;
    }

    public boolean isUpdating() {
        return updating;
    }

    public long getModul() {
        if (null == modul) {
            return 0;
        } else {
            return modul.getModulNo();
        }
    }

    public void setModul(long modulId) {
        if (conv.isTransient()) {
            conv.begin();
        }
        modul = modulEjb.find(modulId);
        if (null == modul) {
            modul = new Modul();
            updating = false;
        } else {
            updating = true;
        }
    }

}
