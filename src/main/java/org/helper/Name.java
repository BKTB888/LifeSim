package org.helper;

public class Name {
    public String firstName;
    public String lastName;

    public Name(Boolean isMale){
        Name randomName = Globals.NameList.getRandomName(isMale);
        this.firstName = randomName.firstName;
        this.lastName = randomName.lastName;
    }
    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return firstName + ' ' + lastName;
    }
}
