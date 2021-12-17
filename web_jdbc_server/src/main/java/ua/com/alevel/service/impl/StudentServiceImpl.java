package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student entity) {
        studentDao.create(entity);
    }

    @Override
    public void update(Student entity) {}

    @Override
    public void delete(Long id) {}

    @Override
    public Student findById(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        DataTableResponse<Student> dataTableResponse = studentDao.findAll(request);
        long count = studentDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllByCourseId(Long courseId) {
        return studentDao.findAllByCourseId(courseId);
    }
}
