package bg.sofia.uni.fmi.piss.project.entity;

import bg.sofia.uni.fmi.piss.project.enums.Role;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("UserRole[id=%d, role='%s'", id, role);
    }
}
