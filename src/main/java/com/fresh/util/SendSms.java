package com.fresh.util;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;



/**
 * @author 74123
 *
 * 功能：短信验证码服务小工具
 * 使用： 1. 调用 verifyCreator() 方法，产生随机验证码
 *       2. 调用 sendSms(String phone, String verify) 方法，发送短信
 */
public class SendSms {
    /**
     * 向目标手机号发送指定验证码内容
     * @param phone 目标手机号
     * @param verify 指定验证码
     */
    public static void sendSms(String phone, String verify) {
    

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "鱼和羊");
        request.putQueryParameter("TemplateCode", "SMS_169865180");
        request.putQueryParameter("TemplateParam", "{\"code\": " + verify + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 产生随机验证码
     * @return 验证码
     */
    public static String verifyCreator() {
        double ran = Math.random();
        return String.valueOf(ran*10000000).substring(0, 6);
    }

    public static void main(String[] args) {
        //生成随机验证码
        String verify = verifyCreator();
        //发送短信，要钱的........谨慎测试
        sendSms("13396053249",  verify);
    }
}
