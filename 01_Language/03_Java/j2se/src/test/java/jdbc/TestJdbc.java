package jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TestJdbc {
    @Test
    public void testConnection1() {
        Connection connection = null;
        try {
            // 1. 创建一个 Driver 实现类的对象
            Driver driver = new com.mysql.jdbc.Driver();

            // 2. 准备连接数据库的基本信息： url, user, password
            String url = "jdbc:mysql://127.0.0.1:3306/default";
            url = "jdbc:mysql://localhost:3306/default";
            url = "jdbc:mysql:///default";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "root");

            // 3. 调用 Driver 接口的 connect(url, info) 获取数据库连接
            connection = driver.connect(url, info);
            System.out.println("connection=" + connection); // com.mysql.jdbc.JDBC4Connection
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testConnection2() {
        InputStream is = getClass().getResourceAsStream("jdbc.properties");
        Properties jdbcProperties = new Properties();
        Connection connection = null;
        try {
            jdbcProperties.load(is);

            String driverClass = jdbcProperties.getProperty("driver");
            String jdbcUrl = jdbcProperties.getProperty("jdbcUrl");
            String user = jdbcProperties.getProperty("user");
            String password = jdbcProperties.getProperty("password");

            Class<?> clazz = Class.forName(driverClass);
            Driver driver = (Driver) clazz.newInstance();

            Properties info = new Properties();
            info.setProperty("user", user);
            info.setProperty("password", password);

            connection = driver.connect(jdbcUrl, info);
            System.out.println("connection=" + connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testConnection3() {
        InputStream is = getClass().getResourceAsStream("jdbc.properties");
        Properties jdbcProperties = new Properties();
        Connection connection = null;
        try {
            jdbcProperties.load(is);

            String driverClass = jdbcProperties.getProperty("driver");
            String jdbcUrl = jdbcProperties.getProperty("jdbcUrl");
            String user = jdbcProperties.getProperty("user");
            String password = jdbcProperties.getProperty("password");

            Class.forName(driverClass);
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("connection=" + connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testStatement() {
        Connection connection = null;
        Statement statement = null;
        String sql = "insert into customer (name, email, birth) values ('xyz', 'xyz@abc.com', '1990-12-12')";
//        sql = "delete from customer where id=1";
//        sql = "update customer set name='tom' where id=1";
        try {
            int rowCount = update(sql);
            System.out.println("rowCount=" + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(statement);
            JDBCTools.close(connection);
        }
    }

    public int update(String sql) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCTools.getConnection();
            statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } finally {
            JDBCTools.close(statement);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testResultSet() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = JDBCTools.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from customer limit 0,10");

            List<Object[]> objects = new ArrayList<>();
            while (rs.next()) {
                objects.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)});
//                objects.add(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("email")});
            }
            for (Object[] obj : objects) {
                System.out.println(Arrays.toString(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(rs);
            JDBCTools.close(statement);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testInsert1() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCTools.getConnection();
            ps = connection.prepareStatement("insert into customer (name, email, birth) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "測試");
            ps.setString(2, "123@456.com");
            ps.setDate(3, new Date(System.currentTimeMillis()));
            int rowCount = ps.executeUpdate();
            System.out.println("rowCount=" + rowCount);

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("lastInsertId=" + generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(ps);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testInsert2() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCTools.getConnection();
            ps = connection.prepareStatement("insert into customer (name, email, birth) values (?, ?, ?)", new String[]{"id"});
            ps.setString(1, "測試");
            ps.setString(2, "123@456.com");
            ps.setDate(3, new Date(System.currentTimeMillis()));
            int rowCount = ps.executeUpdate();
            System.out.println("rowCount=" + rowCount);

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("lastInsertId=" + generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(ps);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testResultSetMetaData() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select *,name as name1 from customer where id=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 3);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            Map<String, Object> map = new HashMap<>();
            if (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i + 1); // 字段名
                    String columnLabel = rsmd.getColumnLabel(i + 1); // 字段别名
                    map.put(columnLabel, rs.getObject(columnLabel));
                }
            }

            Class<Customer> clazz = Customer.class;
            if (!map.isEmpty()) {
                Customer customer = clazz.newInstance();
                for (Map.Entry<String, Object> e : map.entrySet()) {
                    try {
                        Field field = clazz.getDeclaredField(e.getKey());
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        field.set(customer, e.getValue());
                    } catch (NoSuchFieldException e1) {
                        e1.printStackTrace(); // java.lang.NoSuchFieldException: name1
                    }
                }
                System.out.println("customer=" + customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(ps);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testDatabaseMetaData() {
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getDatabaseMajorVersion()); // 5
            System.out.println(metaData.getDatabaseMinorVersion()); // 6
            System.out.println(metaData.getURL()); // jdbc:mysql://localhost:3306/default?rewriteBatchedStatements=true
            System.out.println(metaData.getUserName()); // root@localhost

            ResultSet catalogs = metaData.getCatalogs();
            ResultSetMetaData catalogsMetaData = catalogs.getMetaData();
            List<Map<String, Object>> catalogList = new ArrayList<>();
            while (catalogs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < catalogsMetaData.getColumnCount(); i++) {
                    String columnLabel = catalogsMetaData.getColumnLabel(i + 1);
                    Object columnValue = catalogs.getObject(columnLabel);

                    map.put(columnLabel, columnValue);
                }
                catalogList.add(map);
            }
            System.out.println(Arrays.toString(catalogList.toArray()));

            ResultSet tables = metaData.getTables("default", "", "", null);
            ResultSetMetaData tablesMetaData = tables.getMetaData();
            List<Map<String, Object>> tableList = new ArrayList<>();
            while (tables.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < tablesMetaData.getColumnCount(); i++) {
                    String columnLabel = tablesMetaData.getColumnLabel(i + 1);
                    Object columnValue = tables.getObject(columnLabel);

                    map.put(columnLabel, columnValue);
                }
                tableList.add(map);
            }
            System.out.println(Arrays.toString(tableList.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testTransaction() {
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            connection = JDBCTools.getConnection();
            connection.setAutoCommit(false);
            ps1 = connection.prepareStatement("update users set balance = balance - 100 where name=?");
            ps1.setString(1, "zhangsan");
            ps1.executeUpdate();
            int i = 10 / 0;
            ps2 = connection.prepareStatement("update users set balance = balance + 100 where name=?");
            ps2.setString(1, "lisi");
            ps2.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            JDBCTools.close(ps1);
            JDBCTools.close(ps2);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testIsolation() {
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            System.out.println(connection.getTransactionIsolation()); // 4 TRANSACTION_REPEATABLE_READ
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            System.out.println(connection.getTransactionIsolation()); // 2 TRANSACTION_READ_COMMITTED
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testBatchInsert1() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCTools.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            long begin = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                Date date = new Date((long) (Math.random() * System.currentTimeMillis()));
                String sql = "insert into customer (name, email, birth) values ('name_" + i + "', 'email_" + i + "@email.com', '" + date + "')";
                statement.executeUpdate(sql);
            }
            connection.commit();
            long end = System.currentTimeMillis();

            System.out.println("time=" + (end - begin) + " ms"); // 9381 ms
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(statement);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testBatchInsert2() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCTools.getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into customer (name, email, birth) values (?,?,?)";
            ps = connection.prepareStatement(sql);

            long begin = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                Date date = new Date((long) (Math.random() * System.currentTimeMillis()));
                ps.setString(1, "name_" + i);
                ps.setString(2, "email_" + i + "@email.com");
                ps.setDate(3, date);
                ps.executeUpdate();
            }
            connection.commit();
            long end = System.currentTimeMillis();

            System.out.println("time=" + (end - begin) + " ms"); // 9144 ms
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(ps);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testBatchInsert3() {
        /*
        Mysql批量插入效率慢的解决方法
        https://stackoverflow.com/questions/13504564/jdbc-batch-executing-extremely-slow
        =================================================================================
        Add ?rewriteBatchedStatements=true to the end of your JDBC url. It'll give you a serious performance improvement.
        Note that this is specific to MySql, won't have any effect with any other JDBC drivers.
         */
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCTools.getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into customer (name, email, birth) values (?,?,?)";
            ps = connection.prepareStatement(sql);

            long begin = System.currentTimeMillis();
            int len = 0;
            for (int i = 0; i < 100000; i++) {
                Date date = new Date((long) (Math.random() * System.currentTimeMillis()));
                ps.setString(1, "name_" + i);
                ps.setString(2, "email_" + i + "@email.com");
                ps.setDate(3, date);
                ps.addBatch();
                len++;
                if (len == 300) {
                    ps.executeBatch();
                    ps.clearBatch();
                    len = 0;
                }
            }
            if (len > 0) {
                ps.executeBatch();
                ps.clearBatch();
                len = 0;
            }
            connection.commit();
            long end = System.currentTimeMillis();

            System.out.println("time=" + (end - begin) + " ms"); // 2075 ms
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.close(ps);
            JDBCTools.close(connection);
        }
    }

    @Test
    public void testDruid() {
        DataSource ds = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        InputStream is = getClass().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
            connection = ds.getConnection();
            System.out.println("connection=" + connection);
            ps = connection.prepareStatement("select * from customer limit 10");
            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            List<String> columns = new ArrayList<>();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                columns.add(metaData.getColumnLabel(i + 1));
            }
            while (rs.next()) {
                for (int i = 0; i < columns.size(); i++) {
                    System.out.println(columns.get(i) + ": " + rs.getObject(i + 1));
                }
                System.out.println("------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ds != null) {
                ((DruidDataSource) ds).close();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
