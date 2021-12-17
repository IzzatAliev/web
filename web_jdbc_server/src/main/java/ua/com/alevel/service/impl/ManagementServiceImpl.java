package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.ManagementDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Management;
import ua.com.alevel.service.ManagementService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;

@Service
public class ManagementServiceImpl implements ManagementService {

    private final ManagementDao managementDao;

    public ManagementServiceImpl(ManagementDao managementDao) {
        this.managementDao = managementDao;
    }

    @Override
    public void create(Management entity) {
        managementDao.create(entity);
    }

    @Override
    public void update(Management entity) {}

    @Override
    public void delete(Long id) {}

    @Override
    public Management findById(Long id) {
        return managementDao.findById(id);
    }

    @Override
    public DataTableResponse<Management> findAll(DataTableRequest request) {
        DataTableResponse<Management> dataTableResponse = managementDao.findAll(request);
        long count = managementDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllByCourseId(Long courseId) {
        return managementDao.findAllByCourseId(courseId);
    }
}
