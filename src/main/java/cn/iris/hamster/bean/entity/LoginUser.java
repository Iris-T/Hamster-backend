package cn.iris.hamster.bean.entity;

import cn.iris.hamster.common.constants.CommonConstants;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户对象，继承于UserDetails
 *
 * @author Iris
 * @ClassName LoginUser
 * @date 2022/12/28 15:03
 */

@Data
public class LoginUser implements UserDetails {

    /**
     * 用户信息
     */
    private User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUser(User user) {
        this(user, null);
    }

    public LoginUser(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return CommonConstants.STATUS_ENABLE.equals(user.getStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return CommonConstants.STATUS_ENABLE.equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return CommonConstants.STATUS_ENABLE.equals(user.getStatus());
    }

    @Override
    public boolean isEnabled() {
        return CommonConstants.STATUS_ENABLE.equals(user.getStatus());
    }
}
