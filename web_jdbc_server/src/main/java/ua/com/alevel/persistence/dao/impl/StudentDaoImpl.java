package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentDaoImpl implements StudentDao {

    private final JpaConfig jpaConfig;

    private static final String CREATE_STUDENT_QUERY = "insert into students values(default, ?,?,?,?,?,?)";
    private static final String FIND_STUDENT_BY_ID_QUERY = "select * from students where id = ";
    private static final String FIND_STUDENT_BY_COURSE_ID_QUERY = "select id, first_name, last_name from students left join student_course sc on students.id = sc.student_id where course_id = ";

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Student entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_STUDENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setInt(6, entity.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Student findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_STUDENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return convertResultSetToStudent(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        List<Student> students = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, first_name, last_name, count(student_id) as courseCount " +
                "from students as student left join student_course as sc on student.id = sc.student_id " +
                "group by student.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                StudentResultSet studentResultSet = convertResultSetToSimpleStudent(resultSet);
                students.add(studentResultSet.getStudent());
                otherParamMap.put(studentResultSet.getStudent().getId(), studentResultSet.getCourseCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Student> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(students);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from students")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private StudentResultSet convertResultSetToSimpleStudent(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int courseCount = resultSet.getInt("courseCount");

        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        return new StudentResultSet(student, courseCount);
    }

    private Student convertResultSetToStudent(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer age = resultSet.getInt("age");

        Student student = new Student();
        student.setId(id);
        student.setCreated(created);
        student.setUpdated(updated);
        student.setVisible(visible);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);

        return student;
    }

    @Override
    public Map<Long, String> findAllByCourseId(Long courseId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_STUDENT_BY_COURSE_ID_QUERY + courseId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                map.put(id, firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static class StudentResultSet {

        private final Student student;
        private final int courseCount;

        private StudentResultSet(Student student, int courseCount) {
            this.student = student;
            this.courseCount = courseCount;
        }

        public Student getStudent() {
            return student;
        }

        public int getCourseCount() {
            return courseCount;
        }
    }
}
