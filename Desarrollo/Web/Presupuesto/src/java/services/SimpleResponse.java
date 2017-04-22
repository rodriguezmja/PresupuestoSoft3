/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.Serializable;

/**
 *
 * @author joseco
 */

public class SimpleResponse implements Serializable{
    
    private boolean success;
    private String message;

    public SimpleResponse() {
        message = "";
        success = true;
    }
    
    public SimpleResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
