package com.example.inika.smsexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver
{
    //process the receive information here
    @Override
    public void onReceive(Context context, Intent intent)
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "SMS from ";
        //parse the text of the message
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            //create the an array of SmsMessage type with the same length
            //as Object array
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                if (i==0) {
                	//---get the sender address/phone number---
                    // and concatenate smaller messages into a string
                	str += msgs[i].getOriginatingAddress();
                	str += ": ";
                } 
                //---get the message body---
                str += msgs[i].getMessageBody().toString();                	
            }
            
            //---display the new SMS message---
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            Log.d("SMSReceiver", str);
            
            //---stop the SMS message from being broadcasted---
            this.abortBroadcast();
            
            //---launch the SMSActivity---
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);

            
            //---send a broadcast intent to update the SMS received in the activity---
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
            //put your message in this intent
            broadcastIntent.putExtra("sms", str);
            //broadcast the intent
            context.sendBroadcast(broadcastIntent);
        }
    }
}

