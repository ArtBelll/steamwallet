package ru.steamwallet.swcommon.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Artur Belogur on 29.03.17.
 */
@Entity
@Table(name = "buyers")
public class Buyer {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    @Getter @Setter
    private String login;

    @Getter @Setter
    @OneToMany(mappedBy = "buyer")
    private List<Purchase> purchases;
}
