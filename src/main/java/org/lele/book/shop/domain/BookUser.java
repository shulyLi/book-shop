package org.lele.book.shop.domain;

import org.lele.book.shop.commen.StringView;
import org.lele.book.shop.domain.help.UserType;

import java.time.LocalDateTime;

/**
 * author  shuly
 * date    17-10-18
 * description:
 */

public class BookUser extends StringView {
    public int      id;
    public String   email;
    public String   passWord;
    public String   userName;
    public String   userHead;
    public String   userMark;
    public UserType userType;
    public boolean  isDel;
    public boolean  isBlack;
    public LocalDateTime  createTime;



    public void defaultValue(){
        userHead = "None";
        userMark = "None";
        userType = UserType.Purple;
        isDel    = false;
        isBlack  = false;
    }
}
