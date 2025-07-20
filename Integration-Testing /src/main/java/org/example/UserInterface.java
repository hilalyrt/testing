package org.example;

import java.util.Scanner;

/** Represents the User Interface of an application.
 * @author Daljeet Singh (Dev-Daljeet)
 * @version 1.0
 */
public class UserInterface
{
    /** Runs the program or starting point of execution.
     * @param args Command-line arguments
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello!\nPlease enter the number of courses for which you want to calculate GPA");
        int numOfCourses = input.nextInt();
        CourseList courseList = new CourseList();
        GPACalculator calculator = GPACalculator.getInstance();
        try {
            courseList.addCourse(numOfCourses);
            System.out.printf("Your GPA is %.2f",calculator.calculateGPA(courseList.getCourseList()));
        }
        catch (IllegalArgumentException exception)
        {
            System.out.println("Exception thrown: "+exception);
        }
    }
}

