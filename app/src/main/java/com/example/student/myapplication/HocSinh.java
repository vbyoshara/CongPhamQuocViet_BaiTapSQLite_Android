package com.example.student.myapplication;

public class HocSinh {
    @Override
    public String toString() {
        return "HocSinh{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

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

    public HocSinh(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public HocSinh(){}
    public int id;
    public String name;
}
