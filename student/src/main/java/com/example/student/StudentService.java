package com.example.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Style;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    public List<Student> findAllStudents(){
        return  studentRepository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Long id){
        return studentRepository.findAllBySchoolId(id);
    }

}
