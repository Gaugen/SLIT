package no.uia.slit.ejb;


import no.uia.slit.entity.LectureClass;
import no.uia.slit.entity.Student;
import no.uia.slit.entity.Teacher;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This ejb handles database operations for LectureClass objects. It does not have
 * any fields (except for the entity manager which is handled by the container),
 * which means it can't keep state, and is annotated with Stateless to mark it
 * as a stateless session bean. A stateless bean can be shared by several users,
 * so it is efficient with regards to resource usage.
 *
 * @author Tor Arne
 */
@Stateless
public class LectureClassEJB extends AbstractFacade<LectureClass> {

    /**
     * The PersistenceContext annotation is a request to the container
     * (GlassFish) to create or find an EntityManager and store it in the field
     * em. We get the EntityManager object we need, without having to write the
     * code to create it. This is called dependency injection.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * This method is used in the methods inherited from AbstractFacade to
     * access the EntityManager
     * @return 
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LectureClassEJB() {
        super(LectureClass.class);
    }

    /**
     * Method returns the first lecture class it finds. It is used by the student
     * page, to make sure that every student belongs to a lecture class
     * @return 
     */
    public LectureClass getDefaultLectureClass() {
        List<LectureClass> classList = findAll();
        if (classList != null && classList.size() > 0) {
            return classList.get(0);
        } else {
            throw new IllegalStateException("No classes available.");
        }
    }

    /**
     * A wrapper for the find() method in AbstractFacade. It allows us to pass
     * the primary key as the primitive type long, rather than as the wrapper
     * type Long. It also forces loading of the lecture class's students
     * @param classNo
     * @return 
     */
    public LectureClass find (long classNo) {
        LectureClass class = super.find(classNo);
        if (null != class) {
            class.getStudents();
                }
        return class;
    }
    @Override
    public void delete(LectureClass class) {
        LectureClass dbClass = find (class.getClassNo());
        List<Student> studs = dbClass.getStudents();
        if (studs != null && studs.size() > 0) {
            throw new EJBException("Cannot delete Lecture Class with Students in it");
        }
        super.delete(dbClass);
    }

    }
