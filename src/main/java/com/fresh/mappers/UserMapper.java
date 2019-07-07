package com.fresh.mappers;

import com.fresh.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户接口
 */
@Repository
public interface UserMapper {

    /**
     * 根据用户主键查询用户信息
     *                > 账户，昵称，性别
     * @param user
     * @return
     */
    User SelectUserByPrimaryKey(User user);

    /**
     * 根据账户查询用户信息
     *                > 主键，账户，昵称，性别
     * @param user
     * @return
     */
    User SelectUserByAccount(User user);

    /**
     * 插入一条记录：用户信息
     *          > 注册时使用，只插入：账户名，昵称，性别
     * @param user
     * @return
     */
    int insertUser(User user);



//    int updateSexByPrimaryKey(@Param("nickname") String nickname, @Param("sex") String sex, @Param("uid") Integer uid);

    /**
     * 根据用户主键修改用户的性别和昵称
     *  @param user
     */
    int updateByPrimaryKey(User user);
}