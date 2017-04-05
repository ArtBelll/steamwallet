package ru.steamwallet.swcommon.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Date created = new Date();

    @Getter @Setter
    @Column(name = "ip")
    private String ipAddress;

    @Getter @Setter
    private boolean enable = true;

    @JsonIgnore
    @Getter @Setter
    @OneToMany(mappedBy = "buyer")
    private List<Purchase> purchases;

    public Buyer() {}

    @JsonCreator
    public Buyer(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
