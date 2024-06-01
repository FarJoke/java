package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/daycare";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String createGroupsTable = "CREATE TABLE IF NOT EXISTS groups ("
                    + "number INT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL)";
            String createChildrenTable = "CREATE TABLE IF NOT EXISTS children ("
                    + "number INT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "gender VARCHAR(10) NOT NULL, "
                    + "age INT NOT NULL, "
                    + "groupNumber INT, "
                    + "FOREIGN KEY (groupNumber) REFERENCES groups(number))";
            stmt.executeUpdate(createGroupsTable);
            stmt.executeUpdate(createChildrenTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveGroup(Group group) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO groups (number, name) VALUES (?, ?)")) {
            stmt.setInt(1, group.getNumber());
            stmt.setString(2, group.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveChild(Child child) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO children (number, name, gender, age, groupNumber) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, child.getNumber());
            stmt.setString(2, child.getName());
            stmt.setString(3, child.getGender());
            stmt.setInt(4, child.getAge());
            stmt.setInt(5, child.getGroupNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM groups")) {
            while (rs.next()) {
                Group group = new Group(rs.getInt("number"), rs.getString("name"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public static List<Child> getAllChildren() {
        List<Child> children = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM children")) {
            while (rs.next()) {
                Child child = new Child(rs.getInt("number"), rs.getString("name"), rs.getString("gender"), rs.getInt("age"), rs.getInt("groupNumber"));
                children.add(child);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return children;
    }

    public static void updateGroup(Group group) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE groups SET name = ? WHERE number = ?")) {
            stmt.setString(1, group.getName());
            stmt.setInt(2, group.getNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateChild(Child child) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE children SET name = ?, gender = ?, age = ?, groupNumber = ? WHERE number = ?")) {
            stmt.setString(1, child.getName());
            stmt.setString(2, child.getGender());
            stmt.setInt(3, child.getAge());
            stmt.setInt(4, child.getGroupNumber());
            stmt.setInt(5, child.getNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGroup(int groupNumber) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM groups WHERE number = ?")) {
            stmt.setInt(1, groupNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteChild(int childNumber) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM children WHERE number = ?")) {
            stmt.setInt(1, childNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Child> getChildrenInGroup(int groupNumber) {
        List<Child> children = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM children WHERE groupNumber = ?")) {
            stmt.setInt(1, groupNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Child child = new Child(rs.getInt("number"), rs.getString("name"), rs.getString("gender"), rs.getInt("age"), rs.getInt("groupNumber"));
                children.add(child);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return children;
    }
}
