package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.abc.entity.Technician;

public class TechDAO {

	private Connection conn;

	public TechDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean registerTech(Technician technician) {

		boolean f = false;

		try {

			String sql = "insert into technicians(fullName,dateOfBirth,qualification,email,phone,password) values(?,?,?,?,?,?)";

			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, technician.getFullName());
			pstmt.setString(2, technician.getDateOfBirth());
			pstmt.setString(3, technician.getQualification());
			pstmt.setString(4, technician.getEmail());
			pstmt.setString(5, technician.getPhone());
			pstmt.setString(6, technician.getPassword());

			pstmt.executeUpdate();
			// if query inserted or all ok than
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// getAlltechnicianss list
	public List<Technician> getAllTech() {

		Technician technician = null;
		List<Technician> docList = new ArrayList<Technician>();

		try {

			String sql = "select * from technicians order by id desc";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				technician = new Technician();

				technician.setId(resultSet.getInt("id"));
				technician.setFullName(resultSet.getString("fullName"));
				technician.setDateOfBirth(resultSet.getString("dateOfBirth"));
				technician.setQualification(resultSet.getString("qualification"));
				technician.setEmail(resultSet.getString("email"));
				technician.setPhone(resultSet.getString("phone"));
				technician.setPassword(resultSet.getString("password"));
				docList.add(technician);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return docList;
	}

	// get technician by id
	public Technician getTechById(int id) {

		Technician technician = null;

		try {

			String sql = "select * from technicians where id=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				technician = new Technician();

				technician.setId(resultSet.getInt("id"));
				technician.setFullName(resultSet.getString("fullName"));
				technician.setDateOfBirth(resultSet.getString("dateOfBirth"));
				technician.setQualification(resultSet.getString("qualification"));
				technician.setEmail(resultSet.getString("email"));
				technician.setPhone(resultSet.getString("phone"));
				technician.setPassword(resultSet.getString("password"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return technician;
	}

	// update technicianss by id
	public boolean updateTech(Technician technician) {

		boolean f = false;

		try {

			String sql = "update technicians set fullName=?,dateOfBirth=?,qualification=?,email=?,phone=?,password=? where id=?";

			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, technician.getFullName());
			pstmt.setString(2, technician.getDateOfBirth());
			pstmt.setString(3, technician.getQualification());
			pstmt.setString(4, technician.getEmail());
			pstmt.setString(5, technician.getPhone());
			pstmt.setString(6, technician.getPassword());
			// need to set id also for update
			pstmt.setInt(7, technician.getId());

			pstmt.executeUpdate();
			// if query updated or all ok than
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// delete technicianss by id
	public boolean deleteTechById(int id) {

		boolean f = false;

		try {

			String sql = "delete from technicians where id=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// technician login
	public Technician loginTech(String email, String password) {

		Technician technician = null;

		try {

			String sql = "select * from technicians where email=? and password=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				technician = new Technician();

				/*
				 * technician.setId(resultSet.getInt("id"));
				 * technician.setFullName(resultSet.getString("fullName"));
				 * technician.setDateOfBirth(resultSet.getString("dateOfBirth"));
				 * technician.setQualification(resultSet.getString("qualification"));
				 * technician.setSpecialist(resultSet.getString("specialist"));
				 * technician.setEmail(resultSet.getString("email"));
				 * technician.setPhone(resultSet.getString("phone"));
				 * technician.setPassword(resultSet.getString("password"));
				 */
				// we can write above commented code or
				// or
				// the below way [here 1 2 3..serially are the column index number of technician
				// table]

				technician.setId(resultSet.getInt(1));
				technician.setFullName(resultSet.getString(2));
				technician.setDateOfBirth(resultSet.getString(3));
				technician.setQualification(resultSet.getString(4));
				technician.setEmail(resultSet.getString(5));
				technician.setPhone(resultSet.getString(6));
				technician.setPassword(resultSet.getString(7));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return technician;

	}

	// show total number of dynamic value in admin panel

	// create all count method here to reduce code line...
	// Count total Technician Number
	public int countTotalTech() {

		int i = 0;

		try {

			String sql = "select * from technicians";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	// Count total Appointment Number
	public int countTotalAppointment() {

		int i = 0;

		try {

			String sql = "select * from appointment";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	// Count total number of Appointment for a specific technician
	public int countTotalAppointmentByTechId(int techniciansId) {

		int count = 0;

		try {
			// Assuming the column that stores the technician's ID in the appointment table is named `technicianId`
			String sql = "SELECT COUNT(*) FROM appointment WHERE technicianId = ?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, techniciansId);

			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1); // Get the count from the first column of the result set
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	// Count total User Number
	public int countTotalUser() {

		int i = 0;

		try {

			String sql = "select * from user_details";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	// check old password
	public boolean checkOldPassword(int techniciansId, String oldPassword) {

		boolean f = false;

		try {

			String sql = "select * from technicians where id=? and password=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, techniciansId);
			pstmt.setString(2, oldPassword);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// change password
	public boolean changePassword(int techniciansId, String newPassword) {

		boolean f = false;

		try {

			String sql = "update technicians set password=? where id=?";
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, techniciansId);

			pstmt.executeUpdate();

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// edit technician profile in technician panel edit profile
	public boolean editTechProfile(Technician technician) {

		boolean f = false;

		try {

			//String sql = "update technician set fullName=?,dateOfBirth=?,qualification=?,specialist=?,email=?,phone=?,password=? where id=?";
			String sql = "update technicians set fullName=?,dateOfBirth=?,qualification=?,email=?,phone=? where id=?";

			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, technician.getFullName());
			pstmt.setString(2, technician.getDateOfBirth());
			pstmt.setString(3, technician.getQualification());
			pstmt.setString(4, technician.getEmail());
			pstmt.setString(5, technician.getPhone());
			//pstmt.setString(7, technician.getPassword());
			// need to set id also for update
			pstmt.setInt(6, technician.getId());

			pstmt.executeUpdate();
			// if query updated or all okay than
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

}
