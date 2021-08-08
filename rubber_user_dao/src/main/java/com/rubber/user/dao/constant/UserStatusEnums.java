package com.rubber.user.dao.constant;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
public enum UserStatusEnums {


    /**
     * 1开头的表示正常
     */
    NORMAL(10,"正常"),

    /**
     * 2开头的表示异常
     */
    PAUSE(20,"停用"),
    DELETE(21,"删除"),

    ;


    UserStatusEnums(Integer userStatus, String label) {
        this.userStatus = userStatus;
        this.label = label;
    }

    private Integer userStatus;

    private String label;


    public Integer getUserStatus() {
        return userStatus;
    }

    public String getLabel() {
        return label;
    }
}
