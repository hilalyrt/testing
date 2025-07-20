package org.example;

import java.util.ArrayList;

/** Represents the GPA calculator which calculates GPA of entered courses.
 * (Note: The Singleton pattern has been applied on this class.)
 */
public class GPACalculator {
    private static GPACalculator onlyInstance = null;

    /** No-arg constructor to make instances of class.
     */
    GPACalculator()
    {
    }

    /** Allocates the memory to an instance of class and returns it.
     * @return onlyInstance An one and only instance of class.
     */
    public static GPACalculator getInstance()
    {
        if (onlyInstance == null)
        {
            onlyInstance = new GPACalculator();
        }
        return onlyInstance;
    }

    /** Calculates the GPA of courses.
     * @param courseList An array list containing the courses.
     * @return gpa A double representing the GPA.
     * @throws IllegalArgumentException if grade is invalid or credit is invalid.
     */
    public double calculateGPA(ArrayList<Course> courseList)
    {
        int totalCredit = 0;
        double totalGradePoint = 0.0;
        double gpa;

        boolean isHighGPA = false;
        boolean isLowGPA = false;

        for(Course i: courseList) {
            if (i.getCredit() > 0) {
                totalCredit += i.getCredit();
                switch (i.getGrade()) {
                    case "A+":
                        totalGradePoint += i.getCredit() * 4.3;
                        break;
                    case "A":
                        totalGradePoint += i.getCredit() * 4;
                        break;
                    case "A-":
                        totalGradePoint += i.getCredit() * 3.7;
                        break;
                    case "B+":
                        totalGradePoint += i.getCredit() * 3.3;
                        break;
                    case "B":
                        totalGradePoint += i.getCredit() * 3;
                        break;
                    case "B-":
                        totalGradePoint += i.getCredit() * 2.7;
                        break;
                    case "C+":
                        totalGradePoint += i.getCredit() * 2.3;
                        break;
                    case "C":
                        totalGradePoint += i.getCredit() * 2;
                        break;
                    case "C-":
                        totalGradePoint += i.getCredit() * 1.7;
                        break;
                    case "D+":
                        totalGradePoint += i.getCredit() * 1.3;
                        break;
                    case "D":
                        totalGradePoint += i.getCredit() * 1;
                        break;
                    case "D-":
                        totalGradePoint += i.getCredit() * 0.7;
                        break;
                    case "F":
                        totalGradePoint += i.getCredit() * 0;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid Grade");
                }

                if (totalGradePoint / totalCredit > 3.5) {
                    isHighGPA = true;
                } else if (totalGradePoint / totalCredit < 2.0) {
                    isLowGPA = true;
                }
            }
            else
            {
                throw new IllegalArgumentException("Invalid Credit");
            }
        }

        if (isHighGPA && isLowGPA) {
            System.out.println("GPA is both high and low.");
        } else if (isHighGPA) {
            System.out.println("GPA is high.");
        } else if (isLowGPA) {
            System.out.println("GPA is low.");
        }

        gpa = totalGradePoint / totalCredit;
        return gpa;
    }


    public boolean isHighGPA() {
        return false;
    }
    public boolean isLowGPA() {

        return false;
    }
    public boolean isBothHighAndLowGPA() {

        return false;
    }

}
