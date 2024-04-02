package com.abc.dao;

import com.abc.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    private Connection conn;

    public TestDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    //create Test
    public boolean addTest(Test test) {

        boolean f = false;

        try {
            String sql = "insert into tests(testName, description, cost) values(?,?,?)";

            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, test.getTestName());
            pstmt.setString(2, test.getDescription());
            pstmt.setString(3, test.getCost());

            pstmt.executeUpdate();

            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    // Update Test by ID
    public boolean updateTest(Test test) {
        boolean f = false;

        try {
            String sql = "update tests set testName=?, description=?, cost=? where id=?";

            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, test.getTestName());
            pstmt.setString(2, test.getDescription());
            pstmt.setString(3, test.getCost());
            // Setting the ID for the where clause
            pstmt.setInt(4, test.getId());

            // Execute the update
            int updatedRows = pstmt.executeUpdate();

            // If at least one row is updated, then set f to true
            if (updatedRows > 0) {
                f = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }


    // Delete Test by ID
    public boolean deleteTestById(int testId) {
        boolean f = false;
        try {
            String sql = "delete from tests where id = ?";

            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, testId);

            int deletedRows = pstmt.executeUpdate();

            if (deletedRows > 0) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    // Get All Tests
    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tests";
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getInt("id")); // Assuming your Test class has this field and setter
                test.setTestName(rs.getString("testName"));
                test.setDescription(rs.getString("description"));
                test.setCost(rs.getString("cost"));

                tests.add(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tests;
    }

    // Get Test by ID
    public Test getTestById(int testId) {
        Test test = null;
        try {
            String sql = "SELECT * FROM tests WHERE id = ?";
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, testId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                test = new Test();
                test.setId(rs.getInt("id")); // Make sure your Test class has this field and setter
                test.setTestName(rs.getString("testName"));
                test.setDescription(rs.getString("description"));
                test.setCost(rs.getString("cost"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}
