package com.fangcansen.www.po;

import java.sql.Time;

/**
 * @author it-fang
 */
public class Teacher {
    private int id;
    private String name;
    private String college;
    private String major;
    private String clas;
    private Time freetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public Time getFreetime() {
        return freetime;
    }



    public void setFreetime(Time freetime) {
        this.freetime = freetime;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", clas='" + clas + '\'' +
                ", freetime=" + freetime +
                '}';
    }
}
