import java.util.ArrayList;
public class VaccineInfo {
    private String id, lastName, firstName, vType, vDate, vLocation;
    public VaccineInfo(String[] arr){
        this.id = arr[0];
        this.lastName = arr[1];
        this.firstName = arr[2];
        this.vType = arr[3];
        this.vDate = arr[4];
        this.vLocation = arr[5];
    }

    public String getId(){ return id; }
    public String getLastName(){
        return lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getvType(){
        return vType;
    }
    public String getvDate(){
        return vDate;
    }
    public String getvLocation(){
        return vLocation;
    }
    public void setId(String newId){id = newId;}
    public void setLastName(String newLastName){lastName = newLastName; }
    public void setFirstName(String newFirstName){ firstName = newFirstName; }
    public void setvType(String newVType){ vType = newVType; }
    public void setvDate(String newVDate){ vDate = newVDate; }
    public void setvLocation(String newVLocation){ vLocation = newVLocation; }

    public void print(){
        System.out.println(id);
        System.out.println(lastName);
        System.out.println(firstName);
        System.out.println(vType);
        System.out.println(vDate);
        System.out.println(vLocation);
    }

}
