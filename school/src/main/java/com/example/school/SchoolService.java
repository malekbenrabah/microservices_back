package com.example.school;

import com.example.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    StudentClient studentClient;

    public void saveSchool(School school){
        schoolRepository.save(school);
    }

    public List<School> findAllSchools(){
        return  schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Long id){
        var school = schoolRepository.findById(id).orElse(null);
        var students= studentClient.findAllStudentsBySchool(id);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getName())
                .students(students)
                .build();

    }

}
