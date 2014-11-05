package no.uia.slit.bean;

import no.uia.slit.ejb.StudentEJB;
import no.uia.slit.entity.Student;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("stulistbean")
public class StudentListBean {

    @EJB
    private StudentEJB stuEjb;

    public List<Student> getStudents() {
        return stuEjb.findAll();
    }

    public List<Student> getUnassignedStudents() {
        return stuEjb.findUnassigned();
    }

}
