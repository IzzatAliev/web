package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.StudentRequestDto;
import ua.com.alevel.api.dto.response.StudentResponseDto;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.facade.StudentFacade;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody StudentRequestDto dto) {
        studentFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody StudentRequestDto dto, @PathVariable Long id) {
        studentFacade.update(dto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageData<StudentResponseDto>> findAll(WebRequest webRequest) {
        return ResponseEntity.ok(studentFacade.findAll(webRequest));
    }
}
