package org.project2action.domain;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "GOOGLE_ID", unique = true)
    private String googleId;
    private String email;
    @Column(name = "FULL_NAME")
    private String fullName;
    private String locale;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MEMBERS_QUEUES",
            joinColumns = {@JoinColumn(name = "MEMBER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "QUEUE_ID")}
    )
    private List<Queue> memberIn;*/

    /*@ManyToMany
    @JoinTable(name = "USERS_QUEUES",
            joinColumns = {@JoinColumn(name = "USERS_ID")},
            inverseJoinColumns = {@JoinColumn(name = "QUEUE_ID")}
    )
    private Set<Queue> queues;*/
    @Column(name = "SECURITY_TOKEN")
    private String securityToken;

    public User(Long id, String googleId, String email, String fullName, String locale) {
        this.id = id;
        this.googleId = googleId;
        this.email = email;
        this.fullName = fullName;
        this.locale = locale;
    }

    public User(User other, String securityToken) {
        this(other);
        this.securityToken = securityToken;
    }

    public User(User other) {
        this.id = other.id;
        this.googleId = other.googleId;
        this.email = other.email;
        this.fullName = other.fullName;
        this.locale = other.locale;
        this.securityToken = other.securityToken;
    }

    public User() {
    }

    public User(String email, String fullName, String token) {
        this.email = email;
        this.fullName = fullName;
        this.securityToken = token;
    }

    public Long getId() {
        return id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocale() {
        return locale;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("email", email)
                .add("full name", fullName)
                .toString();
    }

}
