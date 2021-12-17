package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.CourseRequestDto;
import ua.com.alevel.api.dto.response.CourseResponseDto;

import java.util.Map;

public interface CourseFacade extends BaseFacade<CourseRequestDto, CourseResponseDto> {

    Map<Long, String> findByStudentId(Long studentId);
}
