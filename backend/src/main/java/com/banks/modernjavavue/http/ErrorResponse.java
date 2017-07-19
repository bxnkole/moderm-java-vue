package com.banks.modernjavavue.http;

import lombok.NoArgsConstructor;

/**
 * Created on 2016-05-25.
 *
 * @author lavenderx
 */
@NoArgsConstructor
public class ErrorResponse extends BaseResponse {

    public ErrorResponse(String status, String message) {
        super.setStatus(status);
        super.setMessage(message);
    }

}