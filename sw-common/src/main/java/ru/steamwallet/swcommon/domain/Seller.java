package ru.steamwallet.swcommon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Artur Belogur on 28.03.17.
 */
@Entity
@Table(name = "sellers")
public class Seller {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Float reaction;

    @Getter @Setter
    private Date created = new Date();

    @Getter @Setter
    private Date lastAction = new Date();

    @Getter @Setter
    @Column(name = "ip")
    private String ipAddress;

    @Getter @Setter
    private boolean enable = true;

    @Getter @Setter
    @OneToMany(mappedBy = "seller")
    private List<Purchase> purchases;

    public Seller() {}
}
