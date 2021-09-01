package com.cornely.qcstudybuddy.classes;

import java.util.ArrayList;

public class CourseCatalog {
    private ArrayList<Course> courseCatalog;
    private int courseCount;

    public CourseCatalog(){
        this.courseCatalog = new ArrayList<Course>();
        this.courseCount = 0;
    }

    public void addToCatalog(Course newCourse){
        this.courseCatalog.add(newCourse);
        courseCount++;
    }

    public void removeCourseFromCatalog(Course newCourse){
        this.courseCatalog.remove(newCourse);
        courseCount--;
    }

    public ArrayList<Course> getCourseCatalog(){
        return courseCatalog;
    }
}
