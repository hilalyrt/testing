package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/** Represents the course list which contains courses.
 */
public class CourseList {
    private ArrayList<Course> courseList;

    /** No-arg constructor to make an instance of class.
     */
    public CourseList() {
        courseList = new ArrayList<>();
    }

    /** Gets the course list.
     * @return An array list containing the courses.
     */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    /** Adds a single course to the list.
     * @param course the course to add.
     * @throws IllegalArgumentException if course is null.
     */
    public void addCourse(Course course) {
        if (course != null) {
            courseList.add(course);
        } else {
            throw new IllegalArgumentException("Null course cannot be added");
        }
    }

    /** Adds multiple courses (testable version).
     * @param courses List of Course objects
     * @throws IllegalArgumentException if list is null or contains null values.
     */
    public void addCourses(ArrayList<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            throw new IllegalArgumentException("Course list cannot be null or empty");
        }
        for (Course course : courses) {
            if (course != null) {
                courseList.add(course);
            } else {
                throw new IllegalArgumentException("Null course cannot be added");
            }
        }
    }

    /** Sets the course list.
     * @param courseList An array list containing the courses.
     */
    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    /** Adds the course(s) in course list via user input.
     * @param numOfCourses An integer representing the number of courses to be added.
     * @throws IllegalArgumentException if number of courses is invalid.
     */
    public void addCourse(int numOfCourses) {
        if (numOfCourses > 0) {
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < numOfCourses; i++) {
                System.out.println("Please enter the name of course " + (i + 1));
                String name = input.next();
                System.out.println("Please enter the credit of course " + (i + 1));
                int credit = input.nextInt();
                System.out.println("Please enter the grade you achieved in course " + (i + 1));
                String grade = input.next();
                Course course = new Course(name, credit, grade);
                courseList.add(course);
            }
        } else {
            throw new IllegalArgumentException("Invalid number of courses");
        }
    }
}
