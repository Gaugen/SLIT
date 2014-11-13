package no.uia.slit.bean;

import no.uia.slit.ejb.LectureClassEJB;
import no.uia.slit.entity.LectureClass;
import no.uia.slit.entity.Student;
import no.uia.slit.web.JsfUtils;
import no.uia.slit.web.View;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The backing bean for lectureClass.xhtml. The Named annotation marks it as a CDI
 * backing bean. The ConversationScoped annotation controls the lifecycle of the
 * bean. The lifetime of a ConversationScoped bean is explicitly managed in the
 * code.
 * @author Tor
 */
@Named("klassebean")
@ConversationScoped
public class KlasseBean implements Serializable {

    // The @Inject annotation is a request to the server to
    // give us a Conversation object, and store it in conv
    @Inject
    private Conversation conv;
    @EJB
    KlasseEJB klasseEjb;
    private Klasse klasse;
    private boolean updating;

    // LectureClass properties - These properties will be copied to a LectureClass
    // object, when the time comes to save the data in the database.
    private long klasseNo;
    private String name;
    private List<Student> students;
    private Klasse klasse;

    public KlasseBean() {
    }

    /**
     * called when the user selects a LectureClassfrom the list in
     * LectureClass.xhtml.
     * @param klasseNo
     */
    public void setklasseNo(long klasseNo) {
        if (conv.isTransient()) {
            conv.begin();
        }

        kla = klasseEjb.find(klasseNo);
        System.out.println("tull0" + kla);
        if (null == kla) {
            // we will get here if depNo is not a valid, or if
            // depNo is valid but there is no department with that depno
            updating = false;
            kla = new Klasse();
            System.out.println("tull1" + kla);
        } else System.out.println("tull2" + kla);{
            updating = true;
            System.out.println("tull3" + kla);
        }System.out.println("tull4" + kla);
            this.klasseNo = kla.getKlasseNo();
            name = kla.getNavn();
            students = kla.getStudents();
    }

    public long getKlasseNo() {
        System.out.println("tull2" + kla);
        return klasseNo;
    }

    public String getNavn() {
        System.out.println("tull2" + kla);
        return name;
    }

    public void setNavn(String name) {
        System.out.println("tull2" + kla);
        this.name = name;
    }

    public List<Student> getStudents() {
        System.out.println("tull2" + kla);
        return students;
    }

    public boolean isUpdating() {
        System.out.println("tull2" + kla);
        return updating;
    }
 

    public View lagreKlasse() {
        System.out.println("tull1" + kla);
    
        if (updating) {System.out.println("tull2" + kla);
            klasseEjb.update(kla);
        } else {System.out.println("tull3" + kla);
            klasseEjb.insert(kla);
        }
        conv.end();
        return View.klasse;
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
