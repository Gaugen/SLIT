package no.uia.slit.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author evenal
 */
@Entity
public class Slitfil implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue
    private long id;
    private String filNavn;
    private String innholdsType;
    private int mengde;

    private Timestamp opplastningsTid;

    /**
     * The actual content of the fil. It is stored as a BLOB in the database.
     * FetchType.LAZY means that JPA will not retrieve it from the database
     * before we explicitly as for it. This is to avoid unnecessary load on the
     * database and application server.
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fil;

    public Slitfil() {
        opplastningsTid = new Timestamp(System.currentTimeMillis());
    }

    public Slitfil(String filNavn, String innholdsType, int mengde, byte[] fil) {
        this();
        this.filNavn = filNavn;
        this.innholdsType = innholdsType;
        this.mengde = mengde;
        this.fil = fil;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Slitfil other = (Slitfil) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[fil " + id + " " + filNavn + " ]";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public String getFilenavn() {
        return filNavn;
    }

    public String getInnholdsType() {
        return innholdsType;
    }

    public int getMengde() {
        return mengde;
    }

    public Timestamp getOpplastningsTid() {
        return opplastningsTid;
    }

    public byte[] getFil() {
        return fil;
    }

    public void setFilnavn(String filnavn) {
        this.filNavn = filnavn;
    }

    public void setInnholdsType(String innholdsType) {
        this.innholdsType = innholdsType;
    }

    public void setMengde(int mengde) {
        this.mengde = mengde;
    }

    public void setFil(byte[] fil) {
        this.fil = fil;
    }
}
