package sg.edu.np.mad.madpractical;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserModelClass implements Serializable {
    Integer  Name;
    Integer  Description;
    Boolean  Followed;

    public UserModelClass(Integer name, Integer description, Boolean followed) {
        Name = name;
        Description = description;
        Followed = followed;
    }

    public UserModelClass(String create) {
        if(create == "create"){
            Name = randomnum();
            Description = randomnum();
            Followed = randomBool();
        }
    }

    public UserModelClass() {

    }

    public Integer getName() {
        return Name;
    }

    public void setName(Integer name) {
        Name = name;
    }

    public Integer getDescription() {
        return Description;
    }

    public void setDescription(Integer description) {
        Description = description;
    }

    public Boolean getFollowed() {
        return Followed;
    }

    public void setFollowed(Boolean followed) {
        Followed = followed;
    }

    private int randomnum(){
        Random ran = new Random();
        int myRandomValue = ran.nextInt(999999);
        return myRandomValue;
    }

    private Boolean randomBool(){
        Random ran = new Random();
        int myRandomValue = ran.nextInt(2);
        List<Boolean> booleanList = new ArrayList<>();
        booleanList.add(true);
        booleanList.add(false);
        return booleanList.get(myRandomValue);
    }

    @Override
    public String toString() {
        return "UserModelClass{" +
                "Name=" + Name +
                ", Description=" + Description +
                ", Followed=" + Followed +
                '}';
    }
}
