package com.abc.dao;

import com.abc.entity.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultDAO {
    private Connection conn;

    public ResultDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    // Create Result
    public boolean addResult(Result result) {
        boolean f = false;
        try {
            String sql = "insert into results(appointmentId, result) values(?,?)";
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, result.getAppointmentId());
            pstmt.setString(2, result.getResult());
            pstmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Find a single Result by appointmentId
    public Result findResultByAppointmentId(int appointmentId) {
        Result result = null;
        try {
            String sql = "SELECT * FROM results WHERE appointmentId = ? LIMIT 1"; // Ensure only one result is fetched
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = new Result();
                result.setId(rs.getInt("id"));
                result.setAppointmentId(rs.getInt("appointmentId"));
                result.setResult(rs.getString("result"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Assuming similar methods for update, delete, and get all results as needed
}
