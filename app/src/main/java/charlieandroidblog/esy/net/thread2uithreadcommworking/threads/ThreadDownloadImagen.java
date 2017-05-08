package charlieandroidblog.esy.net.thread2uithreadcommworking.threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

import charlieandroidblog.esy.net.thread2uithreadcommworking.datos.Auxiliar;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by user on 5/5/17.
 */

public class ThreadDownloadImagen extends Thread  {

    private String url = Auxiliar.getImageURL();
    private Bitmap bitmap = null;
    private String TAG = "ThreadDownloadImagen";
    private String GROUP_TAG = "charlito";

    CommunicationHandler commHandler;

    public ThreadDownloadImagen(CommunicationHandler commHandler) {
        this.commHandler = commHandler;
    }

    @Override
    public void run() {
        bitmap = downloadBitmap(url);
        commHandler.setBitmap(bitmap);
        commHandler.sendEmptyMessage(0);
    }

    private Bitmap downloadBitmap(String url) {
        // Initialize the default HTTP client object
        final DefaultHttpClient client = new DefaultHttpClient();

        //forming a HttpGet request
        final HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse response = client.execute(getRequest);
            //check 200 OK for success
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Log.d(TAG, "Error " + statusCode + " while retrieving bitmap from " + url);
                return null;
            }
            Log.d(TAG,"got the image..");

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    // getting contents from the stream
                    inputStream = entity.getContent();
                    // decoding stream data back into image Bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Log.d(TAG,"created the bitmap image...");
                    return bitmap;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            getRequest.abort();
            Log.d(TAG, "Error "+ e.toString());
        }
        return null;
    }
}
