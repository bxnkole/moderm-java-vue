package com.banks.modernjavavue.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created on 2017-07-20.
 *
 * @author banks
 */
@Entity
@Table(name = "QUOTE")
@Getter
@Setter
@NoArgsConstructor
public class QuoteEntity extends AbstractId {

    @Column(nullable = false, length = 99999)
    private String quote;

    public QuoteEntity(String quote) {
        this.quote = quote;
    }
}
