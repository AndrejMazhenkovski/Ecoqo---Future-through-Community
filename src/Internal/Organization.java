package Internal;

public class Organization implements User{

    private String name;
    private int SocialPoints;

    public Organization(String name, int SocialPoints){
        this.name=name;
        this.SocialPoints=SocialPoints;
    }

    public String getName(){
        return name;
    }

    public void setSocialPoints(int sp){
        SocialPoints=sp;
    }
    public int getSocialPoints(){
       return SocialPoints;
    }
    public String toString(){
        return name;
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }
}
