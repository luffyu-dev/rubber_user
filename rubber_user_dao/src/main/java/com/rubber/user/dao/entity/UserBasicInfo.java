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
 * 用户基础信息表
 * </p>
 *
 * @author luffyu
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_basic_info")
public class UserBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 外表关联的uid
     */
    private Integer uid;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 用户简介
     */
    private String userMotto;

    /**
     * 用户头像地址
     */
    private String userAvatar;

    /**
     * 0表示男 1表示女 2表示未知
     */
    private Integer userSex;

    /**
     * 生日
     */
    private LocalDateTime userBirthday;

    /**
     * 用户地区
     */
    private String userArea;

    /**
     * 用户地址
     */
    private String userAddress;

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
