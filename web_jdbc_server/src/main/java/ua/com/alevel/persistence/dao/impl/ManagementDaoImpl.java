package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.ManagementDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Management;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagementDaoImpl implements ManagementDao {

    private final JpaConfig jpaConfig;

    private static final String CREATE_MANAGEMENT_QUERY = "insert into managements values(default, ?,?,?,?,?)";
    private static final String FIND_ALL_BY_COURSE_ID = "select id, management_name from managements left join management_course mc on managements.id = mc.management_id where mc.course_id = ";

    public ManagementDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Management entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_MANAGEMENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getManagementName());
            preparedStatement.setInt(5, entity.getStaffCount());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Management entity) {}

    @Override
    public void delete(Long id) {}

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Management findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Management> findAll(DataTableRequest request) {
        List<Management> list = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select m.id, m.management_name, m.staff_count, count(management_id) as course_count from managements as m left join management_course as mc on m.id = mc.management_id group by m.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                ManagementResultSet managementResultSet = convertResultSetToSimpleManagement(resultSet);
                list.add(managementResultSet.getManagement());
                otherParamMap.put(managementResultSet.getManagement().getId(), managementResultSet.getCourseCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Management> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(list);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    private ManagementResultSet convertResultSetToSimpleManagement(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String managementName = resultSet.getString("management_name");
        int staffCount = resultSet.getInt("staff_count");
        int courseCount = resultSet.getInt("course_count");

        Management management = new Management();
        management.setId(id);
        management.setManagementName(managementName);
        management.setStaffCount(staffCount);

        return new ManagementResultSet(management, courseCount);
    }

    private static class ManagementResultSet {

        private final Management management;
        private final int courseCount;

        private ManagementResultSet(Management management, int courseCount) {
            this.management = management;
            this.courseCount = courseCount;
        }

        public Management getManagement() {
            return management;
        }

        public int getCourseCount() {
            return courseCount;
        }
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from managements")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<Long, String> findAllByCourseId(Long courseId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_BY_COURSE_ID + courseId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String managementName = resultSet.getString("management_name");
                map.put(id, managementName);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }
}
