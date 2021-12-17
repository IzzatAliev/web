package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Management;

import java.util.Map;

public interface ManagementDao extends BaseDao<Management> {

    Map<Long, String> findAllByCourseId(Long courseId);
}
