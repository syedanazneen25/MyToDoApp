package com.nazneen.MyToDo.toDo;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class toDo {


    private int id;
    private String userName;
    private String taskName;

    @Size(min = 10 , message = "Enter at least 10 characters.")
    private String taskDescription;

    private LocalDate dueDate;
    private boolean done;

    public toDo(){

    }


    public toDo(int id, String userName, String taskName, String taskDescription, LocalDate dueDate, boolean done) {
        super();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.userName = userName;
        this.id = id;
        this.done = done;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "toDo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", dueDate=" + dueDate +
                ", done=" + done +
                '}';
    }



}
