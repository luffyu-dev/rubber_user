package com.rubber.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * uid的全局自增生成器
 * </p>
 *
 * @author luffyu
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_global_uid_generator")
public class GlobalUidGenerator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id，自100000开始自增。
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 扩展参数
     */
    private String params;


}
