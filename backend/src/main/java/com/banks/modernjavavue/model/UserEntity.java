package com.banks.modernjavavue.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created on 2016-01-18.
 *
 * @author lavenderx
 */
@Entity
@Table(name = "APPUSER")
@Getter
@Setter
public class UserEntity extends AbstractId {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean state;

    @Column
    private Date birthday;

    @Column(nullable = false)
    private String email;

}
