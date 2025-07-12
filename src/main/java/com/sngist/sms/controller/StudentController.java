package com.sngist.sms.controller;

import com.sngist.sms.dto.record.StudentRecord;
import com.sngist.sms.dto.requestDto.StudentRequestDto;
import com.sngist.sms.dto.responseDto.PostResponseDto;
import com.sngist.sms.dto.responseDto.StudentResponseDto;
import com.sngist.sms.fiegn.PostFiegnClient;
import com.sngist.sms.service.StudentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("api/v1/sms/")
public class StudentController {
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private StudentService studentService;
    @Autowired
    private PostFiegnClient postFiegnClient;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    ResponseEntity<StudentResponseDto> saveStudent(@Valid @RequestBody StudentRequestDto request) {
        StudentResponseDto savedStudent = studentService.saveStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping
    ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> allStudents = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(allStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDto dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("update")
    public StudentRecord test(@RequestBody StudentRecord record) {
        System.out.println(record.name());
        return new StudentRecord("asda", 25);
    }

    @GetMapping("rest-template")
    public ResponseEntity<String> testRestTemplate() {
        String url = "https://jsonplaceholder.typicode.com/users/1";
        return ResponseEntity.ok(restTemplate.getForObject(url, String.class));
    }

    @GetMapping("fiegn-client")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> posts = postFiegnClient.getAllPosts();
        Type listType = new TypeToken<List<PostResponseDto>>() {
        }.getType();
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(posts, listType));

    }
}
