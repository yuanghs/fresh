package com.fresh.data_connect;

import com.fresh.bean.User;
import com.fresh.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ygh
 * @date 2019/7/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试  SelectUserByPrimaryKey()
     *              > 成功！
     */
    @Test
    public void test1(){

        User user = new User();

        user.setUid(7);

        User user1 = userMapper.SelectUserByPrimaryKey(user);

        System.out.println(user1);
    }

    /**
     * 测试  SelectUserByAccount()
     *              > 成功！
     */
    @Test
    public void test2(){

        User user = new User();

        user.setUid(2);

        user.setAccount("18627707436");

        User user1 = userMapper.SelectUserByAccount(user);

        System.out.println(user1);
    }

    /**
     * 测试  insertUser()
     *              > 成功！
     */
    @Test
    public void test3(){

        User user = new User();

        user.setNickname("自由飞翔");
        user.setAccount("18896053249");

        int a = userMapper.insertUser(user);

        System.out.println(a);
    }

    /**
     * 测试  updateSexByPrimaryKey()
     *              > 成功！
     */
    @Test
    public void test4(){
        User user = new User();

        user.setUid(6);
//        user.setNickname("啦啦啦");
        user.setSex("女");

//        int a = userMapper.updateSexByPrimaryKey(user.getNickname(), user.getSex(), user.getUid());
        int a = userMapper.updateByPrimaryKey(user);


        System.out.println(a);
    }





}
