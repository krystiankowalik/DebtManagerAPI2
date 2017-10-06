package com.kryx07.debtmanager2.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private LocalDate date;
    private BigDecimal amount;
    private String description;

    private boolean common;

    //@JsonManagedReference("user_transactions")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User payer;

    //@JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "group_id")
    private Group group;

    //@JsonIgnore
    //@JsonManagedReference("transaction_payables")
    /*@OneToMany(mappedBy = "transaction")
    private Set<Payable> payables;*/

    public Transaction() {
    }
}
