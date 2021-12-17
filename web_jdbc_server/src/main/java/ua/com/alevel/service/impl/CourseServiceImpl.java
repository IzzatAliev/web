package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.service.CourseService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void create(Course entity) {}

    @Override
    public void update(Course entity) {}

    @Override
    public void delete(Long id) {}

    @Override
    public Course findById(Long id) {
        return courseDao.findById(id);
    }

    @Override
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        DataTableResponse<Course> dataTableResponse = courseDao.findAll(request);
        long count = courseDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findByStudentId(Long studentId) {
        return courseDao.findByStudentId(studentId);
    }
}
