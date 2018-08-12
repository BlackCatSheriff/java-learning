package com.ouc.tool.idea;

/**
 * Status
 *
 * @author skyUnv
 * created on 2018/5/66( 11),
 */
public enum Status {
    //a demo convert a status json to a Status Enumeration class
//important
    //1xx Informational
    CONTINUE(100),
    PROCESSING(102),
    CHECKPOINT(103),

    //2xx Success
    OK(200),
    CREATED(201),
    ACCEPTED(202),

    //3xx
    FOUND(302),

    //4xx
    UNAUTHORIZED(401),
    CONFLICT(409),
    GONE(410),;
    int code;

    Status(int code) {
        this.code = code;
    }

}
