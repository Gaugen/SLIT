package no.uia.slit.beans;

import no.uia.slit.ejb.StudentEJB;
import no.uia.slit.entity.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author Tor
 */
@Named("studentlistbean")
public class StudentListBean {

    @EJB
    private StudentEJB studentEjb;

    public List<Student> getStudenter() {
        return studentEjb.findAll();
    }

    public List<Student> getArbeidsledigStudenter() {
        return studentEjb.findUnassigned();
    }

}
