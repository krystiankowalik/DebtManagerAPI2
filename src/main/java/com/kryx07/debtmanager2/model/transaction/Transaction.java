package com.kryx07.debtmanager2.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kryx07.debtmanager2.model.due.Due;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transactions")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private LocalDate date;
    private BigDecimal amount;
    private String description;

    private boolean common;

    @Getter(AccessLevel.NONE)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    public TransactionType getTransactionType() {
        return transactionType != null ? transactionType : TransactionType.EXPENSE;
    }

    //@JsonManagedReference("user_transactions")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User payer;

    //@JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "group_id")
    private Group group;

    //@JsonIgnore
    @Getter(onMethod = @_(@JsonManagedReference("transaction_dues")))
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    private List<Due> dues;
}
