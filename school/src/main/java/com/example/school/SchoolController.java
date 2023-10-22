package com.example.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSchool(@RequestBody  School school){
        schoolService.saveSchool(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchools(){
        return  ResponseEntity.ok(schoolService.findAllSchools());
    }


    @GetMapping("/with-students/{school-id}")
    public FullSchoolResponse findAllSchools(@PathVariable("school-id") Long id){

        return schoolService.findSchoolsWithStudents(id);
    }





}
