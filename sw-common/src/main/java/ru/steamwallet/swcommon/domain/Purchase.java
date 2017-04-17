package ru.steamwallet.swcommon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.steamwallet.swcommon.StatusBuy;

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
    private String game;

    @Getter @Setter
    private String url;

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
    private int status = StatusBuy.NON_STATUS.getStatus();

    @JsonIgnore
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @JsonIgnore
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Purchase() {}

    public Purchase(float sum, String game, String url, Seller seller, Buyer buyer) {
        this.start = new Date();
        this.sum = sum;
        this.game = game;
        this.url = url;
        this.status = StatusBuy.PAYMANT.getStatus();
        this.seller = seller;
        this.buyer = buyer;
    }
}
