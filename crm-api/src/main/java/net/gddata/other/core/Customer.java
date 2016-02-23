package net.gddata.other.core;

import lombok.Data;

import java.util.Date;


/**
 * Created by knix on 16/2/17.
 */
@Data
public class Customer {
    private Integer id;
    private String name; //姓名
    private String gender; //性别
    private String birthday; //生日
    private String address;//住址
    private String telephone; //电话
    private String email;//邮箱
    private Date enroll;//注册日期
}
