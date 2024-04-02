package com.abc.entity;

public class Result {
    private int id;
    private int appointmentId;
    private String result;

    public Result() {
        super();
    }

    public Result(int id, int appointmentId, String result) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.result = result;
    }

    public Result(int appointmentId, String result) {
        this.appointmentId = appointmentId;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
