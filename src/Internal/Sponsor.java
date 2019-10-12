package Internal;

public class Sponsor implements User{

  private String name;

  public Sponsor(String name){
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
