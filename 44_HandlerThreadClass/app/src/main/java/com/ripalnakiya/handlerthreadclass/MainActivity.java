package com.ripalnakiya.handlerthreadclass;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExampleHandlerThread handlerThread = new ExampleHandlerThread("HandlerThread");
    private ExampleRunnable runnable = new ExampleRunnable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendMessageButton = findViewById(R.id.sendMessageButton);
        Button removeMessageButton = findViewById(R.id.removeMessageButton);
        Button sendRefRunnableButton = findViewById(R.id.sendRefRunnableButton);
        Button removeRefRunnableButton = findViewById(R.id.removeRefRunnableButton);
        Button sendRunnableButton = findViewById(R.id.sendRunnableButton);
        Button removeAllButton = findViewById(R.id.removeAllButton);

        sendMessageButton.setOnClickListener(onClickListenerSend);
        sendRefRunnableButton.setOnClickListener(onClickListenerSend);
        sendRunnableButton.setOnClickListener(onClickListenerSend);

        removeMessageButton.setOnClickListener(onClickListenerRemove);
        removeRefRunnableButton.setOnClickListener(onClickListenerRemove);
        removeAllButton.setOnClickListener(onClickListenerRemove);

        handlerThread.start();
    }

    View.OnClickListener onClickListenerSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.sendMessageButton) {
                // Send Message
                Message msg = Message.obtain();
                msg.what = 1;
                msg.arg1 = 23;
                msg.arg2 = 45;
                msg.obj = "Object String";
                handlerThread.getHandler().sendMessage(msg);
                // Alternate way to Send Message with only `what` field
//                handlerThread.getHandler().sendEmptyMessage(1);

            } else if (v.getId() == R.id.sendRefRunnableButton) {
                // Post Runnable, whose reference we have
                handlerThread.getHandler().post(runnable);

            } else if (v.getId() == R.id.sendRunnableButton) {
                // Post Runnable
                // We've used static class, so that it doesn't hold a reference to the MainActivity class, when MainActivity class is destroyed.
                handlerThread.getHandler().post(new ExampleRunnable());
            }
        }
    };

    View.OnClickListener onClickListenerRemove = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Only that Handler can remove Messages/Runnables from MessageQueue,
            // which has sent those Messages/Runnables in the MessageQueue
            if (v.getId() == R.id.removeMessageButton) {
                // To Remove a particular Message Object, we need to pass the value of `what` field
                handlerThread.getHandler().removeMessages(1);

            } else if (v.getId() == R.id.removeRefRunnableButton) {
                // To Remove a particular Runnable Object, we need to pass the same referece to Remove it from the MessageQueue
                handlerThread.getHandler().removeCallbacks(runnable);

            } else if (v.getId() == R.id.removeAllButton) {
                // This will remove all the pending Messages/Runnables from the MessageQueue
                handlerThread.getHandler().removeCallbacksAndMessages(null);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    static class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            Log.d(TAG, "ExampleRunnable -> Started");
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "ExampleRunnable: " + i);
                SystemClock.sleep(1000);
            }
            Log.d(TAG, "ExampleRunnable -> Executed");
        }
    }
}