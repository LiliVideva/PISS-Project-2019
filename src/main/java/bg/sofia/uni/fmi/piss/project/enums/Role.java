package bg.sofia.uni.fmi.piss.project.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN(1L),
    USER(2L);

    private Long id;

    Role(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return String.format("ROLE_%s", name());
    }
}
