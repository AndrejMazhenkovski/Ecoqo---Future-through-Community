package Internal;

public class Organization implements User{

    private String name;

    public Organization(String name){
        this.name=name;
    }


    public String getName(){
        return name;
    }
    public String toString(){
        return name;
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
