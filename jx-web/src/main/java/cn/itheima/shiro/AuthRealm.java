package cn.itheima.shiro;

import cn.itheima.domain.Module;
import cn.itheima.domain.Role;
import cn.itheima.domain.User;
import cn.itheima.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author huxianguang
 * @create 2017-10-30-下午7:54
 **/
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByUsername(token.getUsername());
        if (user == null) return null;
        /**
         * principal:当前登录的用户信息
         * credentials：凭证，密码
         * realmName:当前Realm的名字
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前用户
        User user = (User) principalCollection.fromRealm(this.getName()).iterator().next();
        Set<Role> roles = user.getRoles();
        List<String> list = new ArrayList<String>();
        for (Role role : roles) {
            Set<Module> modules = role.getModules();
            for (Module module : modules) {
                //判断是否是一级菜单
                if (module.getCtype() == 0) {
                    list.add(module.getCpermission());
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(list);
        return info;
    }


}
