package com.example.demo.controller;

import com.example.demo.entity.AverageAgeOfStudents;
import com.example.demo.entity.NumberOfStudents;
import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import com.example.demo.service.AvatarService;
import com.example.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/student/5
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/age") // GET http://localhost:8080/student/age?age=25
    public ResponseEntity<Collection<Student>> getStudentsByAge(@RequestParam Integer age) {
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

    @GetMapping // GET http://localhost:8080/student?min=15&max=18
    public ResponseEntity<Collection<Student>> findByAgeBetween(
            @RequestParam Integer min,
            @RequestParam Integer max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/faculty/{id}") // GET http://localhost:8080/student/faculty/5
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentFaculty(id));
    }

    @GetMapping("/count")
    public ResponseEntity<NumberOfStudents> getAllStudentsNumber() {
        return ResponseEntity.ok(studentService.getAllStudentsNumber());
    }

    @GetMapping("/average-age")
    public ResponseEntity<AverageAgeOfStudents> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/average-age-with-stream")
    public ResponseEntity<Double> getAverageAgeWithStream() {
        return ResponseEntity.ok(studentService.getAverageAgeWithStream());
    }

    @GetMapping("/last-students")
    public ResponseEntity<List<Student>> getLastStudentsById(@RequestParam Integer limit) {
        return ResponseEntity.ok(studentService.getLastStudentsById(limit));
    }

    @GetMapping("/name-starts-with")
    public ResponseEntity<List<String>> getStudentsByNameStartsWith(@RequestParam String letter) {
        return ResponseEntity.ok(studentService.getStudentsByNameStartsWith(letter));
    }

    @GetMapping("/names")
    public void echoAllStudentNames() {
        studentService.echoAllStudentNames();
    }

    @GetMapping("/names-sync")
    public void echoAllStudentNamesSync() {
        studentService.echoAllStudentNamesSync();
    }

    @PostMapping // POST http://localhost:8080/student
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping // PUT http://localhost:8080/student
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.editStudent(student));
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/student/5
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}