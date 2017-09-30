package com.kryx07.debtmanager2.model.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryx07.debtmanager2.model.transaction.Transaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @Transient
    private long serialVersionUID = 53877953648246L;

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;
    private String username;
    private String password;

    //@JsonManagedReference
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "payer",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Transaction> transactions;

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