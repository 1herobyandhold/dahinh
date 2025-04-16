/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author LOQ
 */
public class Student extends Person{
    private String program;
    private int year;
    private double fee;

    public Student(String program, int year, double fee, String name, String address) {
        super(name, address);
        this.program = program;
        this.year = year;
        this.fee = fee;
    }
    public String toString(){
        return super.toString()+"Program : "+this.program+"\nYear"+this.year+"\nFee : "+String.fomat("%.0f",this.fee)+" $\n------------\n"
    }
}

