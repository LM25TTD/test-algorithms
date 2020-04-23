package com.lm25ttd.mailservice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.lm25ttd.mailservice.R;
import com.lm25ttd.mailservice.service.MailService;
import com.lm25ttd.mailservice.structure.LinkedList;
import com.lm25ttd.mailservice.structure.Node;

public class MainActivity extends AppCompatActivity {

    private Messenger mailServiceMessenger;
    private boolean isBound;
    private TextView mailSent;
    private TextView mailReceived;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailSent = this.findViewById(R.id.mailSent);
        mailReceived = this.findViewById(R.id.mailReceived);

        doBindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mailServiceMessenger = new Messenger(service);
            sendMailThread();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mailServiceMessenger = null;
        }
    };

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MailService.MSG_PROCESSED_MAIL_THREAD:
                    handleReceivedMailThread(msg);
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger mMessenger = new Messenger(new IncomingHandler());

    private void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because there is no reason to be able to let other
        // applications replace our component.
        bindService(new Intent(this,
                MailService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
        isBound = true;
    }

    private void doUnbindService() {
        if (isBound) {
            // Detach our existing connection.
            unbindService(mServiceConnection);
            isBound = false;
        }
    }

    private void sendMailThread() {
        LinkedList list = new LinkedList();

        list.push(new Node("A"));
        list.push(new Node("B"));
        list.push(new Node("B"));
        list.push(new Node("B"));
        list.push(new Node("C"));
        list.push(new Node("C"));
        list.push(new Node("D"));
        list.push(new Node("D"));

        String mailThread = list.print();
        mailSent.setText(mailThread);

        Bundle data = new Bundle();
        data.putString(
                MailService.DATA_MAIL_THREAD_RAW,
                mailThread
        );

        Message msg = Message.obtain(null, MailService.MSG_PROCESS_MAIL_THREAD, 0, 0);
        msg.replyTo = mMessenger;
        msg.setData(data);

        try {
            mailServiceMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void handleReceivedMailThread(Message msg) {
        String result = msg.getData().getString(MailService.DATA_MAIL_THREAD_RAW);
        if (!TextUtils.isEmpty(result)) {
            Toast.makeText(this, R.string.mail_thread_received, Toast.LENGTH_SHORT).show();
            mailReceived.setText(result);
        }
    }
}
