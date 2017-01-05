package me.rumengzhenxing.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

/**
 * @author h.yw
 * @version 1.0
 * @time：16-12-30:下午3:31
 * @email rumengzhenxing@gmail.com
 * @description ： 加载中view
 */
public class ProgressView extends BasePopupWindow {

    GifView gifView;


    public ProgressView(Activity context) {
        super(context);
        gifView = (GifView) mPopupView.findViewById(R.id.progress_gif);
        gifView.setGifResource(R.mipmap.gif_loading);
        mPopupView.setOnKeyListener(new View.OnKeyListener() {
            @Override public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return true;
            }
        });
    }


    @Override public void showPopupWindow() {
        onPlay();
        super.showPopupWindow();
    }


    @Override public void dismiss() {
        onPause();
        super.dismiss();
    }


    public void setGifResource(@IdRes int mipmap) {
        if (gifView != null) gifView.setGifResource(mipmap);
    }


    public void onPlay() {
        if (gifView != null) gifView.play();
    }


    public void onPause() {
        if (gifView != null && gifView.isPlaying()) gifView.pause();
    }


    @Override protected Animation getShowAnimation() {
        return null;
    }


    @Override
    public Animator getShowAnimator() {
        return getDefaultSlideFromBottomAnimationSet();
    }


    @Override
    public Animator getExitAnimator() {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();
            if (getAnimaView() != null) {
                set.playTogether(
                    ObjectAnimator.ofFloat(getAnimaView(), "translationY", 0, 250).setDuration(400),
                    ObjectAnimator.ofFloat(getAnimaView(), "alpha", 1, 0.4f)
                        .setDuration(250 * 3 / 2));
            }
        }
        return set;
    }


    @Override protected View getClickToDismissView() {
        return null;
    }


    @Override public View getPopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout.view_progress, null);
    }


    @Override public View getAnimaView() {
        return gifView;
    }
}
