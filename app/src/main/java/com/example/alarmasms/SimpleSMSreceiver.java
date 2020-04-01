package com.example.alarmasms;
import android.os.Bundle;
import android.telephony.*;
import android.widget.Toast;
import android.content.*;


public class SimpleSMSreceiver extends BroadcastReceiver  {

    private static final String TAG = "Message received";

    @Override
    public void onReceive(Context context, Intent intent){
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus=(Object[]) pudsBundle.get("pdus");
        SmsMessage messages=SmsMessage.createFromPdu((byte[]) pdus[0]);

        Intent smsIntent=new Intent(context,SMS_receive.class);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        smsIntent.putExtra("MessageNumber",messages.getOriginatingAddress());
        smsIntent.putExtra("Message",messages.getMessageBody());
        context.startActivity(smsIntent);
        Toast.makeText(context,"SMS Received From :"+messages.getOriginatingAddress()+"\n"+messages.getMessageBody(),Toast.LENGTH_LONG).show();
    }
}