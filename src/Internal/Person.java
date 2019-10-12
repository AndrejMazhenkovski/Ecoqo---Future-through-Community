package Internal;

import java.net.Socket;

public class Person implements User{

    private String name;
    private String age;
    private int SocialPoints;

    public Person(String name,String age,int SocialPoints){
        this.name=name;
        this.SocialPoints=SocialPoints;
        this.age=age;
    }
    public String getName(){
        return name;
    }

    public String getAge(){
        return age;
    }

    public int getSocialPoints(){
        return SocialPoints;
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString(){
        return name+" "+age;
    }
}
