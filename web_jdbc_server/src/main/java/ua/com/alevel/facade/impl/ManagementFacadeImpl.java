package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.api.dto.request.ManagementRequestDto;
import ua.com.alevel.api.dto.response.ManagementResponseDto;
import ua.com.alevel.api.dto.response.PageData;
import ua.com.alevel.facade.ManagementFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Management;
import ua.com.alevel.service.ManagementService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ManagementFacadeImpl implements ManagementFacade {

    private final ManagementService managementService;

    public ManagementFacadeImpl(ManagementService managementService) {
        this.managementService = managementService;
    }

    @Override
    public void create(ManagementRequestDto managementRequestDto) {}

    @Override
    public void update(ManagementRequestDto managementRequestDto, Long id) {}

    @Override
    public void delete(Long id) {}

    @Override
    public ManagementResponseDto findById(Long id) {
        return new ManagementResponseDto(managementService.findById(id));
    }

    @Override
    public PageData<ManagementResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Management> tableResponse = managementService.findAll(dataTableRequest);
        List<ManagementResponseDto> list = tableResponse.getItems()
                .stream()
                .map(ManagementResponseDto::new).
                peek(studentResponseDto -> studentResponseDto.setCourseCount((Integer) tableResponse.
                        getOtherParamMap().get(studentResponseDto.getId())))
                .collect(Collectors.toList());
        PageData<ManagementResponseDto> pageData = (PageData<ManagementResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllByCourseId(Long courseId) {
        return managementService.findAllByCourseId(courseId);
    }
}
