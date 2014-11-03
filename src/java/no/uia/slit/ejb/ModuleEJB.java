package no.uia.slit.ejb;

import no.uia.slit.entity.Student;
import no.uia.slit.entity.SlitFile;
import no.uia.slit.entity.Module;
import no.uia.slit.entity.Assessment;
import no.uia.slit.auth.AuthGroup;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tor Arne
 */
@Stateless
public class ModuleEJB extends AbstractFacade<Module> {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private StudentEJB stuEjb;
    @EJB
    FileEJB fileEjb;

    public ModuleEJB() {
        super(Module.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Assessment> getParticipant(long pid) {
        Module p = find(pid);
        if (null == p) {
            return new ArrayList<Assessment>();
        } else {
            return p.getParticipant();
        }
    }

    @Override
    public Module insert(Module mod) {
        return super.insert(mod);
    }

    @Override
    public Module update(Module editedMod) {
        Module dbMod = find(editedMod.getModuleNo());
        if (null == dbMod) {
            insert(editedMod);
        }
        editedMod = super.update(editedMod);
        return editedMod;
    }

    public void addParticipant(long moduleNo, Student stu, AuthGroup group) {
        Module mod = find(moduleNo);
        if (null == mod) {
            System.err.println("krise - mod er null");
            return;
        }
        stu = em.merge(stu);

        Assessment assessment = new Assessment(mod, stu, group);
        em.persist(assessment);
        mod.addParticipant(assessment);
        stu.addAssessment(assessment);
    }

    public void removeParticipant(long moduleNo, long stuNo) {
        System.err.println("removing " + stuNo + " from " + modNo);
        Module mod = find(moduleNo);
        Student stu = stuEjb.find(stuNo);
        Assessment pa = null;

        System.err.println("removing " + stu + " from " + mod);

        if (mod != null && stu != null) {
            pa = stu.getAssessment(mod);
            System.err.println("removing " + pa);

            stu.removeAssessment(pa);
            mod.removeParticipant(pa);
            em.merge(stu);
            em.merge(mod);
            em.remove(pa);
        }
    }

    public void setUpploadFile(Module mod, SlitFile file) {
        System.out.println("Uploading " + file + " to " + mod);
        // only add file if module exists, and file is not empty
        if (mod.getModuleNo() > 0 && file.getSize() > 0) {
            mod = update(mod);
            if (file.getId() > 0) {
                file = fileEjb.update(file);
            } else {
                file = fileEjb.insert(file);
            }
            module.setFile(file);
        }
    }
}
