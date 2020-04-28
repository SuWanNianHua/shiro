package com.example.comment;

import com.example.pojo.SysUsers;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author 张佳奇
 * @Description
 * @Date 2020-04-26 15:53
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumber=new SecureRandomNumberGenerator();
    //散列算法 即密码加密
    public static final String ALGORITHE_NAME="md5";
    //自定义散列次数
    public static final int HASH_ITERATIONS=2;

    public void emcryptPassword(SysUsers user){
        //随机字符串作为盐值因子
        user.setSalt(randomNumber.nextBytes().toHex());
        String newPassword=new SimpleHash(ALGORITHE_NAME,user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),HASH_ITERATIONS).toHex();
       user.setPassword(newPassword);
    }
}
