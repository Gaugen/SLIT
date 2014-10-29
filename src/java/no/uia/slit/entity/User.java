/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tor
 */
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue
    private long userID;
    
    @NotNull
    @Size (min = 1)
    private String name;
    private String email;
    private String passord;
    private int rettigheter;
    
    @OneToMany (mappedBy = "user")
    private List<Modul> moduler;
    
    /** Her kommer atributter i getters og setters */
    public User() {
        moduler = new ArrayList<Moduler>();
    }

    public long getUserID() {
        return userID;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
     public void setName(String name) {
        this.name = name;
    }

         public String getEmail() {
        return email;
    }
    
     public void setEmail(String email) {
        this.email = email;
    }
     
         public String getPassord() {
        return passord;
    }
    
     public void setPassord(String passord) {
        this.passord = passord;
    }
     
         public int getRettigheter() {
        return rettigheter;
    }
    
     public void setRettigheter(int rettigheter) {
        this.rettigheter = rettigheter;
    }
    
     public List<Moduler> getModuler() {
         return moduler;
     }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrering)) {
            return false;
        }
        Registrering other = (Registrering) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "no.uia.slit.entity.Registrering[ id=" + id + " ]";
    }
    
}
