package charlieandroidblog.esy.net.thread2uithreadcommworking.threads;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import charlieandroidblog.esy.net.thread2uithreadcommworking.InterfaceThreadComm.ActivityCallBack;

/**
 * Created by user on 5/5/17.
 */

public class CommunicationHandler extends Handler {

    ActivityCallBack uiThread;
    Bitmap bitmap;


    public CommunicationHandler(ActivityCallBack mainActivity) {
        this.uiThread = mainActivity;
    }



    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        uiThread.threadCommResults(bitmap);
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

}
