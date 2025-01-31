package com.studentTesting.StudentTestingPlatform.controllers;

import com.studentTesting.StudentTestingPlatform.api.ApiResponse;
import com.studentTesting.StudentTestingPlatform.dto.teacherDTOs.TeacherLoginRequestDto;
import com.studentTesting.StudentTestingPlatform.dto.teacherDTOs.TeacherRegisterRequestDto;
import com.studentTesting.StudentTestingPlatform.dto.teacherDTOs.TeacherResponseDto;
import com.studentTesting.StudentTestingPlatform.dto.testDetails.TestDetailsDto;
import com.studentTesting.StudentTestingPlatform.exceptions.TeacherException;
import com.studentTesting.StudentTestingPlatform.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //teacher registration
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/teachers/register")
    public ResponseEntity<?> registerTeacher(
            @RequestBody TeacherRegisterRequestDto teacherRegisterRequestDto
            ){
        try{
            TeacherResponseDto teacherResponseDto = this.teacherService.registerTeacher(teacherRegisterRequestDto);
            return ResponseEntity.ok(new ApiResponse("SUCCESS","Teacher registered successfully", teacherResponseDto));
        }catch(TeacherException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("ERROR",e.getMessage(),null));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("ERROR","An unexpected error occured",null));
        }
    }

    // Teacher validation
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/teachers/login")
    public ResponseEntity<?> isValidUser(
            @RequestBody TeacherLoginRequestDto loginRequestDto
            ){
        try{
            TeacherResponseDto responseDto =  teacherService.isValidUser(loginRequestDto.email(), loginRequestDto.password());
            return ResponseEntity.ok(new ApiResponse("SUCCESS",null, responseDto));
        }
        catch(TeacherException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("ERROR",e.getMessage(), null));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("ERROR", e.getMessage(),null));
        }
    }


    // Get All tests by teacher id, accessed by teacher
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/teachers/{teacher_id}/tests")
    public List<TestDetailsDto> getTestsByTeacherId(
            @PathVariable("teacher_id") Integer teacherId
    ){
        return this.teacherService.getTestsByTeacherId(teacherId);
    }

}
