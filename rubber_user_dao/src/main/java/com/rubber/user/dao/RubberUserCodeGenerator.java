package com.rubber.user.dao;

import com.baomidou.mybatisplus.extension.api.R;
import com.rubber.base.components.mysql.utils.RubberSqlCodeGenerator;

/**
 * @author luffyu
 * Created on 2021/6/14
 */
public class RubberUserCodeGenerator {


    public static void main(String[] args) {

        RubberSqlCodeGenerator.CodeGeneratorConfigBean configBean = new RubberSqlCodeGenerator.CodeGeneratorConfigBean();
        configBean.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/user_db_00?useUnicode=true;characterEncoding=utf-8");
        configBean.setUserName("root");
        configBean.setPassword("root");
        configBean.setModelName("rubber_user_dao");
        configBean.setPackageParent("com.rubber.user.dao");
        configBean.setAuthor("luffyu");

        RubberSqlCodeGenerator rubberSqlCodeGenerator =  new RubberSqlCodeGenerator(configBean);
        rubberSqlCodeGenerator.create("t_user_basic_info_00");
    }
}
