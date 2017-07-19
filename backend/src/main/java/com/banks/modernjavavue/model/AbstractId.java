package com.banks.modernjavavue.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created on 2016-01-23.
 *
 * @author lavenderx
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractId {

    @Id
    @GeneratedValue
    private Long id;
}
