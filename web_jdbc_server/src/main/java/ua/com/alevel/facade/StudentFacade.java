package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.StudentRequestDto;
import ua.com.alevel.api.dto.response.StudentResponseDto;

import java.util.Map;

public interface StudentFacade extends BaseFacade<StudentRequestDto, StudentResponseDto> {

    Map<Long, String> findAllByCourseId(Long courseId);
}
