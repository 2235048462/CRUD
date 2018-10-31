package dao;

import bean.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {

    // JDBC驱动名及数据库URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    // 数据库用户名及密码
    static final String USERNAME = "root";
    static final String PASSWORD = "123456";

    public HeroDAO () {
        try {
            Class.forName ( JDBC_DRIVER ); // 注册JDBC驱动
        } catch (ClassNotFoundException e) {
            System.out.println ( "未发现相关类,以至无法驱动..." );
        }
    }

    // 打开数据库连接
    public Connection getConnection () throws SQLException {
        return DriverManager.getConnection ( DB_URL, USERNAME, PASSWORD );
    }

    public int getTotal () {
        int total = 0;

        // 建立连接及创建状态,实例化Statement对象
        try (Connection connection = getConnection ();
             Statement statement = connection.createStatement ()) {
            String sql = "select count(*) from hero";

            ResultSet resultSet = statement.executeQuery ( sql );
            while (resultSet.next ()) {
                total = resultSet.getInt ( 1 );
            }

            System.out.println ( "total:" + total );
        } catch (SQLException e) {
            System.out.println ( "数据库异常001..." );
        }
        return total;
    }

    // 增加英雄
    public void add ( Hero hero ) {
        String sql = "insert into `hero` (id,name,hp,damage) values(null,?,?,?)";
        try (Connection connection = getConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement ( sql );) {

            // 向数据库添加数据
            preparedStatement.setString ( 1, hero.name );
            preparedStatement.setFloat ( 2, hero.hp );
            preparedStatement.setInt ( 3, hero.damage );

            preparedStatement.execute (); // 执行操作

            ResultSet resultSet = preparedStatement.getGeneratedKeys ();
            if (resultSet.next ()) {
                int id = resultSet.getInt ( 1 );
                hero.id = id;
            }
        } catch (SQLException e) {
            System.out.println ( "数据库异常002..." );
            e.printStackTrace ();
        }
    }

    // 更新数据库
    public void update ( Hero hero ) {
        String sql = "update hero set name = ?, hp = ?, damage = ? where id = ?";
        try (Connection connection = getConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement ( sql )) {

            // 更新数据库信息
            preparedStatement.setString ( 1, hero.name );
            preparedStatement.setFloat ( 2, hero.hp );
            preparedStatement.setInt ( 3, hero.damage );
            preparedStatement.setInt ( 4, hero.id );

            preparedStatement.execute (); // 执行操作

        } catch (SQLException e) {
            System.out.println ( "数据库异常003..." );
            e.printStackTrace ();
        }
    }

    // 删除数据库信息
    public void delete ( int id ) {
        try (Connection connection = getConnection ();
             Statement statement = connection.createStatement ()) {
            String sql = "delete from hero where id = " + id;

            statement.execute ( sql ); // 执行操作
			
			statement.close();
			connection.close();

        } catch (SQLException e) {
            System.out.println ( "数据库异常004..." );
            e.printStackTrace ();
        }
    }

    // 选择英雄
    public Hero get ( int id ) {
        Hero hero = null;
        try (Connection connection = getConnection ();
             Statement statement = connection.createStatement ()) {
            String sql = "select * from hero where id = " + id;

            ResultSet resultSet = statement.executeQuery ( sql );
            if (resultSet.next ()) {
                hero = new Hero ();
                String name = resultSet.getString ( 2 );
                float hp = resultSet.getFloat ( "hp" );
                int damage = resultSet.getInt ( 4 );
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                hero.id = id;
            }
        } catch (SQLException e) {
            System.out.println ( "数据库异常005..." );
            e.printStackTrace ();
        }
        return hero;
    }

    public List <Hero> list () {
        return list ( 0, Short.MAX_VALUE );
    }

    // 对数据库信息降序排序
    public List <Hero> list ( int start, int count ) {
        List <Hero> heroes = new ArrayList ();

        String sql = "select * from hero order by id desc limit ?,?";
        try (Connection connection = getConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement ( sql );) {

            preparedStatement.setInt ( 1, start );
            preparedStatement.setInt ( 2, count );

            ResultSet resultSet = preparedStatement.executeQuery ();
            while (resultSet.next ()) {
                Hero hero = new Hero ();
                int id = resultSet.getInt ( 1 );
                String name = resultSet.getString ( 2 );
                float hp = resultSet.getFloat ( "hp" );
                int damage = resultSet.getInt ( 4 );
                hero.id = id;
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                heroes.add ( hero );
            }
        } catch (SQLException e) {
            System.out.println ( "数据库异常006..." );
            e.printStackTrace ();
        }
        return heroes;
    }
}
