package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    // Test: Default constructor doğru atama yapıyor mu?
    @Test
    void testDefaultConstructor() {
        Course course = new Course();
        assertEquals("", course.getName());
        assertEquals(0, course.getCredit());
        assertEquals("", course.getGrade());
    }

    // Test: Parametreli constructor doğru çalışıyor mu?
    @Test
    void testParameterizedConstructor() {
        Course course = new Course("Math", 3, "A");
        assertEquals("Math", course.getName());
        assertEquals(3, course.getCredit());
        assertEquals("A", course.getGrade());
    }

    // Test: setName() ve getName()
    @Test
    void testSetAndGetName() {
        Course course = new Course();
        course.setName("Physics");
        assertEquals("Physics", course.getName());
    }

    // Test: setCredit() ve getCredit()
    @Test
    void testSetAndGetCredit() {
        Course course = new Course();
        course.setCredit(4);
        assertEquals(4, course.getCredit());
    }

    // Test: setGrade() ve getGrade()
    @Test
    void testSetAndGetGrade() {
        Course course = new Course();
        course.setGrade("B+");
        assertEquals("B+", course.getGrade());
    }
}
