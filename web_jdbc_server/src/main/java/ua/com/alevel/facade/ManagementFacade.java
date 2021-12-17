package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.ManagementRequestDto;
import ua.com.alevel.api.dto.response.ManagementResponseDto;

import java.util.Map;

public interface ManagementFacade extends BaseFacade<ManagementRequestDto, ManagementResponseDto> {

    Map<Long, String> findAllByCourseId(Long courseId);
}
