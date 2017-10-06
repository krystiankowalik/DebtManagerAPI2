package com.kryx07.debtmanager2.model.payable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payables")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class Payable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Transient
    private User debtor;

    @Transient
    private User creditor;

    private BigDecimal amount;

    private boolean settled;

  /*  //@JsonBackReference(value = "transaction_payables")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;*/

    @JsonIgnore
    //@JsonBackReference("group")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public Payable(User debtor, User creditor, BigDecimal amount, boolean settled, Transaction transaction, Group group) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
        this.settled = settled;
        //this.transaction = transaction;
        this.group = group;
    }


}
