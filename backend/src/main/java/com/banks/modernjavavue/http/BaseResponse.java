package com.banks.modernjavavue.http;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 2016-05-25.
 *
 * @author lavenderx
 */
@Getter
@Setter
public abstract class BaseResponse implements Serializable {

    private String status;
    private String message;
}
