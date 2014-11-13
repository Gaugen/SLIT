package no.uia.slit.ejb;

import no.uia.slit.entity.Slitfil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tor
 */
@Stateless
public class SlitfilEJB extends AbstractFacade<Slitfil> {

    @PersistenceContext
    private EntityManager em;

    public SlitfilEJB() {
        super(Slitfil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public byte[] getInnhold(int filId) {
        Slitfil fil = find(filId);
        if (null == fil) {
            return null;
        } else {
            return fil.getFil();
        }
    }
}
