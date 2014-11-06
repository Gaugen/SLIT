/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
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
public class Module {
   @Id 
   @GeneratedValue
   private long moduleNo;
   @Column(unique=true)
   private String moduleName;
   private String description;
   @ManyToOne 
   private SlitFile file;
   @OneToMany (mappedBy = "module")
   private List<Assessment> participants;
   
   public Module() {
       participants = new ArrayList<Assessment>();
   }
   
    public Module(String moduleName, LectureClass lectureClass  ) {
        this();
        this.moduleName = moduleName;
    }

    public long getModuleNo() {
        return moduleNo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<Assessment> getParticipant() {
        return participants;
    }

    public void addParticipant(Assessment pa) {
        for (Assessment part : participants) {
            System.err.println("Checking " + part);
            if (part.getStudent().equals(pa.getStudent())) {
                return;
            }
        }
        participants.add(pa);
    }

    public void removeParticipant(Assessment pa) {
        System.err.println("removing " + pa + " from " + this);

        for (Assessment s : participants) {
            System.err.println(s);
        }
        participants.remove(pa);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.moduleNo ^ (this.moduleNo >>> 32));
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
        final Module other = (Module) obj;
        if (this.moduleNo != other.moduleNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ " + moduleNo + " " + moduleName + " ]";
    }

    public SlitFile getFile() {
        return file;
    }

    public void setFile(SlitFile file) {
        this.file = file;
    }
}
