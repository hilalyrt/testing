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
 * Tests the integration between Course and CourseList classes
 */
public class CourseAndCourseListIntegrationTest {

    @Mock
    private Course mockCourse1;
    @Mock
    private Course mockCourse2;
    @Mock
    private Course mockCourse3;

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
    public void testAddingAndRetrievingCourses() {
        // Create real CourseList for integration testing
        CourseList courseList = new CourseList();

        // Mock behavior 1: Configure course names
        // 3 sahte dersi CourseList’e ekleyip, doğru şekilde saklanıp saklanmadığını kontrol et
        when(mockCourse1.getName()).thenReturn("Math");
        when(mockCourse2.getName()).thenReturn("Physics");
        when(mockCourse3.getName()).thenReturn("Chemistry");

        // Mock behavior 2: Configure course credits
        when(mockCourse1.getCredit()).thenReturn(3.0);
        when(mockCourse2.getCredit()).thenReturn(4.0);
        when(mockCourse3.getCredit()).thenReturn(3.0);

        // Mock behavior 3: Configure course grades
        when(mockCourse1.getGrade()).thenReturn("A");
        when(mockCourse2.getGrade()).thenReturn("B+");
        when(mockCourse3.getGrade()).thenReturn("A-");

        // Add mock courses to the real CourseList - this is the integration point
        courseList.addCourse(mockCourse1);
        courseList.addCourse(mockCourse2);
        courseList.addCourse(mockCourse3);

        // Verify the integration worked correctly
        ArrayList<Course> retrievedCourses = courseList.getCourseList();
        assertEquals(3, retrievedCourses.size());
        assertSame(mockCourse1, retrievedCourses.get(0));
        assertSame(mockCourse2, retrievedCourses.get(1));
        assertSame(mockCourse3, retrievedCourses.get(2));

        // Verify the course information was correctly retrieved through integration
        assertEquals("Math", retrievedCourses.get(0).getName());
        assertEquals(3.0, retrievedCourses.get(0).getCredit());
        assertEquals("A", retrievedCourses.get(0).getGrade());
    }

    @Test
    public void testAddingMultipleCourses() {
        // Create real CourseList for integration testing
        CourseList courseList = new CourseList();

        // Mock behavior 4: Configure course information
        when(mockCourse1.getName()).thenReturn("Database");
        when(mockCourse2.getName()).thenReturn("Programming");

        // Create list of courses to add
        ArrayList<Course> coursesToAdd = new ArrayList<>(Arrays.asList(mockCourse1, mockCourse2));

        // Add multiple courses at once - this is another integration point
        courseList.addCourses(coursesToAdd);

        // Verify the integration worked correctly
        ArrayList<Course> retrievedCourses = courseList.getCourseList();
        assertEquals(2, retrievedCourses.size());
        assertEquals("Database", retrievedCourses.get(0).getName());
        assertEquals("Programming", retrievedCourses.get(1).getName());
    }

    @Test
    public void testExceptionHandling() {
        // Create real CourseList for integration testing
        CourseList courseList = new CourseList();

        // Mock behavior 5: Test exception handling with null courses
        assertThrows(IllegalArgumentException.class, () -> {
            courseList.addCourse(null);
        });

        // Create an empty list to test another exception case
        ArrayList<Course> emptyCourseList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            courseList.addCourses(emptyCourseList);
        });
    }
}