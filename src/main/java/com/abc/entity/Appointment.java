package com.abc.entity;

public class Appointment {

	private int id;
	private int userId;
	private String fullName;
	private String gender;
	private String doctorName;
	private String appointmentDate;
	private String email;
	private String phone;
	private int testId;
	private int technicianId;
	private String address;
	private String status;
	
	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Appointment(int id, int userId, String fullName, String gender, String doctorName, String appointmentDate,
			String email, String phone,int testId, int techId, String address, String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.email = email;
		this.phone = phone;
		this.testId = testId;
		this.technicianId = techId;
		this.address = address;
		this.status = status;
	}


	public Appointment(int userId, String fullName, String gender, String doctorName, String appointmentDate, String email,
			String phone, int testId, int techId, String address, String status) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.email = email;
		this.phone = phone;
		this.testId = testId;
		this.technicianId = techId;
		this.address = address;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public int getTechnicianId() {
		return technicianId;
	}


	public void setTechnicianId(int techId) {
		this.technicianId = techId;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
