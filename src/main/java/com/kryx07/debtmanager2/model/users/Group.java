package com.kryx07.debtmanager2.model.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group implements Serializable {

    @Transient
    private long serialVersionUID = 13877953648146L;

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private int id;
    private String name;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.DETACH,  CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "users_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }


    public int getId() {
        return id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}