package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.entity.Appointment;

public class AppointmentDAO {

	private Connection conn;

	public AppointmentDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	//for create appointment
	// for create appointment
// This method now returns an integer representing the generated appointment ID or -1 if the operation fails.
	public int addAppointment(Appointment appointment) {

		int generatedId = -1; // Initialize to -1 to indicate failure by default

		try {

			String sql = "insert into appointment(userId, fullName, gender, doctorName, appointmentDate, email, phone, testId, technicianId, address, status) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, appointment.getUserId());
			pstmt.setString(2, appointment.getFullName());
			pstmt.setString(3, appointment.getGender());
			pstmt.setString(4, appointment.getDoctorName());
			pstmt.setString(5, appointment.getAppointmentDate());
			pstmt.setString(6, appointment.getEmail());
			pstmt.setString(7, appointment.getPhone());
			pstmt.setInt(8, appointment.getTestId());
			pstmt.setInt(9, appointment.getTechnicianId());
			pstmt.setString(10, appointment.getAddress());
			pstmt.setString(11, appointment.getStatus());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {
				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedId = generatedKeys.getInt(1);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return generatedId; // Returns the generated appointment ID or -1 if the operation failed.
	}


	// get list of appointment for logged in specific user 
	//show appointment list for specific user panel
	public List<Appointment> getAllAppointmentByLoginUser(int userId) {
		List<Appointment> appList = new ArrayList<Appointment>();

		Appointment appointment = null;

		try {

			String sql = "select * from appointment where userId=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, userId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setDoctorName(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setTestId(resultSet.getInt(9));
				appointment.setTechnicianId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;

	}

	// get appointment list of patient for specific technician
	//show list of appointment in specific technician panel
	public List<Appointment> getAllAppointmentByLoginTechnician(int technicianId) {
		List<Appointment> appList = new ArrayList<Appointment>();

		Appointment appointment = null;

		try {

			String sql = "select * from appointment where technicianId=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, technicianId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setDoctorName(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setTestId(resultSet.getInt(9));
				appointment.setTechnicianId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;

	}

	// for technician comment need specific appointment id
	public Appointment getAppointmentById(int id) {

		Appointment appointment = null;

		try {

			String sql = "select * from appointment where id=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setDoctorName(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setTestId(resultSet.getInt(9));
				appointment.setTechnicianId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appointment;

	}

	// for update comment status
	public boolean updateDrAppointmentCommentStatus(int apptId, int docId, String comment) {

		boolean f = false;

		try {

			String sql = "update appointment set status=? where id=? and technicianId=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, comment);
			pstmt.setInt(2, apptId);
			pstmt.setInt(3, docId);

			pstmt.executeUpdate();

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// get all appointment in admin panel
	public List<Appointment> getAllAppointment() {
		List<Appointment> appList = new ArrayList<Appointment>();
		Appointment appointment = null;

		try {

			String sql = "select * from appointment order by id desc";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				appointment = new Appointment();

				appointment.setId(resultSet.getInt(1));// appoint id
				appointment.setUserId(resultSet.getInt(2));// userId
				appointment.setFullName(resultSet.getString(3));
				appointment.setGender(resultSet.getString(4));
				appointment.setDoctorName(resultSet.getString(5));
				appointment.setAppointmentDate(resultSet.getString(6));
				appointment.setEmail(resultSet.getString(7));
				appointment.setPhone(resultSet.getString(8));
				appointment.setTestId(resultSet.getInt(9));
				appointment.setTechnicianId(resultSet.getInt(10));
				appointment.setAddress(resultSet.getString(11));
				appointment.setStatus(resultSet.getString(12));
				appList.add(appointment);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return appList;
	}

}
