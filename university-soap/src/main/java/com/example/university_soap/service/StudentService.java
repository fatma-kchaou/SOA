package com.example.university_soap.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.university_soap.model.StudentEntity;

@Service
public class StudentService {

    private final Map<Long, StudentEntity> data = new HashMap<>();

    private final AtomicLong sequence = new AtomicLong(2);

    public StudentService() {

     
        data.put(
            1L,
            new StudentEntity(
                1L,
                "Amine",
                "Ben Salah",
                "amine@example.com"
            )
        );
    }

    // Rechercher un étudiant par son ID
    public Optional<StudentEntity> getStudentById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    // Ajouter un nouvel étudiant
    public StudentEntity addStudent(StudentEntity student) {

        // Générer automatiquement un nouvel ID
        Long id = sequence.getAndIncrement();

        // Affecter l'ID à l'étudiant
        student.setId(id);

        // Ajouter l'étudiant dans la Map
        data.put(id, student);

        // Retourner l'étudiant créé
        return student;
    }
}