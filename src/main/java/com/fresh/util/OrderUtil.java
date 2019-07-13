package com.fresh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ygh
 * @date 2019/7/12
 */
public class OrderUtil {

    /**
     * 随机生成一个订单号
     * @param date
     * @return
     */
    public static String getOrderNumber(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String dateNumber = simpleDateFormat.format(date);

        // 拼接订单号
        String orderNumber = dateNumber + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0,10);

        return orderNumber;
    }

    /**
     * 得到交易时间
     * @param date
     * @return
     */
    public static String getOrderTime(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateNumber = simpleDateFormat.format(date);

        return dateNumber;
    }

}
