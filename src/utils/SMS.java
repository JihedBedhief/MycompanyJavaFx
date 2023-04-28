/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


/**
 *
 * @author MediaCenter Zaghouan
 */
public class SMS {
    
    public static final String ACCOUNT_SID = "ACa4011c2a24782039d1f0e7d2d7bfb0c9";
    public static final String AUTH_TOKEN = "ebf23b0de5332e3991df496825adf8bb";
    
        public void sendsms(String code ){
    
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21694937629"),
                new com.twilio.type.PhoneNumber("+15674832979"),
                code)
            .create();

        System.out.println(message.getSid());

        
    
    
    
    }
    
    
    
}