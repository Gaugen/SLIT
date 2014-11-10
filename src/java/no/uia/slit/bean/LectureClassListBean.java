/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.bean;

import no.uia.slit.ejb.LectureClassEJB;
import no.uia.slit.entity.LectureClass;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Tor
 */
@Named("lecClassListbean")
@RequestScoped
public class LectureClassListBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    LectureClassEJB classEjb;

    public LectureClassListBean() {
    }

    public List<LectureClass> getLectureClasses() {
        List<LectureClass> l = classEjb.findAll();
        System.err.print("lecClasslistbean.lectureClasses -> ");
        for (LectureClass c : l) {
            System.err.print(" " + c);
        }
        return l;
    }

    public boolean isLectureClassesDefined() {
        return classEjb.count() > 0;
    }

}
