package com.rubber.user.dao.logic;

import com.rubber.user.dao.entity.GlobalUidGenerator;
import com.rubber.user.dao.exception.UserDaoException;
import com.rubber.user.dao.mapper.GlobalUidGeneratorMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Component
public class GlobalUidGeneratorLogic {

    @Resource
    private GlobalUidGeneratorMapper globalUidGeneratorMapper;


    /**
     * 不需要在事务中进行，如果已经在事务中，则挂起当前事务，并完成插入
     *
     * 以此来提高 全局生成uid的效率
     *
     * 如果异常了，那么重新生成一个uid即可
     *
     * 生成全局的uid
     * @return 返回uid的相关信息
     */
    @Transactional(
        propagation = Propagation.NOT_SUPPORTED
    )
    public int createGlobalUid(){
        GlobalUidGenerator globalUidGenerator = new GlobalUidGenerator();
        globalUidGenerator.setParams("");
        int value = globalUidGeneratorMapper.insert(globalUidGenerator);
        if (value <=0 || globalUidGenerator.getUid() == null){
            throw new UserDaoException("createGlobalUid uid is null");
        }
        return globalUidGenerator.getUid();
    }


}
