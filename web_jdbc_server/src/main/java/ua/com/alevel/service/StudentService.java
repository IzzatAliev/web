package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Student;

import java.util.Map;

public interface StudentService extends BaseService<Student> {

    Map<Long, String> findAllByCourseId(Long courseId);
}
