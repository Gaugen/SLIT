/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author TorArne
 */
@Entity
public class LectureClass implements Serializable {
    private static final long serialVersionUID = 42L;
    
    @Id
    @GeneratedValue
    private Long classNo;
    
    @NotNull
    @Size(min = 1)
    private String name;
    @OneToMany(mappedBy = "lectureClass")
    private List<Student> students;
    
    /**
     * Getters and setters 
     */
    
    public LectureClass() {
        students = new ArrayList<Student>();
    }

    public Long getClassNo() {
        return classNo;
    }

    public void setClassNo(Long classNo) {
        this.classNo = classNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student stu) {
        if (!students.contains(stu)) {
            students.add(stu);
        }
    }

    public void removeStudent(Student stu) {
        students.remove(stu);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.classNo ^ (this.classNo >>> 32));
        return hash;
    }

   @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        LectureClass other = (LectureClass) obj;
        if (classNo != other.classNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(classNo);
        sb.append(" ");
        sb.append(name);
//        sb.append(" {");
//        for (Employee emp : employees) {
//            sb.append(" ");
//            sb.append(emp);
//        }
//        sb.append(" }");

        return sb.toString();
    }
}