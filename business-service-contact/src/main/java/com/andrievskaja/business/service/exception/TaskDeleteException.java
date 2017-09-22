/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.exception;

/**
 *
 * @author Людмила
 */
public class TaskDeleteException extends Exception {

    private final FaultCode code;

    public TaskDeleteException(FaultCode code, String message) {
        super(message);
        this.code = code;
    }

    public TaskDeleteException(FaultCode code) {
        this.code = code;
    }

    public FaultCode getCode() {
        return code;
    }
    
}
