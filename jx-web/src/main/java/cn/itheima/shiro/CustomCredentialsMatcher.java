package cn.itheima.shiro;

import cn.itheima.util.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 密码比较器
 * @author huxianguang
 * @create 2017-10-30-下午7:56
 **/
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 比较密码
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        char[] password = uToken.getPassword();
        //加密
        String newPwd = Encrypt.md5(new String(password), uToken.getUsername());
        String dbPwd = (String) info.getCredentials();
        return equals(newPwd,dbPwd);
    }
}
