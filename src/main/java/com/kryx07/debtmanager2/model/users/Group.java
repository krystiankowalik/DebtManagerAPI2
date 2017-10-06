package com.kryx07.debtmanager2.model.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String name;

    @JsonBackReference("users_groups")
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "users_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

  /*  @OneToMany(mappedBy = "group")
    private Set<Payable> payables;*/


    //@JsonIgnore
    @JsonBackReference("transactions")
    @OneToMany(mappedBy = "group", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Transaction> transactions;

    public void addUser(User user) {
        this.users.add(user);
    }
}