package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.CourseRequestDto;
import ua.com.alevel.api.dto.response.CourseResponseDto;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.facade.CourseFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseFacadeImpl implements CourseFacade {

    private final CourseService courseService;

    public CourseFacadeImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void create(CourseRequestDto courseRequestDto) {}

    @Override
    public void update(CourseRequestDto courseRequestDto, Long id) {}

    @Override
    public void delete(Long id) {}

    @Override
    public CourseResponseDto findById(Long id) {
        return new CourseResponseDto(courseService.findById(id));
    }

    @Override
    public PageData<CourseResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        String studentId = request.getParameter("studentId");
        if (StringUtils.isNotBlank(studentId)) {
            dataTableRequest.getQueryParam().put("studentId", studentId);
        }
        DataTableResponse<Course> tableResponse = courseService.findAll(dataTableRequest);
        List<CourseResponseDto> courses = tableResponse.getItems().stream().
                map(CourseResponseDto::new).
                collect(Collectors.toList());

        PageData<CourseResponseDto> pageData = (PageData<CourseResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(courses);
        return pageData;
    }

    @Override
    public Map<Long, String> findByStudentId(Long studentId) {
        return courseService.findByStudentId(studentId);
    }
}
