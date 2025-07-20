package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseListTest {

    @Test
    void testInitialCourseListIsEmpty() {
        CourseList courseList = new CourseList();
        assertTrue(courseList.getCourseList().isEmpty());
    }

    @Test
    void testAddSingleCourse() {
        CourseList courseList = new CourseList();
        Course course = new Course("Math", 3, "A");
        courseList.addCourse(course);
        assertEquals(1, courseList.getCourseList().size());
        assertEquals("Math", courseList.getCourseList().get(0).getName());
    }

    @Test
    void testAddNullCourseThrowsException() {
        CourseList courseList = new CourseList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseList.addCourse(null);
        });
        assertEquals("Null course cannot be added", exception.getMessage());
    }

    @Test
    void testSetAndGetCourseList() {
        CourseList courseList = new CourseList();
        ArrayList<Course> mockList = new ArrayList<>();
        mockList.add(new Course("Test", 2, "B"));
        courseList.setCourseList(mockList);
        assertEquals(1, courseList.getCourseList().size());
        assertEquals("Test", courseList.getCourseList().get(0).getName());
    }

    @Test
    void testAddMultipleCoursesSuccessfully() {
        CourseList courseList = new CourseList();
        ArrayList<Course> newCourses = new ArrayList<>();
        newCourses.add(new Course("Math", 3, "A"));
        newCourses.add(new Course("Physics", 2, "B+"));
        courseList.addCourses(newCourses);
        assertEquals(2, courseList.getCourseList().size());
    }

    @Test
    void testAddCoursesWithNullListThrowsException() {
        CourseList courseList = new CourseList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseList.addCourses(null);
        });
        assertEquals("Course list cannot be null or empty", exception.getMessage());
    }

    @Test
    void testAddCoursesWithEmptyListThrowsException() {
        CourseList courseList = new CourseList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseList.addCourses(new ArrayList<>());
        });
        assertEquals("Course list cannot be null or empty", exception.getMessage());
    }

    @Test
    void testAddCoursesWithNullInsideListThrowsException() {
        CourseList courseList = new CourseList();
        ArrayList<Course> list = new ArrayList<>();
        list.add(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseList.addCourses(list);
        });
        assertEquals("Null course cannot be added", exception.getMessage());
    }
}
