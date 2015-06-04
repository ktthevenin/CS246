package com.example.ktaylor.myapplication;

package com.javacodegeeks.android.androidprogressbarexample;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    Button button;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();

    private long fileSize = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        addButtonListener();

    }

    public void addButtonListener() {

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                // create and display a new ProgressBarDialog
                progressBar = new ProgressDialog(view.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("File downloading ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();

                progressBarStatus = 0;

                fileSize = 0;

                new Thread(new Runnable() {

                    public void run() {
                        while (progressBarStatus < 100) {

                            // process some tasks
                            progressBarStatus = downloadFile();

                            // sleep 1 second (simulating a time consuming task...)
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            progressBarbHandler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }

                        // if the file is downloaded,
                        if (progressBarStatus >= 100) {

                            // sleep 2 seconds, so that you can see the 100%
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // and then close the progressbar dialog
                            progressBar.dismiss();
                        }
                    }
                }).start();

            }

        });

    }

    // file download simulator...
    public int downloadFile() {

        while (fileSize <= 1000000) {

            fileSize++;

            if (fileSize == 100000) {
                return 10;

            } else if (fileSize == 200000) {
                return 20;

            } else if (fileSize == 300000) {
                return 30;

            } else if (fileSize == 400000) {
                return 40;

            } else if (fileSize == 500000) {

                return 50;
            } else if (fileSize == 700000) {

                return 70;
            } else if (fileSize == 800000) {

                return 80;
            }

        }

        return 100;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_example);
    }

    public void buttonClick(View view)
    {
        long endTime = System.currentTimeMillis() + 20*1000;

        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
        TextView myTextView =
                (TextView)findViewById(R.id.myTextView);
        myTextView.setText("Button Pressed");
    }

    public void buttonClick(View view)
    {

        Runnable runnable = new Runnable() {
            public void run() {

                long endTime = System.currentTimeMillis()
                        + 20*1000;

                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {}
                    }
                }
            }
        };
        Thread mythread = new Thread(runnable);
        mythread.start();
    }

    public class ThreadExampleActivity extends ActionBarActivity {

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                TextView myTextView =
                        (TextView)findViewById(R.id.myTextView);
                myTextView.setText("Button Pressed");
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_thread_example);
        }

}
