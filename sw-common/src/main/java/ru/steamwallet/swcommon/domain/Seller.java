package ru.steamwallet.swcommon.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Artur Belogur on 28.03.17.
 */
@Entity
@Table(name = "sellers")
public class Seller implements Serializable {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    @Getter @Setter
    private String login;

    @JsonIgnore
    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private Float reaction = -1f;

    @Getter @Setter
    private Date created = new Date();

    @JsonIgnore
    @Getter @Setter
    @Column(name = "ip")
    private String ipAddress;

    @Getter @Setter
    private boolean enable = true;

    @JsonIgnore
    @Getter @Setter
    @OneToMany(mappedBy = "seller")
    private List<Purchase> purchases;

    public Seller() {}

    @JsonCreator
    public Seller(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
