package no.uia.slit.ejb;

import no.uia.slit.entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TorArne
 */
@Stateless
public class TeacherEJB extends AbstractFacade<Teacher> {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private LectureClassEJB classEJb;

    public TeacherEJB() {
        super(Teacher.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Teacher> findUnassigned() {
        List<Teacher> all = findAll();
        List<Teacher> unassigned = new ArrayList<Teacher>();

        for (Teacher tea : all) {
            if (tea.getAssessment().isEmpty()) {
                unassigned.add(tea);
                System.err.println("Added " + tea);

            }
        }
        return unassigned;
    }

    /**
     * Update is a bit more complex, because the old and new departments must
     * both be updated. In addition we need to get managed objects to ensure
     * that everything propagates to the database
     * @param editedTea
     * @return 
     */
    @Override
    public Teacher update(Teacher editedTea) {
        Teacher dbTea = find(editedTea.getId());
        if (null == dbTea) {
            insert(editedTea);
        }
        editedTea = super.update(editedTea);
        return editedTea;
    }
}
