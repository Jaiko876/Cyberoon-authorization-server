package net.pet.auth_server.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "authorities", schema = "public")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "authority")
    private String authority;
}
