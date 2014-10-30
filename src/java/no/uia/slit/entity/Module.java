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
 * @author even
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
   private SlitFile ressurs;
   @OneToMany (mappedBy = "module")
   private List<Assessment> students;
   
   public Module() {
       students = new ArrayList<Assessment>();
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

    public List<Assessment> getStudents() {
        return students;
    }

    public void addStudent(Assessment pa) {
        for (Assessment stu : students) {
            System.err.println("Checking " + stu);
            if (stu.getStudent().equals(pa.getStudent())) {
                return;
            }
        }
        students.add(pa);
    }

    public void removeStudent(Assessment pa) {
        System.err.println("removing " + pa + " from " + this);

        for (Assessment s : students) {
            System.err.println(s);
        }
        students.remove(pa);
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
