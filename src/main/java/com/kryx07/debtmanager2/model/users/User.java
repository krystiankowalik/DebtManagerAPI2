package com.kryx07.debtmanager2.model.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(exclude = {"groups", "transactions"})
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String username;
    @Getter(onMethod = @_(@JsonIgnore))
    private String password;

    //@JsonManagedReference("users_groups")
  //  @Getter(onMethod = @_(@JsonManagedReference("users_groups")))
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Group> groups;


    @Getter(onMethod = @_(@JsonBackReference))
    @OneToMany(mappedBy = "payer", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Transaction> transactions;

    /*@Getter(onMethod = @_(@JsonBackReference("payables_user")))
    @OneToMany(mappedBy = "payer", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Due> dues;*/

   /* @Getter(onMethod = @_(@JsonBackReference("payables_user")))
    @OneToMany(mappedBy = "debtor", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Due> payables;

    @Getter(onMethod = @_(@JsonBackReference("receivables_user")))
    @OneToMany(mappedBy = "creditor", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Due> receivables;*/

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

}