package com.sngist.sms.service;

import com.sngist.sms.dto.requestDto.StudentRequestDto;
import com.sngist.sms.dto.responseDto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto saveStudent(StudentRequestDto studentRequest);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto getStudentById(Long id);
    StudentResponseDto updateStudent(Long id, StudentRequestDto studentRequestDto);
    void deleteStudent(Long id);

}
