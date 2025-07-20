package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GPACalculatorTest {

    private final GPACalculator calculator = GPACalculator.getInstance();

    // GPA hesaplama: farklı not ağırlıkları ile kesin sonuç
    @Test
    public void testGPAExactCalculation() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Math", 3, "A"));     // 3 * 4.0 = 12.0
        courses.add(new Course("Physics", 4, "B"));  // 4 * 3.0 = 12.0
        courses.add(new Course("History", 3, "C"));  // 3 * 2.0 = 6.0

        double gpa = calculator.calculateGPA(courses);
        // Total Grade Points = 30.0, Total Credit = 10, GPA = 3.0
        assertEquals(3.0, gpa, 0.001);
    }

    // GPA = 0.0 (tüm dersler F)
    @Test
    public void testGPAZeroAllFailed() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Math", 3, "F"));
        courses.add(new Course("Physics", 4, "F"));
        double gpa = calculator.calculateGPA(courses);
        assertEquals(0.0, gpa, 0.001);
    }

    // GPA = 4.3 (tek ders A+)
    @Test
    public void testGPAWithSingleAPlus() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Math", 1, "A+")); // 1 * 4.3 = 4.3
        double gpa = calculator.calculateGPA(courses);
        assertEquals(4.3, gpa, 0.001);
    }

    // GPA = ortalama ağırlıklı (karma notlar)
    @Test
    public void testWeightedGPA() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Course1", 2, "A"));  // 8.0
        courses.add(new Course("Course2", 3, "B"));  // 9.0
        courses.add(new Course("Course3", 5, "C"));  // 10.0

        double gpa = calculator.calculateGPA(courses);
        // Total Grade Point = 27, Total Credit = 10, GPA = 2.7
        assertEquals(2.7, gpa, 0.001);
    }


    // P1: Valid inputs → High GPA (GPA > 3.5)
    @Test
    public void testHighGPAPath() {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Math", 3, "A+")); // 4.3
        courseList.add(new Course("Physics", 3, "A")); // 4.0
        double gpa = calculator.calculateGPA(courseList);
        assertTrue(gpa > 3.5);
    }

    // P2: Valid inputs → Low GPA (GPA < 2.0)
    @Test
    public void testLowGPAPath() {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Biology", 3, "D")); // 1.0
        courseList.add(new Course("Chemistry", 3, "F")); // 0.0
        double gpa = calculator.calculateGPA(courseList);
        assertTrue(gpa < 2.0);
    }

    // P3: Valid inputs → GPA is neither high nor low (2.0 ≤ GPA ≤ 3.5)
    @Test
    public void testNeutralGPAPath() {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course("English", 3, "B")); // 3.0
        courseList.add(new Course("Geography", 3, "C")); // 2.0
        double gpa = calculator.calculateGPA(courseList);
        assertTrue(gpa >= 2.0 && gpa <= 3.5);
    }

    // P4: Invalid credit → triggers "Invalid Credit" exception
    @Test
    public void testInvalidCreditPath() {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Art", -2, "A"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateGPA(courseList);
        });
        assertEquals("Invalid Credit", ex.getMessage());
    }

    // P5: Invalid grade → triggers "Invalid Grade" exception
    @Test
    public void testInvalidGradePath() {
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Music", 3, "Z")); // not a valid grade
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateGPA(courseList);
        });
        assertEquals("Invalid Grade", ex.getMessage());
    }
    @Test
    void testOnlyLowGPAPath() {
        ArrayList<Course> cl = new ArrayList<>();
        cl.add(new Course("X", 3, "F"));
        cl.add(new Course("Y", 3, "D"));
        double gpa = calculator.calculateGPA(cl);
        assertTrue(gpa < 2.0);
    }

    @Test
    void testOnlyHighGPAPath() {
        ArrayList<Course> cl = new ArrayList<>();
        cl.add(new Course("X", 3, "A+"));
        cl.add(new Course("Y", 3, "A"));
        double gpa = calculator.calculateGPA(cl);
        assertTrue(gpa > 3.5);
    }

    @Test
    void testBothHighAndLowPath() {
        ArrayList<Course> cl = new ArrayList<>();
        cl.add(new Course("A", 3, "A+")); // yüksek
        cl.add(new Course("B", 3, "F"));  // düşük
        double gpa = calculator.calculateGPA(cl);
        // Burada isHighGPA + isLowGPA kombinasyonu aktif olur
    }

}
