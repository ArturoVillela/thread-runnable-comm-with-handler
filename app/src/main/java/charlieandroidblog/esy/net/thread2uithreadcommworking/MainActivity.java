package charlieandroidblog.esy.net.thread2uithreadcommworking;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import charlieandroidblog.esy.net.thread2uithreadcommworking.InterfaceThreadComm.ActivityCallBack;
import charlieandroidblog.esy.net.thread2uithreadcommworking.threads.CommunicationHandler;
import charlieandroidblog.esy.net.thread2uithreadcommworking.threads.RunnableDownloadImagen;
import charlieandroidblog.esy.net.thread2uithreadcommworking.threads.ThreadDownloadImagen;

public class MainActivity extends AppCompatActivity implements ActivityCallBack {

    private ImageView imageView;
    private ProgressDialog progressDialog;
    Button btnStart;
    CommunicationHandler commHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);

        btnStart = (Button) findViewById(R.id.button1);

        commHandler = new CommunicationHandler(this);


        //this one runs the thread class
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btnStart.setEnabled(false);
//                progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading..");
//                ThreadDownloadImagen thread = new ThreadDownloadImagen(commHandler);
//                thread.start();
//            }
//        });

        //this one runs the runnable class
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setEnabled(false);
                progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading..");
                RunnableDownloadImagen runnable = new RunnableDownloadImagen(commHandler);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }

    @Override
    public void threadCommResults(Bitmap bitmap) {
        progressDialog.dismiss();
        imageView.setVisibility(View.VISIBLE);
        btnStart.setEnabled(true);
        imageView.setImageBitmap(bitmap);
    }
}
