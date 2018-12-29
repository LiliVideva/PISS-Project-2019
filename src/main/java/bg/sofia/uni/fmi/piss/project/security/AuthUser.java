package bg.sofia.uni.fmi.piss.project.security;

import bg.sofia.uni.fmi.piss.project.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthUser extends User {
    private static final long serialVersionUID = -5548153808005025549L;

    private Account account;

    private String name;

    AuthUser(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
    }

    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }
}
