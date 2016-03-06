package crusader.nikeshopping;

import android.util.Log;

/**
 * Created by user on 3/6/2016.
 */
public class Utility {

    private static final String TAG = "NikeAppLogs";

    public static void showLog(String loggingData){
        StringBuffer sb = new StringBuffer(loggingData);
        if (sb.length() > 4000) {
            Log.v(TAG, "sb.length = " + sb.length());
            int chunkCount = sb.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    Log.v(TAG, "chunk " + i + " of " + chunkCount + ":" + sb.substring(4000 * i));
                } else {
                    Log.v(TAG, "chunk " + i + " of " + chunkCount + ":" + sb.substring(4000 * i, max));
                }
            }
        } else {
            Log.v(TAG, sb.toString());
        }
    }

}
