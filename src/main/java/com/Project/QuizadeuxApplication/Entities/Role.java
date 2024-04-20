package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_id", nullable = false)
    private  int Role_id;
    @Basic
    @Column(name = "Role_Name", nullable = false, unique = true)
    private  String Role_Name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users= new HashSet<>();

    public Role(String role_Name) {
        Role_Name = role_Name;
    }

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "Role_id=" + Role_id +
                ", Role_Name='" + Role_Name + '\'' +
                '}';
    }

    public int getRole_id() {
        return Role_id;
    }

    public void setRole_id(int role_id) {
        Role_id = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Role_id == role.Role_id && Objects.equals(Role_Name, role.Role_Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Role_id, Role_Name);
    }

    public String getRole_Name() {
        return Role_Name;
    }

    public void setRole_Name(String role_Name) {
        Role_Name = role_Name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
