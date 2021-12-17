package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.ManagementRequestDto;
import ua.com.alevel.api.dto.response.ManagementResponseDto;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.facade.ManagementFacade;

@RestController
@RequestMapping("/api/managements")
public class ManagementController {

    private final ManagementFacade managementFacade;

    public ManagementController(ManagementFacade managementFacade) {
        this.managementFacade = managementFacade;
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody ManagementRequestDto dto) {
        managementFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody ManagementRequestDto dto, @PathVariable Long id) {
        managementFacade.update(dto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        managementFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(managementFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageData<ManagementResponseDto>> findAll(WebRequest webRequest) {
        return ResponseEntity.ok(managementFacade.findAll(webRequest));
    }
}
