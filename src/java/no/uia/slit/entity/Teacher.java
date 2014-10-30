/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Teacher implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
   @Id 
   @GeneratedValue
   private long id;
   @NotNull
   @Column(unique=true)
   private String username;
   

    @ManyToOne
    @JoinColumn(nullable = false)
    private Module module;
    @OneToMany(mappedBy = "teacher")
    private List<Assessment> assessments;

   
   public Teacher() {
       assessments = new ArrayList<Assessment>();
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
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
      final Teacher other = (Teacher) obj;
      if (this.id != other.id) {
         return false;
      }
      return true;
   }
       public String toString() {
        return "[" + id + " " + username + "]";
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
