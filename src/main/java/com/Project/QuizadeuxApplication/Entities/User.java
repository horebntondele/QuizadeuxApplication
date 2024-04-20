package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id", nullable = false)
    private int User_id;
    @Basic
    @Column(name = "Username", nullable = false, unique = true)
    private String username;
    @Basic
    @Column(name = "Password", nullable = false, unique = true)
    private  String Password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "User_id")},
            inverseJoinColumns = {@JoinColumn(name = "Role_id")})
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "User_id=" + User_id +
                ", username='" + username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public void assignRoleToUser(Role role){
        this.roles.add(role);
        role.getUsers().add(this);

    }

    public void removeRoleFromUser(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return User_id == user.User_id && Objects.equals(username, user.username) && Objects.equals(Password, user.Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(User_id, username, Password);
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
