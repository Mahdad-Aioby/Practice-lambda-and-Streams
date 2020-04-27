package com.team3d;

public class Person {
    String name;
    String familly;

    public Person(String name,String familly){
        this.name=name;
        this.familly=familly;


    }
    public boolean getName(String name) {
        if(this.name.equals("ali")) return true;
        else return false;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilly() {
        return familly;
    }

    public void setFamilly(String familly) {
        this.familly = familly;
    }
}
