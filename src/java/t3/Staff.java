/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LOQ
 */
public class Staff extends Person{
    private String school;
    private double pay;

    public Staff(String school, double pay, String name, String address) {
        super(name, address);
        this.school = school;
        this.pay = pay;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

   public String toString(){
       return super.toString()+"\nSchool : "+this.school+"\nPay : "+String.fomat("%.0f",this.pay)+" $\n-----------\n";
   }
    
}
