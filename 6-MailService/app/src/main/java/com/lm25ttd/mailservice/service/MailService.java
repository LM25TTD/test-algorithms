package com.lm25ttd.mailservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.Toast;

import com.lm25ttd.mailservice.R;
import com.lm25ttd.mailservice.structure.LinkedList;
import com.lm25ttd.mailservice.structure.Node;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MailService extends Service {

    private ExecutorService executionQueue = Executors.newSingleThreadExecutor();

    public static final int MSG_PROCESS_MAIL_THREAD = 1;
    public static final int MSG_PROCESSED_MAIL_THREAD = 2;

    public static final String DATA_MAIL_THREAD_RAW = "mailThreadRaw";

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_PROCESS_MAIL_THREAD:
                    handleProcessMailThread(msg);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public void onCreate() {

        // Tell the user we stopped.
        Toast.makeText(this, R.string.service_running, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {

        // Tell the user we stopped.
        Toast.makeText(this, R.string.service_stopped, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private void handleProcessMailThread(final Message msg) {
        final Bundle rcvData = msg.peekData();

        if (rcvData == null) return;

        final String mailThreadRaw = (String) rcvData.get(DATA_MAIL_THREAD_RAW);

        if (TextUtils.isEmpty(mailThreadRaw)) return;

        final Messenger reply = msg.replyTo;

        if (reply == null) return;

        executionQueue.submit(new Runnable() {
            @Override
            public void run() {
                LinkedList list = new LinkedList();


                String[] mails = mailThreadRaw.split(LinkedList.LIST_SPLIT);
                for (String mail : mails) {
                    if (!TextUtils.isEmpty(mail)) {
                        list.push(new Node(mail));
                    }
                }

                list.removeDuplicates();

                Bundle data = new Bundle();
                data.putString(
                        DATA_MAIL_THREAD_RAW,
                        list.print()
                );

                Message message = Message.obtain(null, MSG_PROCESSED_MAIL_THREAD, 0, 0);
                message.setData(data);

                try {
                    reply.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
