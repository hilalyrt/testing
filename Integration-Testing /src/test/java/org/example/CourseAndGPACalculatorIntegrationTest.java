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
 * Tests the integration between Course and GPACalculator classes
 */
public class CourseAndGPACalculatorIntegrationTest {

    @Mock
    private Course mockCourse1;
    @Mock
    private Course mockCourse2;
    @Mock
    private Course mockCourse3;

    private GPACalculator calculator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        calculator = GPACalculator.getInstance();
    }

    @Test
    public void testIndividualGradeCalculation() {
        // Mock behavior 1: Configure A+ grade course
        when(mockCourse1.getGrade()).thenReturn("A+");
        when(mockCourse1.getCredit()).thenReturn(3.0);

        // Create list with single course
        ArrayList<Course> singleCourseList = new ArrayList<>(Arrays.asList(mockCourse1));

        // Test integration between Course and GPACalculator
        double gpa = calculator.calculateGPA(singleCourseList);

        // Verify result - should be 4.3 for A+
        assertEquals(4.3, gpa, 0.01);
    }

    @Test
    public void testMultipleGradeCalculation() {
        // Mock behavior 2: Configure multiple courses with different grades
        when(mockCourse1.getGrade()).thenReturn("A");
        when(mockCourse1.getCredit()).thenReturn(3.0);

        when(mockCourse2.getGrade()).thenReturn("B");
        when(mockCourse2.getCredit()).thenReturn(4.0);

        // Create list with multiple courses
        ArrayList<Course> multiCourseList = new ArrayList<>(Arrays.asList(mockCourse1, mockCourse2));

        // Test integration between Course and GPACalculator
        double gpa = calculator.calculateGPA(multiCourseList);

        // Verify result - should be (3.0*4.0 + 4.0*3.0) / 7.0 = 3.43
        assertEquals(3.43, gpa, 0.01);
    }

    @Test
    public void testInvalidGradeHandling() {
        // Mock behavior 3: Configure course with invalid grade
        when(mockCourse1.getGrade()).thenReturn("Z");
        when(mockCourse1.getCredit()).thenReturn(3.0);

        // Create list with invalid grade course
        ArrayList<Course> invalidGradeList = new ArrayList<>(Arrays.asList(mockCourse1));

        // Test integration with invalid grade
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateGPA(invalidGradeList);
        });
    }

    @Test
    public void testInvalidCreditHandling() {
        // Mock behavior 4: Configure course with invalid credit
        when(mockCourse1.getGrade()).thenReturn("A");
        when(mockCourse1.getCredit()).thenReturn(0.0);

        // Create list with invalid credit course
        ArrayList<Course> invalidCreditList = new ArrayList<>(Arrays.asList(mockCourse1));

        // Test integration with invalid credit
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateGPA(invalidCreditList);
        });
    }

    @Test
    public void testWeightedGPACalculation() {
        // Mock behavior 5: Configure courses with different credits
        when(mockCourse1.getGrade()).thenReturn("A");
        when(mockCourse1.getCredit()).thenReturn(2.0);

        when(mockCourse2.getGrade()).thenReturn("A");
        when(mockCourse2.getCredit()).thenReturn(4.0);

        when(mockCourse3.getGrade()).thenReturn("A");
        when(mockCourse3.getCredit()).thenReturn(1.0);

        // Create list with weighted courses
        ArrayList<Course> weightedCourseList = new ArrayList<>(Arrays.asList(mockCourse1, mockCourse2, mockCourse3));

        // Test integration with weighted courses
        double gpa = calculator.calculateGPA(weightedCourseList);

        // Verify result - all courses have A grade (4.0) but different credits
        // Should be (2.0*4.0 + 4.0*4.0 + 1.0*4.0) / 7.0 = 4.0
        assertEquals(4.0, gpa, 0.01);
    }
}