package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseDao extends BaseDao<Course> {

    Map<Long, String> findByStudentId(Long studentId);
    Map<Long, String> findByManagementId(Long managementId);
    List<Course> findAllByStudentId(Long studentId);
    List<Course> findAllByManagementId(Long managementId);
}
