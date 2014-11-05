package no.uia.slit.bean;

import no.uia.slit.ejb.LectureClassEJB;
import no.uia.slit.ejb.StudentEJB;
import no.uia.slit.entity.LectureClass;
import no.uia.slit.entity.Student;
import no.uia.slit.web.View;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("stubean")
@ConversationScoped
public class StudentBean implements Serializable {

    private static final long serialVersionUID = 42L;
    // The inject annotation asks the server to insert a suitable
    // object, i.e. a new Conversation object in the variable conv.
    @Inject
    private Conversation conv;
    @EJB
    private StudentEJB stuEjb;
    @EJB
    private LectureClassEJB classEjb;
    private Student stu;
    private boolean updating;

    public StudentBean() {
        stu = null;
        updating = false;
    }

    public long getStuId() {
        if (null == stu) {
            return 0;
        } else {
            return stu.getId();
        }
    }

    public void setStuId(long stuid) {
        if (conv.isTransient()) {
            conv.begin();
        }
        if (stuid > 0) {
            stu = stuEjb.find(stuid);
            updating = true;
        } else {
            //
            stu = new Student();
            stu.setLectureClass(classEjb.getDefaultLectureClass());
        }

    }

    public boolean isUpdating() {
        return updating;
    }

    public String getName() {
        if (stu == null) {
            return null;
        } else {
            return stu.getName();
        }
    }

    public void setName(String name) {
        stu.setName(name);
    }

    public LectureClass getLectureClass() {
        return stu.getLectureClass();
    }

    public void setLectureClass(LectureClass lectureClass) {
        stu.setLectureClass(stu);
    }

    public View saveStudent() {
        if (updating) {
            stuEjb.update(stu);
        } else {
            stuEjb.insert(stu);
        }
        conv.end();
        return View.students;
    }

    public View deleteStudent() {
        stuEjb.delete(stu);
        conv.end();
        return View.students;
    }
}
