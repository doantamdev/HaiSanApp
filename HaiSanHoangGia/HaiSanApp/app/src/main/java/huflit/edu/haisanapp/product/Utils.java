package huflit.edu.haisanapp.product;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static Bitmap convertCateToBitmapFromAssets(Context context, String nameImage)
    {
        AssetManager assetManager= context.getAssets();
        try {
            InputStream inputStream=assetManager.open("imagecate/"+nameImage);
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Bitmap convertProToBitmapFromAssets(Context context, String nameImage)
    {
        AssetManager assetManager= context.getAssets();
        try {
            InputStream inputStream=assetManager.open("imagepro/"+nameImage);
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
