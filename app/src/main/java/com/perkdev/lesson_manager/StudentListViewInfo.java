package com.perkdev.lesson_manager;

/**
 * Created by tylerperkins on 8/25/2015.
 */
public class StudentListViewInfo {
    int studentId;
    String fullName;
    String nextAppointmentDate;
    Double paymentBalance;


    public int getId() {
        return studentId;
    }
    public void setId(int id) {
        this.studentId = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public void setNextAppointmentDate(String nextAppointmentDate) {
        this.nextAppointmentDate = nextAppointmentDate;
    }

    public Double getPaymentBalance() {
        return paymentBalance;
    }

    public void setPaymentBalance(Double paymentBalance) {
        this.paymentBalance = paymentBalance;
    }
}
