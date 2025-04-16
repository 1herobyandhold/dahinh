/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author LOQ
 */
public class NewClass{
    public class Student {
    private String name;
    private String id;
    private String group;
    private String email;

    // Constructor không tham số
    public Student() {
        this.name = "Student";
        this.id = "000";
        this.group = "K62CB";
        this.email = "uet@vnu.edu.vn";
    }

    // Constructor có tham số name, id, email
    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.group = "K62CB";
    }

    // Constructor sao chép
    public Student(Student s) {
        this.name = s.name;
        this.id = s.id;
        this.group = s.group;
        this.email = s.email;
    }

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getId() {
        return id;
    }

    public void setId(String i) {
        this.id = i;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String g) {
        this.group = g;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    // Trả về thông tin sinh viên
    public String getInfo() {
        return name + " - " + id + " - " + group + " - " + email;
    }

    }
}
