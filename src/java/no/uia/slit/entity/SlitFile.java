package no.uia.slit.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


/**
 * The Document entity represents an uploaded file.
 * @author even
 */
@Entity
public class SlitFile implements Serializable {
    
    private static final long serialVersionUID = 42L;
    
    @Id 
    @GeneratedValue
    private long id;
    private String filename;
    private String contentType;
    private int size;
    private Timestamp uploadTime;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    public SlitFile () {
        uploadTime = new Timestamp(System.currentTimeMillis());
    }
    
    public SlitFile(String filename, String contentType, int size, byte[] file){
        this();
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.file = file;
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
        final SlitFile other = (SlitFile) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public String toString () {
        return "[file " + id + " " + filename + " ]";
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getMimeType() {
        return contentType;
    }

    public int getSize() {
        return size;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
