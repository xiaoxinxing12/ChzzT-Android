package org.chzz.demo.common.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by copy on 2017/8/9.
 */
@Entity(nameInDb = "user_entity")
public class UserEntity {
    @Id
    private long userId = 0;
    private String name = null;
    private String avatar = null;
    private String gender = null;
    private String address = null;
    @Generated(hash = 1568847700)
    public UserEntity(long userId, String name, String avatar, String gender,
            String address) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
