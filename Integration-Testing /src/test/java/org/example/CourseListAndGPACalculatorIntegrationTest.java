package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests the integration between CourseList and GPACalculator classes
 */
public class CourseListAndGPACalculatorIntegrationTest {

    @Mock
    private Course mockCourse1;
    @Mock
    private Course mockCourse2;
    @Mock
    private Course mockCourse3;
    @Mock
    private CourseList mockCourseList;

    private ArrayList<Course> mockCourses;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockCourses = new ArrayList<>();
        mockCourses.add(mockCourse1);
        mockCourses.add(mockCourse2);
        mockCourses.add(mockCourse3);
    }

    @Test
    public void testCalculatingGPA() {
        // Create real objects for integration testing
        CourseList courseList = new CourseList();
        GPACalculator calculator = GPACalculator.getInstance();

        // Mock behavior 1: Configure course grades and credits
        when(mockCourse1.getGrade()).thenReturn("A");
        when(mockCourse1.getCredit()).thenReturn(3.0);

        when(mockCourse2.getGrade()).thenReturn("B+");
        when(mockCourse2.getCredit()).thenReturn(4.0);

        when(mockCourse3.getGrade()).thenReturn("A-");
        when(mockCourse3.getCredit()).thenReturn(3.0);

        // Add mock courses to real CourseList - this is the integration point
        courseList.addCourse(mockCourse1);
        courseList.addCourse(mockCourse2);
        courseList.addCourse(mockCourse3);

        // Use real GPACalculator with CourseList - this is the integration point
        double gpa = calculator.calculateGPA(courseList.getCourseList());

        // Verify the integration result is in valid range
        assertTrue(gpa >= 0.0 && gpa <= 4.3, "GPA should be in valid range");

        // The expected GPA would be approximately 3.6 based on the grades and credits
        // (3.0*4.0 + 4.0*3.3 + 3.0*3.7) / (3.0 + 4.0 + 3.0) = 3.6
        assertEquals(3.6, gpa, 0.1);
    }

    @Test
    public void testHighGPA() {
        // Create real objects for integration testing
        CourseList courseList = new CourseList();
        GPACalculator calculator = GPACalculator.getInstance();

        // Mock behavior 2: Configure courses for high GPA
        when(mockCourse1.getGrade()).thenReturn("A+");
        when(mockCourse1.getCredit()).thenReturn(4.0);

        when(mockCourse2.getGrade()).thenReturn("A");
        when(mockCourse2.getCredit()).thenReturn(3.0);

        // Add mock courses to real CourseList
        courseList.addCourse(mockCourse1);
        courseList.addCourse(mockCourse2);

        // Integration test with high GPA
        double gpa = calculator.calculateGPA(courseList.getCourseList());

        // Verify high GPA result
        assertTrue(gpa > 4.0, "GPA should be high");
    }

    @Test
    public void testLowGPA() {
        // Create real objects for integration testing
        CourseList courseList = new CourseList();
        GPACalculator calculator = GPACalculator.getInstance();

        // Mock behavior 3: Configure courses for low GPA
        when(mockCourse1.getGrade()).thenReturn("D");
        when(mockCourse1.getCredit()).thenReturn(3.0);

        when(mockCourse2.getGrade()).thenReturn("F");
        when(mockCourse2.getCredit()).thenReturn(3.0);

        // Add mock courses to real CourseList
        courseList.addCourse(mockCourse1);
        courseList.addCourse(mockCourse2);

        // Integration test with low GPA
        double gpa = calculator.calculateGPA(courseList.getCourseList());

        // Verify low GPA result
        assertTrue(gpa < 1.0, "GPA should be low");
    }

    @Test
    public void testMixedGradeGPA() {
        // Create real objects for integration testing
        CourseList courseList = new CourseList();
        GPACalculator calculator = GPACalculator.getInstance();

        // Mock behavior 4: Configure courses with mixed grades
        when(mockCourse1.getGrade()).thenReturn("A");
        when(mockCourse1.getCredit()).thenReturn(3.0);

        when(mockCourse2.getGrade()).thenReturn("C");
        when(mockCourse2.getCredit()).thenReturn(3.0);

        when(mockCourse3.getGrade()).thenReturn("B+");
        when(mockCourse3.getCredit()).thenReturn(4.0);

        // Add mock courses to real CourseList
        courseList.addCourse(mockCourse1);
        courseList.addCourse(mockCourse2);
        courseList.addCourse(mockCourse3);

        // Integration test with mixed grades
        double gpa = calculator.calculateGPA(courseList.getCourseList());

        // Verify mixed GPA result
        assertTrue(gpa > 2.0 && gpa < 4.0, "GPA should be in mid-range");
    }

    @Test
    public void testEmptyCourseList() {
        // Create real objects for integration testing
        CourseList courseList = new CourseList();
        GPACalculator calculator = GPACalculator.getInstance();

        // Mock behavior 5: Configure mock CourseList to return empty list
        when(mockCourseList.getCourseList()).thenReturn(new ArrayList<>());

        // Test integration with empty course list
        // This should throw an exception in the real implementation
        assertThrows(ArithmeticException.class, () -> {
            calculator.calculateGPA(mockCourseList.getCourseList());
        });
    }
}