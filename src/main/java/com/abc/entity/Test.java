package com.abc.entity;

public class Test {

    private int id;
    private String testName;
    private String description;
    private String cost;

    public Test() {
        super();
    }

    public Test(int id, String testName, String description, String cost) {
        super();
        this.id = id;
        this.testName = testName;
        this.description = description;
        this.cost = cost;
    }

    public Test(String testName, String description, String cost) {
        this.testName = testName;
        this.description = description;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


}
