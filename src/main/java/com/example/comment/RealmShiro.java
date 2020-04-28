package com.example.comment;

import com.example.pojo.SysPermissions;
import com.example.pojo.SysRoles;
import com.example.pojo.SysUsers;
import com.example.service.SysUsersService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @Author 张佳奇
 * @Description 自定义shiro的核心 为当前用户做认证即赋权
 * @Date 2020-04-26 16:08
 */
public class RealmShiro extends AuthorizingRealm {

    @Resource

    SysUsersService sysUsersService;

    /**
     * 该方法用于当前用户授权(对当前用户进行角色和权限初始化)[告知shiro当前登录用户所拥有的角色和权限]
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //创建权限信息对象
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //获取当前用户的用户名
        String username=(String)principalCollection.getPrimaryPrincipal();
        //根据用户名再数据库查找用户
        SysUsers user=sysUsersService.findUserByName(username);
        //遍历用户所拥有的角色
        for (SysRoles sysRoles:user.getRoles()){
            //把用户所拥有的角色加入Realm中
            authorizationInfo.addRole(sysRoles.getRolename());
            //遍历角色所拥有的权限
            for (SysPermissions permissions:sysRoles.getPermissions()){
                //把角色所拥有的权限加入Realm中
                authorizationInfo.addStringPermission(permissions.getName());
            }
        }
        return authorizationInfo;
    }

    /**
     * 身份认证 登录时判断用户身份信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //authenticationToken保存了用户的登录信息
        String username=(String)authenticationToken.getPrincipal();
        SysUsers sysUsers=sysUsersService.findUserByName(username);
        if (sysUsers==null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(sysUsers.getUsername(),sysUsers.getPassword(), ByteSource.Util.bytes(sysUsers.getSalt()),getName());
        return authenticationInfo;
    }

    @Override
    public void setName(String name) {
        super.setName("realmShiro");
    }
}
