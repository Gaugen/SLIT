package no.uia.slit.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Foreleser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 1)
    private String navn;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Modul modul;
    @OneToMany(mappedBy = "foreleser")
    private final List<ModulEvaluering> evaluering;

    @SuppressWarnings("Convert2Diamond")
    public Foreleser() {
        evaluering = new ArrayList<ModulEvaluering>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Modul getModul() {
        return modul;
    }

    public void setModul(Modul modul) {
        this.modul = modul;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Foreleser other = (Foreleser) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + " " + navn + "]";
    }

    public List<ModulEvaluering> getEvaluering() {
        return evaluering;
    }

    public ModulEvaluering getEvaluering(Modul m) {
        for (ModulEvaluering me  : evaluering) {
            if (me.getModul().equals(m)) {
                return me;
            }
        }
        return null;
    }

    public void removeEvaluering(ModulEvaluering me) {
        evaluering.remove(me);
    }

    public void addEvaluering(ModulEvaluering me) {
        evaluering.add(me);
    }
}
