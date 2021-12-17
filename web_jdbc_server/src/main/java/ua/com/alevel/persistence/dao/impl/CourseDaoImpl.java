package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.type.CourseType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseDaoImpl implements CourseDao {

    private final JpaConfig jpaConfig;

    private static final String FIND_ALL_COURSES_QUERY = "select * from courses";
    private static final String FIND_COURSE_BY_ID_QUERY = "select * from courses where id = ";
    private static final String FIND_ALL_SIMPLE_COURSES_BY_STUDENT_ID_QUERY = "select id, course_name from courses left join student_course sc on courses.id = sc.course_id where sc.student_id = ";
    private static final String FIND_ALL_COURSES_BY_STUDENT_ID_QUERY = "select * from courses left join student_course sc on courses.id = sc.course_id where sc.student_id = ";
    private static final String FIND_ALL_SIMPLE_COURSES_BY_MANAGEMENT_ID_QUERY = "select id, course_name from courses left join management_course mc on courses.id = mc.course_id where mc.management_id = ";
    private static final String FIND_ALL_COURSES_BY_MANAGEMENT_ID_QUERY = "select * from courses left join management_course mc on courses.id = mc.course_id where mc.management_id = ";

    public CourseDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Course entity) {

    }

    @Override
    public void update(Course entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Course findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_COURSE_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initCourseByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        List<Course> courses = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_COURSES_QUERY)) {
            while (resultSet.next()) {
                courses.add(initCourseByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Course> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(courses);
        return dataTableResponse;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Map<Long, String> findByStudentId(Long studentId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SIMPLE_COURSES_BY_STUDENT_ID_QUERY + studentId)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String courseName = resultSet.getString("course_name");
                map.put(id, courseName);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }

    @Override
    public Map<Long, String> findByManagementId(Long managementId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SIMPLE_COURSES_BY_MANAGEMENT_ID_QUERY + managementId)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String courseName = resultSet.getString("course_name");
                map.put(id, courseName);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }

    @Override
    public List<Course> findAllByStudentId(Long studentId) {
        List<Course> courses = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_COURSES_BY_STUDENT_ID_QUERY + studentId)) {
            while (resultSet.next()) {
                courses.add(initCourseByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return courses;
    }

    @Override
    public List<Course> findAllByManagementId(Long managementId) {
        List<Course> courses = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_COURSES_BY_MANAGEMENT_ID_QUERY + managementId)) {
            while (resultSet.next()) {
                courses.add(initCourseByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return courses;
    }

    private Course initCourseByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String courseName = resultSet.getString("course_name");
        Integer credit = resultSet.getInt("credit");
        String courseType = resultSet.getString("course_type");
        String description = resultSet.getString("description");

        Course course = new Course();
        course.setId(id);
        course.setCreated(created);
        course.setUpdated(updated);
        course.setVisible(visible);
        course.setCourseName(courseName);
        course.setCredit(credit);
        course.setCourseType(CourseType.valueOf(courseType));
        course.setDescription(description);

        return course;
    }

    private static class CourseResultSet {

        private final Course course;
        private final int managementCount;
        private final int studentCount;

        public CourseResultSet(Course course, int managementCount, int studentCount) {
            this.course = course;
            this.managementCount = managementCount;
            this.studentCount = studentCount;
        }

        public Course getCourse() {
            return course;
        }

        public int getManagementCount() {
            return managementCount;
        }

        public int getStudentCount() {
            return studentCount;
        }
    }
}
