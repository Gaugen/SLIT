package no.uia.slit.beans;

import no.uia.slit.ejb.StudentEJB;
import no.uia.slit.ejb.ModulEJB;
import no.uia.slit.entity.Student;
//import is202.hrms.web.View;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tor
 */
@Named("moduldeltakebean")
@ConversationScoped
public class ModulDeltakerBean implements Serializable {

    private static final long serialVersionUID = 42L;
    @Inject
    private Conversation conv;
    @EJB
    private ModulEJB modulEjb;
    @EJB
    StudentEJB studentEjb;
    @Inject
    ModulBean modulBean;

    
    private boolean updating;
    private Student valgtStudent;

    public ModulDeltakerBean() {
        updating = false;
    }

    public boolean isUpdating() {
        return updating;
    }

    public Student getValgStudent() {
        return valgtStudent;
    }

    public void setValgtStudent(Student student) {
        this.valgtStudent = student;
    }

    public long getValgtStudentId() {
        if (null == valgtStudent) {
            return 0L;
        } else {
            return valgtStudent.getId();
        }
    }

    public void setValgtStudentId(long id) {
        valgtStudent = studentEjb.find(id);
        if (valgtStudent == null) {
            //???
        }
    }


    public View lagre Deltaker() {
        System.err.println("->lagreDeltaker()");
        modulEjb.add(projectBean.getPid(), selectedEmployee, role);
        return View.project;
    }
}
