package no.uia.slit.ejb;

import no.uia.slit.entity.Student;
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
public class StudentEJB extends AbstractFacade<Student> {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private LectureClassEJB classEJb;

    public StudentEJB() {
        super(Student.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Student> findUnassigned() {
        List<Student> all = findAll();
        List<Student> unassigned = new ArrayList<Student>();

        for (Student stu : all) {
            if (stu.getAssessment().isEmpty()) {
                unassigned.add(stu);
                System.err.println("Added " + stu);

            }
        }
        return unassigned;
    }

    /**
     * Update is a bit more complex, because the old and new departments must
     * both be updated. In addition we need to get managed objects to ensure
     * that everything propagates to the database
     * @param editedStu
     * @return 
     */
    @Override
    public Student update(Student editedStu) {
        Student dbStu = find(editedStu.getId());
        if (null == dbStu) {
            insert(editedStu);
        }
        editedStu = super.update(editedStu);
        return editedStu;
    }
}
