package no.uia.slit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Tor
 */
@Entity
public class ModulEvaluering implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Foreleser foreleser;
    @ManyToOne
    private Modul modul;

    public ModulEvaluering() {
    }

    public ModulEvaluering(Modul modul, Student student, Foreleser foreleser) {
        this.student = student;
        this.modul = modul;
        this.foreleser = foreleser;
    
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Comparing "+this+" to "+obj);
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModulEvaluering other = (ModulEvaluering) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
        public Foreleser getForeleser() {
        return foreleser;
    }

    public void setForeleser(Foreleser foreleser) {
        this.foreleser = foreleser;
    }

    public Modul getModul() {
        return modul;
    }

    public void setModul(Modul modul) {
        this.modul = modul;
    }

    @Override
    public String toString() {
        return "[pa "+id+" "+modul+" "+student+" ]";
    }
}
