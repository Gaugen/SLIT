package no.uia.slit.bean;

import no.uia.slit.ejb.LectureClassEJB;
import no.uia.slit.entity.LectureClass;
import no.uia.slit.entity.Student;
import no.uia.slit.web.JsfUtils;
import no.uia.slit.web.View;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The backing bean for lectureClass.xhtml. The Named annotation marks it as a CDI
 * backing bean. The ConversationScoped annotation controls the lifecycle of the
 * bean. The lifetime of a ConversationScoped bean is explicitly managed in the
 * code.
 * @author Tor
 */
@Named("lectureClassbean")
@ConversationScoped
public class LectureClassBean implements Serializable {

    // The @Inject annotation is a request to the server to
    // give us a Conversation object, and store it in conv
    @Inject
    private Conversation conv;
    @EJB
    LectureClassEJB classEjb;
    private LectureClass lectureClass;
    private boolean updating;

    // LectureClass properties - These properties will be copied to a LectureClass
    // object, when the time comes to save the data in the database.
    private long classNo;
    private String name;
    private List<Student> students;
    private LectureClass lecClass;

    public LectureClassBean() {
    }

    /**
     * called when the user selects a LectureClassfrom the list in
     * LectureClass.xhtml.
     * @param classNo
     */
    public void setClassNo(long classNo) {
        if (conv.isTransient()) {
            conv.begin();
        }

        lecClass = classEjb.find(classNo);
        System.out.println("tull0" + lecClass);
        if (null == lecClass) {
            // we will get here if classNo is not a valid, or if
            // classNo is valid but there is no lectureClass with that classNo
            updating = false;
            lecClass = new LectureClass();
            System.out.println("tull1" + lecClass);
        } else System.out.println("tull2" + lecClass);{
            updating = true;
            System.out.println("tull3" + lecClass);
        }System.out.println("tull4" + lecClass);
            this.classNo = lecClass.getClassNo();
            name = lecClass.getName();
            students = lecClass.getStudents();
    }

    public long getClassNo() {
        System.out.println("tull2" + lecClass);
        return classNo;
    }

    public String getName() {
        System.out.println("tull2" + lecClass);
        return name;
    }

    public void setName(String name) {
        System.out.println("tull2" + lecClass);
        this.name = name;
    }

    public List<Student> getStudents() {
        System.out.println("tull2" + lecClass);
        return students;
    }

    public boolean isUpdating() {
        System.out.println("tull2" + lecClass);
        return updating;
    }
 

    public View saveLectureClass() {
        System.out.println("tull1" + lecClass);
    
        if (updating) {System.out.println("tull2" + lecClass);
            classEjb.update(lecClass);
        } else {System.out.println("tull3" + lecClass);
            classEjb.insert(lecClass);
        }
        conv.end();
        return View.lectureClass;
    }

    public View deleteLectureClass() {System.out.println("tull2" + lecClass);
     
        try {
            classEjb.delete(lecClass);
        } catch (EJBException e) {
            JsfUtils.addMessage(name, e.getCause().getLocalizedMessage());
        }
        conv.end();

        return View.lectureClasses;
    }
}
