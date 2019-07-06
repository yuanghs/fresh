package com.fresh.data_connect;

import com.fresh.bean.Location;
import com.fresh.bean.User;
import com.fresh.mappers.LocationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author ygh
 * @date 2019/7/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class LocationDao {

    @Autowired
    private LocationMapper locationMapper;

    /**
     * 测试 selectLocationsByUid()
     *              > 成功！
     *
     */
    @Test
    public void test1() {

        User user = new User();

        user.setUid(1);

        Location location = new Location();
        location.setUser(user);

        List<Location> list = locationMapper.selectLocationsByUid(location);

        list.forEach(e -> System.out.println(e));

    }

    /**
     * 测试 insertLocation()
     *              > 成功！
     *
     */
    @Test
    public void test2() {

        User user = new User();

        user.setUid(6);

        Location location = new Location();
        location.setUser(user);
        location.setAddress("湖北省 武汉市 洪山区");

        int a = locationMapper.insertLocation(location);

        System.out.println(a);
    }

    /**
     * 测试 deleteByPrimaryKey()
     *              > 成功！
     *
     */
    @Test
    public void test3() {

        Location location = new Location();

        location.setLid(9);

        int a = locationMapper.deleteByPrimaryKey(location);

        System.out.println(a);
    }

    /**
     * 测试 updateByPrimaryKey()
     *              > 成功！
     *
     */
    @Test
    public void test4() {

        Location location = new Location();

        location.setLid(7);
        location.setAddress("本星系群 麦哲伦星系 银河系");

        int a = locationMapper.updateByPrimaryKey(location);

        System.out.println(a);
    }

    /**
     * 测试 selectByPrimaryKey()
     *              > 成功！
     *
     */
    @Test
    public void test5() {

        Location location = new Location();
        location.setLid(8);

        Location location1 = locationMapper.selectByPrimaryKey(location);

        System.out.println(location1);
    }




}
