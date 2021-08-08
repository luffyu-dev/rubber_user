package com.rubber.user.service.encrypt.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.rubber.user.service.encrypt.IEncryptHandler;
import org.springframework.stereotype.Component;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Component(value = "encryptProvider")
public class DefaultEncryptProvider implements IEncryptHandler {
    /**
     * 需要加密的密码信息
     *
     * @param psw  未加密密码
     * @param salt 盐值
     * @return 返回加密密码
     */
    @Override
    public String encrypt(String psw, String salt) {
        String pswSalt = joinPswAndSalt(psw,salt);
        return BCrypt.hashpw(pswSalt);
    }

    /**
     * 创建盐值
     *
     * @param length 长度
     * @return 返回创建的盐值
     */
    @Override
    public String createSalt(int length) {
        return BCrypt.gensalt(length);
    }

    /**
     * 检测密码是否正确
     *
     * @param psw     密码信息
     * @param salt    盐值
     * @param encrypt 加密的值
     * @return
     */
    @Override
    public boolean matches(String psw, String salt, String encrypt) {
        try{
            String pswSalt = joinPswAndSalt(psw,salt);
            return BCrypt.checkpw(pswSalt,encrypt);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 连接两个参数
     * @param psw 当前的密码明文
     * @param salt 盐值
     * @return 返回连接之后的字符信息
     */
    private String joinPswAndSalt(String psw,String salt){
        return StrUtil.join("^_^",psw,salt);
    }
}
