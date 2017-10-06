package com.kryx07.debtmanager2.model.payable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payables")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"debtor", "creditor", "group", "transaction"})
public class Payable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Transient
    @Getter(onMethod = @_(@JsonIgnore))
    private User debtor;

    @Transient
    @Getter(onMethod = @_(@JsonIgnore))
    private User creditor;

    private int tmp;

    private BigDecimal amount;

    private boolean settled;

    //@Getter(onMethod = @_(@JsonIgnore))
    @Getter(onMethod = @_(@JsonBackReference("transaction_payables")))
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @Getter(onMethod = @_(@JsonIgnore))
    //@JsonBackReference("group")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public Payable(int tmp, User debtor, User creditor, BigDecimal amount, boolean settled, Transaction transaction, Group group) {
        this.tmp = tmp;
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
        this.settled = settled;
        this.transaction = transaction;
        this.group = group;
    }


}
