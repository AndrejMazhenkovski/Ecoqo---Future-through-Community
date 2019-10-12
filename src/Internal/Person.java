package Internal;

public class Person {

    private String name;
    private String age;

    public Person(String name,String age){
        this.name=name;
        this.age=age;
    }
    public String getName(){
        return name;
    }

    public String getAge(){
        return age;
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
