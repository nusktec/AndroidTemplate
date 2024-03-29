package com.rscbyte.homechurch.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.gms.maps.GoogleMap;
import com.rscbyte.homechurch.R;
import com.rscbyte.homechurch.activities.Dashboard;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {
    public static void setSystemBarColor(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static void setSystemBarColorDialog(Context act, Dialog dialog, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = dialog.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static void setSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void setSystemBarLightDialog(Dialog dialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = dialog.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void clearSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = act.getWindow();
            window.setStatusBarColor(ContextCompat.getColor(act, R.color.colorPrimaryDark));
        }
    }

    /**
     * Making notification bar transparent
     */
    public static void setSystemBarTransparent(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static String getFormattedDateSimple(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return newFormat.format(new Date(dateTime));
    }

    public static String getFormattedDateEvent(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("EEE, MMM dd yyyy");
        return newFormat.format(new Date(dateTime));
    }

    public static String getFormattedTimeEvent(Long time) {
        SimpleDateFormat newFormat = new SimpleDateFormat("h:mm a");
        return newFormat.format(new Date(time));
    }

    public static String getEmailFromName(String name) {
        if (name != null && !name.equals("")) {
            String email = name.replaceAll(" ", ".").toLowerCase().concat("@mail.com");
            return email;
        }
        return name;
    }

    public static int dpToPx(Context c, int dp) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static GoogleMap configActivityMaps(GoogleMap googleMap) {
        // set map type
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Enable / Disable zooming controls
        googleMap.getUiSettings().setZoomControlsEnabled(false);

        // Enable / Disable Compass icon
        googleMap.getUiSettings().setCompassEnabled(true);
        // Enable / Disable Rotate gesture
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        // Enable / Disable zooming functionality
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        return googleMap;
    }

    public static void copyToClipboard(Context context, String data) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("clipboard", data);
        assert clipboard != null;
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    public static String getFromClipBoard(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        try {
            if (clipboard != null) {
                return clipboard.getText().toString();
            }
        } catch (NullPointerException np) {
        }
        return "";
    }

    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(new Runnable() {
            @Override
            public void run() {
                nested.scrollTo(500, targetView.getBottom());
            }
        });
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    public static boolean toggleArrow(boolean show, View view) {
        return toggleArrow(show, view, true);
    }

    public static boolean toggleArrow(boolean show, View view, boolean delay) {
        if (show) {
            view.animate().setDuration(delay ? 200 : 0).rotation(180);
            return true;
        } else {
            view.animate().setDuration(delay ? 200 : 0).rotation(0);
            return false;
        }
    }

    public static void changeNavigateionIconColor(Toolbar toolbar, @ColorInt int color) {
        Drawable drawable = toolbar.getNavigationIcon();
        drawable.mutate();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    public static void changeMenuIconColor(Menu menu, @ColorInt int color) {
        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable == null) continue;
            drawable.mutate();
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void changeOverflowMenuIconColor(Toolbar toolbar, @ColorInt int color) {
        try {
            Drawable drawable = toolbar.getOverflowIcon();
            drawable.mutate();
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        } catch (Exception e) {
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static String toCamelCase(String input) {
        input = input.toLowerCase();
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    public static String insertPeriodically(String text, String insert, int period) {
        StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length() / period) + 1);
        int index = 0;
        String prefix = "";
        while (index < text.length()) {
            builder.append(prefix);
            prefix = insert;
            builder.append(text, index, Math.min(index + period, text.length()));
            index += period;
        }
        return builder.toString();
    }


    public static void rateAction(Activity activity) {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }

    public static String getMarketLink(Activity activity) {
        return "http://play.google.com/store/apps/details?id=" + activity.getPackageName();
    }

    /**
     * Break long sentence and capitalize each
     *
     * @param words
     * @return
     * @throws IndexOutOfBoundsException
     */
    public static String capsWords(String words) throws IndexOutOfBoundsException {
        String[] wordsSplit = words.split(" ");
        StringBuilder rebuildWords = new StringBuilder();
        for (String wd : wordsSplit) {
            rebuildWords.append(String.valueOf(wd.substring(0, 1).toUpperCase() + wd.substring(1) + " "));
        }
        return rebuildWords.toString();
    }

    /**
     * Campitalize first latter
     *
     * @param str string
     * @return
     * @throws IndexOutOfBoundsException
     */
    public static String capsfLetter(String str) throws IndexOutOfBoundsException {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Generate random number with custom length
     *
     * @param length length of string
     * @return
     */
    public static String getRandomStr(int length) {
        int lenght = length;
        if (lenght > 36) {
            lenght = 36;
        }
        String STR_DATA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int MAX_INT = STR_DATA.length() - 1;
        int MIN_INT = 1;
        StringBuilder STR_HOLDER = new StringBuilder();
        for (int i = 0; i < length; i++) {
            STR_HOLDER.append(STR_DATA.charAt(new Random().nextInt(MAX_INT) + MIN_INT));
        }
        return STR_HOLDER.toString();
    }

    @SuppressLint("DefaultLocale")
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public static int getMonthAsscNumeric(String sdate) {
        if (sdate.contains("January"))
            return 1;
        if (sdate.contains("February"))
            return 2;
        if (sdate.contains("March"))
            return 3;
        if (sdate.contains("April"))
            return 4;
        if (sdate.contains("May"))
            return 5;
        if (sdate.contains("June"))
            return 6;
        if (sdate.contains("July"))
            return 7;
        if (sdate.contains("August"))
            return 8;
        if (sdate.contains("September"))
            return 9;
        if (sdate.contains("October"))
            return 10;
        if (sdate.contains("November"))
            return 11;
        if (sdate.contains("December"))
            return 12;
        return 1;
    }

    /**
     * Format current
     *
     * @param amount
     * @return
     */
    public static String doCuurency(double amount) {
        return String.format("%,.0f", (double) amount);
    }

    /**
     * Share text to external
     *
     * @param activity
     * @param subject
     * @param body
     */
    public static void shareText(Activity activity, String subject, String body) {
        Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
        txtIntent.setType("text/plain");
        txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        activity.startActivity(Intent.createChooser(txtIntent, "Share"));
    }

    /**
     * Support gradient header
     *
     * @param activity Do not supply activity is null, already instantiated activity will be fine
     */
    public static void supportGradientHeader(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = activity.getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    public static void supportGradientHeader(Activity activity, Boolean aBoolean) {
        Window w = activity.getWindow();
        w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (aBoolean) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                w.setNavigationBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
            }
        }
    }

    public interface aniTypeSeq {
        void precedesChar(String string);
    }

    //Enable strict mode
    public static void enabledStrickMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    //public please wait
    public static void pleaseWait(int timeMS, final OnWait onWait) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onWait.onComplete();
            }
        }, timeMS);
    }

    public interface OnWait {
        void onComplete();
    }

    //Bitmap conversions
    public static String encodeTobase64(Bitmap image, EncodedListener encodedListener) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        encodedListener.converted(imageEncoded);
        return imageEncoded;
    }

    public interface EncodedListener {
        void converted(String base64);
    }

    //Bitmap conversions
    public static String encodeTobase64(String image, ImageListener listener) {
        InputStream inputStream = null;
        String encodedFile = "", lastVal;
        try {
            inputStream = new FileInputStream(image);

            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile = output.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        lastVal = encodedFile;
        listener.onFinished(true, lastVal);
        return image;
    }

    public interface ImageListener {
        void onFinished(boolean status, String sbitmap);
    }

    public interface BitMapListener {
        void onBitMap(Bitmap img);
    }

    //Bitmap constructor
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        return bitmap;
    }

    public static Bitmap decodeBase64(String input, BitMapListener listener) {
        byte[] decodedByte = Base64.decode(input, 0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
        listener.onBitMap(bitmap);
        return bitmap;
    }

    public static Bitmap bitMapFromUri(Activity ctx, Uri uri) {
        InputStream imageStream = null;
        try {
            imageStream = ctx.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(imageStream);
    }

    //Circle image
    public static Bitmap getCircleImage(Bitmap bitmap) {
        try {
            final int width = bitmap.getWidth();
            final int height = bitmap.getHeight();
            final Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            final Path path = new Path();
            path.addCircle(
                    (float) (width / 2)
                    , (float) (height / 2)
                    , (float) Math.min(width, (height / 2))
                    , Path.Direction.CCW);

            final Canvas canvas = new Canvas(outputBitmap);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap, 0, 0, null);
            return outputBitmap;
        } catch (NullPointerException np) {
            //keep my secrete
        }
        return null;
    }

    //Notification Service
    public static int Notification(Context context, String title, String small_title, String message, int id, Class aClass, String meta_data) {
        Bitmap largeIcon = Tools.getCircleImage(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            largeIcon = Tools.getCircleImage(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Logic for platforms
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final String noti_id = String.valueOf(id);
            final CharSequence noti_name = context.getResources().getString(R.string.app_name);
            final int noti_importance = NotificationManager.IMPORTANCE_HIGH;

            //Apply it to a channel
            NotificationChannel notificationChannel = new NotificationChannel(noti_id, noti_name, noti_importance);
            notificationChannel.setDescription("It's your money");
            notificationChannel.setShowBadge(true);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }

        PendingIntent pendingIntent = null;
        if (aClass != null) {
            Intent intent = new Intent(context, aClass).putExtra("meta-data", meta_data);
            // Creating a pending intent and wrapping our intent
            pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            Intent intent = new Intent(context, Dashboard.class);
            // Creating a pending intent and wrapping our intent
            pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        //Now General Notification setup
        Notification.Builder notification = new Notification.Builder(context);
        notification.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setSubText(small_title)
                .setStyle(new Notification.BigTextStyle().bigText(message))
                .setContentText(message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(String.valueOf(id));
        }
        notification.setContentIntent(pendingIntent);
        assert notificationManager != null;
        notificationManager.notify(id, notification.build());

        return id;
    }

    /**
     * @param act
     * @param msg
     */
    public static void showToast(Activity act, String msg) {
        View layout = act.getLayoutInflater().inflate(R.layout.util_custom_tool, (ViewGroup) act.findViewById(R.id.custom_toast_layout_id));
        TextView text = layout.findViewById(R.id.text);
        text.setText(msg);
        Toast toast = new Toast(act.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static Dialog warningDialog(Activity act, String msg, Drawable iconSrc, boolean touchCancel, View.OnClickListener callBack) {
        final Dialog dialog = new Dialog(act);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_add_data);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(touchCancel);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.findViewById(R.id.bt_close).setOnClickListener(callBack);

        dialog.show();
        dialog.getWindow().setAttributes(lp);

        return dialog;
    }

}
