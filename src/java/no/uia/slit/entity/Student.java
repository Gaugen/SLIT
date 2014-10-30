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
public class Student {
   @Id @GeneratedValue
   private long Id;
   
   @Column(unique=true)
   private String username;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Module module;
    @OneToMany(mappedBy = "student")
    private List<ModuleAssignment> module;

   
   public Student() {
   }

   public long getId() {
      return Id;
   }

   public void setId(long Id) {
      this.Id = Id;
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
      hash = 67 * hash + (int) (this.Id ^ (this.Id >>> 32));
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
      if (this.Id != other.Id) {
         return false;
      }
      return true;
   }
       public String toString() {
        return "[" + id + " " + name + "]";
    }

    public List<Module> getModule() {
        return module;
    }

    public  ModuleAssignment getModule(Module p) {
        for (ModuleAssignment pa : module) {
            if (pa.getModule().equals(p)) {
                return pa;
            }
        }
        return null;
    }

    public void removeModule(ModuleAssignment pa) {
        assignments.remove(pa);
    }

    public void addModule(ModuleAssignment pa) {
        assignments.add(pa);
    }
}
