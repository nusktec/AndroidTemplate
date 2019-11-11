package com.rscbyte.homechurch.objects;

import android.app.Activity;
import android.util.Patterns;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.rscbyte.homechurch.Utils.Constants;
import com.rscbyte.homechurch.Utils.SharedPreferred;
import com.rscbyte.homechurch.Utils.Tools;

public class OSignIn extends BaseObservable {
    private String email = "";
    private String password = "";
    private boolean remember = false;
    private boolean enabled = true;
    private String info = "";

    //constructor
    public OSignIn(String email, String password, boolean remember, boolean enabled) {
        this.email = email;
        this.password = password;
        this.remember = remember;
        this.enabled = enabled;
    }

    //single constructor
    public OSignIn() {
    }

    //single methods

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.notifyPropertyChanged(BR.password);
    }

    @Bindable
    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
        notifyPropertyChanged(BR.remember);
    }

    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (!enabled) {
            info = "Please wait...";
        } else {
            info = "";
        }
        notifyPropertyChanged(BR.info);
        notifyPropertyChanged(BR.enabled);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        notifyPropertyChanged(BR.info);
    }

    //check fields requirement
    public boolean isReady(Activity ctx, boolean showToast) {
        if (email.isEmpty()) {
            if (showToast)
                Tools.showToast(ctx, "Your email is empty...");
            return false;
        }
        if (password.isEmpty()) {
            if (showToast)
                Tools.showToast(ctx, "Your password is empty...");
            return false;
        }
        if (password.length() < 5) {
            if (showToast)
                Tools.showToast(ctx, "Password length too small");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Tools.showToast(ctx, "Invalid email address");
            return false;
        }
        return true;
    }

    //load default values on views that required it
    @BindingAdapter("android:default")
    public static void rememberMeDefault(View v, boolean opt) {
        SharedPreferred preferred = new SharedPreferred(v.getContext());
        preferred.putBoolean(Constants.LOGIN_CHK_VALUE, opt);
    }
}
