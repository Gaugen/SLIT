package no.uia.slit.ejb;

import no.uia.slit.entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tor
 */
@Stateless
public class StudentEJB extends AbstractFacade<Student> {

    @PersistenceContext
    private EntityManager em;

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

        for (Student student : all) {
            if (student.getEvaluering().isEmpty()) {
                unassigned.add(student);
                System.err.println("Added " + student);

            }
        }
        return unassigned;
    }

    /**
     * Update is a bit more complex, because the old and new departments must
     * both be updated. In addition we need to get managed objects to ensure
     * that everything propagates to the database
     * @param editedStudent
     * @return 
     */
    @Override
    public Student update(Student editedStudent) {
        Student dbStudent = find(editedStudent.getId());
        if (null == dbStudent) {
            insert(editedStudent);
        }
        editedStudent = super.update(editedStudent);
        return editedStudent;
    }
}
