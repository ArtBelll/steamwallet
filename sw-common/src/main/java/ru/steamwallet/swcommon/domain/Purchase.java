package ru.steamwallet.swcommon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Artur Belogur on 29.03.17.
 */
@Entity
@Table(name = "purchases")
public class Purchase {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    @Getter @Setter
    private Date start = new Date();

    @Getter @Setter
    @Column(name = "seller_success")
    private Date sellerSuccess;

    @Getter @Setter
    private Date endDate;

    @Getter @Setter
    private float sum = -1;

    @Getter @Setter
    private int status = -1;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
}
