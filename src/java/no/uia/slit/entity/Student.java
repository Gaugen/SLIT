/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author even
 */
@Entity
public class Student implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
   @Id 
   @GeneratedValue
   private long id;
   
   @Column(unique=true)
   private String name;
   
    @ManyToOne
    @JoinColumn(nullable = false)
    private LectureClass lectureClass;
    @OneToMany(mappedBy = "student")
    private List<Assessment> assessments;

   
   public Student() {
       assessments = new ArrayList<Assessment>();
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   public LectureClass getLectureClass() {
        return lectureClass;
    }

   public void setLectureClass(LectureClass lectureClass) {
        this.lectureClass = lectureClass;
    }
   
   @Override
   public int hashCode() {
      int hash = 3;
      hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
      final Student other = (Student) obj;
      if (this.id != other.id) {
         return false;
      }
      return true;
   }
       public String toString() {
        return "[" + id + " " + name + "]";
    }

    public List<Assessment> getAssessment() {
        return assessments;
    }

    public  Assessment getAssessment(Module p) {
        for (Assessment pa : assessments) {
            if (pa.getModule().equals(p)) {
                return pa;
            }
        }
        return null;
    }

    public void removeAssessment(Assessment pa) {
        assessments.remove(pa);
    }

    public void addAssessment(Assessment pa) {
        assessments.add(pa);
    }
}
