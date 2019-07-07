package com.fresh.service.imple;

import com.fresh.bean.Location;
import com.fresh.bean.User;
import com.fresh.mappers.LocationMapper;
import com.fresh.mappers.UserMapper;
import com.fresh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ygh
 * @date 2019/7/3
 */
@Service("userService")
public class UserServiceImple implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public int login(User user) {
        return 0;
    }

    @Override
    public int updateLocation(User user, Location location) {
        return 0;
    }

    @Override
    public int updateInformation(User user) {
        return 0;
    }
}
