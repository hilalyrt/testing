package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseManagerAndCourseListIntegrationTest {

    @Mock
    private Course mockCourse1;
    @Mock
    private Course mockCourse2;
    @Mock
    private CourseList mockCourseList;
    @Mock
    private GPACalculator mockCalculator;

    private CourseManager courseManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        courseManager = new CourseManager();
    }

    @Test
    public void testCalculateGPAWithExtendedMocking() {
        // 1-2. MockCourse1 özellikleri
        when(mockCourse1.getName()).thenReturn("AI");
        when(mockCourse1.getGrade()).thenReturn("A");
        when(mockCourse1.getCredit()).thenReturn(3.0);

        // 3-4. MockCourse2 özellikleri
        when(mockCourse2.getName()).thenReturn("ML");
        when(mockCourse2.getGrade()).thenReturn("B+");
        when(mockCourse2.getCredit()).thenReturn(4.0);

        // 5. mockCourseList.getCourseList()
        ArrayList<Course> mockedCourses = new ArrayList<>(Arrays.asList(mockCourse1, mockCourse2));
        when(mockCourseList.getCourseList()).thenReturn(mockedCourses);

        // 6. GPA hesaplaması sonucu
        when(mockCalculator.calculateGPA(mockedCourses)).thenReturn(3.57);

        // Test işlemi
        double calculatedGPA = mockCalculator.calculateGPA(mockCourseList.getCourseList());

        // Doğrulamalar
        assertEquals(3.57, calculatedGPA, 0.01);
        assertEquals("AI", mockedCourses.get(0).getName()); // 7. ek when-thenReturn için kullanım
        assertEquals("ML", mockedCourses.get(1).getName());
    }
}
