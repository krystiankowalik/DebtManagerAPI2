package com.kryx07.debtmanager2.model.users;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<Group> groups = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }


    public int getId() {
        return id;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}