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
public class Buyer extends User {

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
