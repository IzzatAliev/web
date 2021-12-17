package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.CourseRequestDto;
import ua.com.alevel.api.dto.response.CourseResponseDto;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.facade.CourseFacade;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseFacade courseFacade;

    public CourseController(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody CourseRequestDto dto) {
        courseFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody CourseRequestDto dto, @PathVariable Long id) {
        courseFacade.update(dto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        courseFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageData<CourseResponseDto>> findAll(WebRequest webRequest) {
        return ResponseEntity.ok(courseFacade.findAll(webRequest));
    }
}
