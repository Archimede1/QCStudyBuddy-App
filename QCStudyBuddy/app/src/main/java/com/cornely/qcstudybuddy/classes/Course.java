package com.cornely.qcstudybuddy.classes;

public class Course {
    private String crseName, crseIdentifier, id;

    public Course(String cName, String ci, String iD){
        this.crseIdentifier  = ci;
        this.crseName = cName;
        this.id = iD;
    }

    public String getCourseIdentfier(){
         return crseIdentifier;
    }

    public String getCourseName(){
        return crseName;
    }

    public String getId(){
        return id;
    }

    public void setCourseIdentifier(String ci){
        this.crseIdentifier = ci;
    }

    public void setCourseName(String courseName) {
        this.crseName = courseName;
    }

    public void setId(String id) {
        this.id = id;
    }
}
