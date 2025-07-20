package org.example;

import java.util.ArrayList;

/** Manages courses and GPA calculation by combining CourseList and GPACalculator */
public class CourseManager {
    private static CourseList courseList;
    private static GPACalculator calculator;

    public CourseManager() {
        this.courseList = new CourseList();
        this.calculator = GPACalculator.getInstance();
    }

    public void addCourse(Course course) {
        courseList.addCourse(course);
    }

    public void addCourses(ArrayList<Course> courses) {
        courseList.addCourses(courses);
    }

    public static double calculateGPA() {
        return calculator.calculateGPA(courseList.getCourseList());
    }

    public ArrayList<Course> getCourses() {
        return courseList.getCourseList();
    }
}
