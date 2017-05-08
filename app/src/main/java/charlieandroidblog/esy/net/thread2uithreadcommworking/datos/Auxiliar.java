package charlieandroidblog.esy.net.thread2uithreadcommworking.datos;

import android.util.Log;

import java.util.Random;

/**
 * Created by user on 5/8/17.
 */

public class Auxiliar {

    private final static String url1 = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTLiYWSfX_Ff80PkLoguqWQT1egTj5VheNiHWavztYAoy0hyyIN";
    private final static String url2 = "https://lh3.googleusercontent.com/-R7YKKkZQi_k/VROmrQ9nPfI/AAAAAAAAWRQ/4spwib1rAlI/s512/Jennifer%252520Connelly%252520Images%25252010.JPG";
    private final static String url3 = "https://pbs.twimg.com/profile_images/571760451222921216/wYjnMQ3K.jpeg";
    private final static String url4 = "https://images2.alphacoders.com/219/219452.jpg";

    private final static String TAG="Auxiliar";

    public static String getImageURL(){
        Random rand = new Random();

        int  n = rand.nextInt(4);
        Log.d(TAG,"numero random : "+n);
        switch (n){
            case 1: return url1;
            case 2: return url2;
            case 3: return url3;
            default: return url4;
        }
    }
}
