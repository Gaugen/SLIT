package no.uia.slit.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Tor
 */
@Entity
public class Modul {

    @Id
    @GeneratedValue
    private long modulNo;
    private String modulNavn;
    private String beskrivelse;
    @OneToMany(mappedBy = "modul")
    private final List<ModulEvaluering> deltakere;

    @ManyToOne
    private Slitfil fil;

    public Modul() {
        deltakere = new ArrayList<ModulEvaluering>();
    }

//    public Modul(String modulnavn) {
//        this();
//        this.modulNavn = modulNavn;
//    }

    public long getModulNo() {
        return modulNo;
    }
    
    public void setModulNo(long modulNo) {
        this.modulNo = modulNo;
    }
    
    public String getModulNavn() {
        return modulNavn;
    }

    public void setModulNavn(String modulNavn) {
        this.modulNavn = modulNavn;
    }
    
    public String getBeskrivelse () {
        return beskrivelse;
    }
    
    public void setBeskrivelse (String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public List<ModulEvaluering> getDeltakere() {
        return deltakere;
    }

    public void addDeltaker(ModulEvaluering me) {
        for (ModulEvaluering del : deltakere) {
            System.err.println("Checking " + del);
            if (del.getStudent().equals(me.getStudent())) {
                return;
            }
        }
        deltakere.add(me);
    }

    public void removeDeltaker(ModulEvaluering me) {
        System.err.println("removing " + me + " from " + this);

        for (ModulEvaluering d : deltakere) {
            System.err.println(d);
        }
        deltakere.remove(me);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.modulNo ^ (this.modulNo >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Modul other = (Modul) obj;
        if (this.modulNo != other.modulNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ " + modulNo + " " + modulNavn + " ]";
    }

    public Slitfil getFil() {
        return fil;
    }

    public void setFil(Slitfil fil) {
        this.fil = fil;
    }
}
