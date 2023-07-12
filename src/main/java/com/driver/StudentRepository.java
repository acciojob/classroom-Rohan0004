package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String,Student> studentMap = new HashMap<>();

    Map<String,Teacher> teacherMap = new HashMap<>();

    Map<String, List<String>> teacherStudentsMap = new HashMap<>();
    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        teacherStudentsMap.putIfAbsent(teacher,new ArrayList<>());
        teacherStudentsMap.get(teacher).add(student);
        Teacher teacher1=teacherMap.get(teacher);
        if(teacher1!=null) teacher1.setNumberOfStudents(1+teacher1.getNumberOfStudents());
    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherStudentsMap.get(teacher);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        teacherMap.remove(teacher);
        teacherStudentsMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        teacherStudentsMap.clear();
        teacherMap.clear();
    }
}
