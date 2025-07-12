package com.sngist.sms.serviceImpl;

import com.sngist.sms.dto.requestDto.StudentRequestDto;
import com.sngist.sms.dto.responseDto.StudentResponseDto;
import com.sngist.sms.entity.Student;
import com.sngist.sms.exception.StudentNotFoundException;
import com.sngist.sms.repository.StudentRepository;
import com.sngist.sms.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final ModelMapper modelMapper;
    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentResponseDto saveStudent(StudentRequestDto studentRequest) {

        List<Student> studentByNaAndAge = studentRepository.getStudentByNaAndAge(studentRequest.getName(), studentRequest.getAge());
        System.out.println(studentByNaAndAge);
        Student student =modelMapper.map(studentRequest, Student.class);
        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentResponseDto.class);

    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<Student> studentList = students.stream().filter(student -> student.getAge() > 18).collect(Collectors.toList());
        List<String> collect = studentList.stream().map(student -> student.getName()).collect(Collectors.toList());
        Type listType = new TypeToken<List<StudentResponseDto>>() {}.getType();

        return modelMapper.map(studentList,listType);

    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequest) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        // Update fields
        existingStudent.setName(studentRequest.getName());
        existingStudent.setEmail(studentRequest.getEmail());
        existingStudent.setAge(studentRequest.getAge());

        Student updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, StudentResponseDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

}
