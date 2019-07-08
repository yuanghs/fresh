package com.fresh.mappers;

import com.fresh.bean.Location;
import com.fresh.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 地址接口
 */
@Repository
public interface LocationMapper {
    /**
     * 根据主键删除用户的地址
     * @param location
     * @return
     */
    int deleteByPrimaryKey(Location location);

    /**
     * 添加一条地址记录
     *         > 插入：用户的地址，用户的uid
     * @param location
     * @return
     */
    int insertLocation(Location location);

    /**
     * 根据用户的id查询出用户的地址
     *              > 地址是一个List
     * @param location
     * @return
     */
    List<Location> selectLocationsByUid(Location location);

    /**
     * 根据主键查询地址
     * @param location
     * @return
     */
    Location selectByPrimaryKey(Location location);

    /**
     * 根据主键修改地址
     * @param location
     * @return
     */
    int updateByPrimaryKey(Location location);
}