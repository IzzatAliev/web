package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Student;

import java.util.Map;

public interface StudentDao extends BaseDao<Student> {

    Map<Long, String> findAllByCourseId(Long courseId);
}
