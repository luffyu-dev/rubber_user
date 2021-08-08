package com.rubber.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户账户表
 * </p>
 *
 * @author luffyu
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_account_info")
public class UserAccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id，自100000开始自增。
     */
    private Integer uid;

    /**
     * 用户登录账号
     */
    private String userAccount;

    /**
     * 用户登录密码
     */
    private String userPassword;

    /**
     * 登录盐值
     */
    private String userSalt;

    /**
     * 第三方登录账户id
     */
    private String thirdAccountId;

    /**
     * 第三方登录账户相关密钥信息
     */
    private String thirdAccountKey;

    /**
     * 用户登录类型 0表示账号密码登录 1表示手机验短登录 2表示微信扫码登录
     */
    private Integer accountType;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户状态 10表示正常 20表示停用 21表示注销
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 版本号
     */
    @Version
    private Integer version;


}
