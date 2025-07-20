package org.example;

/** Represents a course of a program.
 * @author Daljeet Singh (Dev-Daljeet)
 * @version 1.0
 */
public class Course
{
    private String name;
    private int credit;
    private String grade;

    /** No-arg constructor to make an instance of class.
     */
    public Course()
    {
        name = "";
        credit = 0;
        grade = "";
    }

    /** Parameterized constructor to make an instance of class.
     * @param name A String representing the name of a course.
     * @param credit An integer representing the credit of a course.
     * @param grade A String representing the grade of a course.
     */
    public Course(String name, int credit, String grade)
    {
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    /** Gets the name of a course.
     * @return A String representing the name of a course.
     */
    public String getName() {
        return name;
    }

    /** Sets the name of a course.
     * @param name A String representing the name of a course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Gets the credit of a course.
     * @return An integer representing the credit of a course.
     */
    public double getCredit() {
        return credit;
    }

    /** Sets the credit of a course.
     * @param credit An integer representing the credit of a course.
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }

    /** Gets the grade of a course.
     * @return A String representing the grade of a course.
     */
    public String getGrade() {
        return grade;
    }

    /** Sets the grade of a course.
     * @param grade A String representing the grade of a course.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
