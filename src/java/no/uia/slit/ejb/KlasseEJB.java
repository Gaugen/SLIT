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
public class KlasseEJB extends AbstractFacade<Klasse> {

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

    public KlasseEJB() {
        super(Klasse.class);
    }

    /**
     * Method returns the first lecture class it finds. It is used by the student
     * page, to make sure that every student belongs to a lecture class
     * @return 
     */
    public Klasse getDefaultKlasse() {
        List<Klasse> klasseListe = findAll();
        if (klasseListe != null && klasseListe.size() > 0) {
            return klasseListe.get(0);
        } else {
            throw new IllegalStateException("No classes available.");
        }
    }

    /**
     * A wrapper for the find() method in AbstractFacade. It allows us to pass
     * the primary key as the primitive type long, rather than as the wrapper
     * type Long. It also forces loading of the lecture class's students
     * @param klasseNo
     * @return 
     */
    public Klasse find (long klasseNo) {
        Klasse kla = super.find(klasseNo);
        if (null != kla) {
            kla.getStudents();
                }
        return kla;
    }
    @Override
    public void delete(Klasse klasse) {
        Klasse dbKlasse = find (klasse.getKlasseNo());
        List<Student> studs = dbKlasse.getStudents();
        if (studs != null && studs.size() > 0) {
            throw new EJBException("Cannot delete Klasse with Students in it");
        }
        super.delete(dbKlasse);
    }

    }
