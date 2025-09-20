package br.com.cacadoresti.saga.sagh.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(
    name = "tab_user",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "un_cpf_tab_user",
                columnNames = {"cpf"}
        ),
        @UniqueConstraint(
                name = "un_email_tab_user",
                columnNames = {"email"}
        ),
        @UniqueConstraint(
            name = "un_username_tab_user",
            columnNames = {"username"}
        )
    }
)
//TODO: Implement custom exceptions
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CPF
    @Column(nullable = false)
    private String cpf;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    @Size(message = "Password cannot exceed 100 characters", max = 100)
    private String password;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "tab_user_has_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "user_role_id")
    )
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Affiliation> affiliations = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                         .map(UserRole::toString)
                         .map(SimpleGrantedAuthority::new)
                         .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public User(String cpf, String name, String surname, LocalDate birthdate, String email, String username, String password, Set<UserRole> roles) {
        this.cpf = cpf;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.setRoles(roles);
    }
}
