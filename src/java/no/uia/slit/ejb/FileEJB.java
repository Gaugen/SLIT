package no.uia.slit.ejb;

import no.uia.slit.entity.SlitFile;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tor Arne
 */
@Stateless
public class FileEJB extends AbstractFacade<SlitFile> {

    @PersistenceContext
    private EntityManager em;

    public FileEJB() {
        super(SlitFile.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public byte[] getContents(int fileId) {
        SlitFile file = find(fileId);
        if (null == file) {
            return null;
        } else {
            return file.getFile();
        }
    }
}
