// src/edu/ccrm/service/CourseService.java
package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import edu.ccrm.util.CourseCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService implements Searchable<Course> {
    private List<Course> courses;
    
    public CourseService() {
        this.courses = new ArrayList<>();
    }
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    public Course getCourseByCode(CourseCode code) {
        return courses.stream()
                .filter(c -> c.getCode().equals(code) && c.isActive())
                .findFirst()
                .orElse(null);
    }
    
    public List<Course> getAllCourses() {
        return courses.stream()
                .filter(Course::isActive)
                .collect(Collectors.toList());
    }
    
    public void updateCourse(Course course) {
        courses.removeIf(c -> c.getCode().equals(course.getCode()));
        courses.add(course);
    }
    
    public void deactivateCourse(CourseCode code) {
        Course course = getCourseByCode(code);
        if (course != null) {
            course.setActive(false);
        }
    }
    
    public List<Course> getCoursesByInstructor(Instructor instructor) {
        return courses.stream()
                .filter(c -> c.isActive() &&