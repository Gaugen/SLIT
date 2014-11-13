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
@Named("klasseConverter")
@RequestScoped
@FacesConverter(forClass = Klasse.class)
public class KlasseConverter implements Converter {

    @EJB
    LectureClassEJB lectureClassEjb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        long lecClassId = Long.parseLong(value);
        if (null == KlasseEjb) {
            throw new ConverterException("No EJB!");
        }
        LectureClass kla = KlasseEjb.find(klaId);
        if (null == kla) {
            throw new ConverterException("Cannot convert \"" + value + "\" to Klasse");
        }
        return kla;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (null != value && value instanceof Klasse) {
            Klasse kla = (Klasse) value;
            return "" + kla.getKlasseNo();
        }
        throw new ConverterException("Illegal value: not a Klasseobject in " + component);
    }

}
