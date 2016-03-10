package net.gddata.other.core;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private Integer id;
    private String name; //姓名
    private String letter; //拼音
    private String gender; //性别
    private Date birthday; //生日
    private Date lunar;//阴历生日
    private int happyDay;//今年的生日
    private String address;//住址
    private String telephone; //电话
    private String email;//邮箱
    private Date enroll;//注册日期
    private String user;//所属用户
    private boolean willBirthday;
}
