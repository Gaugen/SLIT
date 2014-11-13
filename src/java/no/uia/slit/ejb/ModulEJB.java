package no.uia.slit.ejb;

import no.uia.slit.entity.Student;
import no.uia.slit.entity.Foreleser;
import no.uia.slit.entity.Slitfil;
import no.uia.slit.entity.Modul;
import no.uia.slit.entity.ModulEvaluering;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tor
 */
@Stateless
public class ModulEJB extends AbstractFacade<Modul> {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private StudentEJB studentEjb;
    @EJB
    private ForeleserEJB foreleserEjb;
    @EJB
    SlitfilEJB filEjb;

    public ModulEJB() {
        super(Modul.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<ModulEvaluering> getDeltakere(long pid) {
        Modul m = find(pid);
        if (null == m) {
            return new ArrayList<ModulEvaluering>();
        } else {
            return m.getDeltakere();
        }
    }

    @Override
    public Modul insert(Modul modul) {
        return super.insert(modul);
    }

    @Override
    public Modul update(Modul editedModul) {
        Modul dbModul = find(editedModul.getModulNo());
        if (null == dbModul) {
            insert(editedModul);
        }
        editedModul = super.update(editedModul);
        return editedModul;
    }

    /**
     *
     * @param modulNo
     * @param student
     * @param foreleser
     */
    public void addDeltaker(long modulNo, Student student) {
        Modul modul = find(modulNo);
        if (null == modul) {
            System.err.println("krise - modul er null");
            return;
        }
        student = em.merge(student);

        ModulEvaluering evaluering = new ModulEvaluering(modul, student);
        em.persist(evaluering);
        modul.addDeltaker(evaluering);
        student.addEvaluering(evaluering);
    }

    public void removeDeltaker(long modulNo, long studentNo) {
        System.err.println("removing " + studentNo + " from " + modulNo);
        Modul modul = find(modulNo);
        Student student = studentEjb.find(studentNo);
        ModulEvaluering pa = null;

        System.err.println("removing " + student + " from " + modul);

        if (modul != null && student != null) {
            pa = modul.getEvaluering(modul);
            System.err.println("removing " + pa);

            student.removeEvaluering(pa);
            modul.removeDeltaker(pa);
            em.merge(student);
            em.merge(modul);
            em.remove(pa);
        }
    }

    public void setFil(Modul modul, Slitfil fil) {
        System.out.println("Setting " + modul + " plan to " + fil);
        // Legg kun til fil hvis modulen eksisterer og filen ikke er tom.
        if (modul.getModulNo() > 0 && fil.getMengde() > 0) {
            modul = update(modul);
            if (fil.getId() > 0) {
                fil = filEjb.update(fil);
            } else {
                fil = filEjb.insert(fil);
            }
            modul.setFil(fil);
        }
    }
}
