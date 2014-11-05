/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.bean;

import no.uia.slit.ejb.LectureClassEJB;
import no.uia.slit.entity.LectureClass;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Tor 
 */
@Named("lectureClassconverter")
@RequestScoped
@FacesConverter(forClass = LectureClass.class)
public class LectureClassConverter implements Converter {

    @EJB
    LectureClassEJB lectureClassEjb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        long lecClassId = Long.parseLong(value);
        if (null == lectureClassEjb) {
            throw new ConverterException("No EJB!");
        }
        LectureClass lecClass = lectureClassEjb.find(lecClassId);
        if (null == lecClass) {
            throw new ConverterException("Cannot convert \"" + value + "\" to Lecture Class");
        }
        return lecClass;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (null != value && value instanceof LectureClass) {
            LectureClass lecClass = (LectureClass) value;
            return "" + lecClass.getClassNo();
        }
        throw new ConverterException("Illegal value: not a LectureClass object in " + component);
    }

}
