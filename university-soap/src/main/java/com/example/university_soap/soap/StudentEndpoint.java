package com.example.university_soap.soap;

import com.example.university.student.AddStudentRequest;
import com.example.university.student.AddStudentResponse;
import com.example.university.student.GetStudentRequest;
import com.example.university.student.GetStudentResponse;
import com.example.university.student.Student;
import com.example.university_soap.model.StudentEntity;
import com.example.university_soap.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {

    private static final String NS =
            "http://example.com/university/student";

    @Autowired
    private StudentService studentService;


    // =========================
    // GET STUDENT
    // =========================

    @PayloadRoot(
            namespace = NS,
            localPart = "getStudentRequest"
    )
    @ResponsePayload
    public GetStudentResponse getStudent(
            @RequestPayload GetStudentRequest request) {

        StudentEntity entity =
                studentService
                        .getStudentById(request.getId())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Étudiant introuvable"
                                )
                        );

        Student student = new Student();

        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setEmail(entity.getEmail());

        GetStudentResponse response =
                new GetStudentResponse();

        response.setStudent(student);

        return response;
    }


    // =========================
    // ADD STUDENT
    // =========================

    @PayloadRoot(
            namespace = NS,
            localPart = "addStudentRequest"
    )
    @ResponsePayload
    public AddStudentResponse addStudent(
            @RequestPayload AddStudentRequest request) {

        StudentEntity entity = new StudentEntity();

        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());

        StudentEntity created =
                studentService.addStudent(entity);

        Student student = new Student();

        student.setId(created.getId());
        student.setFirstName(created.getFirstName());
        student.setLastName(created.getLastName());
        student.setEmail(created.getEmail());

        AddStudentResponse response =
                new AddStudentResponse();

        response.setStudent(student);

        return response;
    }
}