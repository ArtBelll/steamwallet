package ru.steamwallet.swcommon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Artur Belogur on 13.04.17.
 */
@MappedSuperclass
public abstract class User {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id = -1;


    @Getter @Setter
    protected String login;

    @Getter @Setter
    protected String email;

    @JsonIgnore
    @Getter @Setter
    protected String password;

    @Getter @Setter
    protected Date created = new Date();

    @JsonIgnore
    @Getter @Setter
    @Column(name = "ip")
    protected String ipAddress;

    @Getter @Setter
    protected boolean enable = true;

}
