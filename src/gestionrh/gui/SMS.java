/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrh.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


/**
 *
 * @author MediaCenter Zaghouan
 */
public class SMS {
    
    public static final String ACCOUNT_SID = "AC75038046937bd10e2a96db08e57129da";
    public static final String AUTH_TOKEN = "35cd0e2c6b64d006d5ed1532126d8f07";
    
        public void sendsms(String code ){
    
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21698781664"),
                new com.twilio.type.PhoneNumber("+17407167788"),
                code)
            .create();

        System.out.println(message.getSid());

        
    
    
    
    }
    
    
    
}