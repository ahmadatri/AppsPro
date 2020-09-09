package com.multiselect;

/**
 * Created by arthonsystechnologiesllp on 10/03/17.
 */

public class UserModel {

    boolean isSelected;
    String userName;
    Integer flags;
    //now create constructor and getter setter method using shortcut like command+n for mac & Alt+Insert for window.


    public UserModel(boolean isSelected, String userName, Integer flags) {
        this.isSelected = isSelected;
        this.userName = userName;
        this.flags=flags;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getFlags(){return flags;}

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
