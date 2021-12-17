package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.StudentRequestDto;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.api.dto.response.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setAge(studentRequestDto.getAge());
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Long id) {}

    @Override
    public void delete(Long id) {}

    @Override
    public StudentResponseDto findById(Long id) {
        return new StudentResponseDto(studentService.findById(id));
    }

    @Override
    public PageData<StudentResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Student> tableResponse = studentService.findAll(dataTableRequest);

        List<StudentResponseDto> students = tableResponse.getItems().stream().
                map(StudentResponseDto::new).
                peek(studentResponseDto -> studentResponseDto.setCourseCount((Integer) tableResponse.
                        getOtherParamMap().get(studentResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<StudentResponseDto> pageData = (PageData<StudentResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(students);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllByCourseId(Long courseId) {
        return studentService.findAllByCourseId(courseId);
    }
}
