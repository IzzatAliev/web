package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Management;

import java.util.Map;

public interface ManagementService extends BaseService<Management> {

    Map<Long, String> findAllByCourseId(Long courseId);
}
