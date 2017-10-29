package vn.ray.animation.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.ray.animation.R;
import vn.ray.animation.base.BaseFragment;
import vn.ray.animation.base.BaseListener;

/**
 * Created by Ray on 10/28/17.
 */

public class CustomPopupMenu<T extends BaseFragment> implements PopupWindow.OnDismissListener {
    @BindView(R.id.ivCancel)
    ImageView ivCancel;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.ivRemove)
    ImageView ivRemove;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.ivSetting)
    ImageView ivSetting;
    @BindView(R.id.ivEdit)
    ImageView ivEdit;
    @BindView(R.id.ivMore)
    ImageView ivMore;
    @BindView(R.id.popupContainer)
    RelativeLayout popupContainer;
    private PopupWindow popupWindow;
    private Context context;
    private WeakReference<T> weakReference;
    private BaseListener.OnPopupMenuListener listener;

    public CustomPopupMenu(T t) {
        weakReference = new WeakReference<T>(t);
        context = weakReference.get().getContext();
        init();
    }

    public CustomPopupMenu setListener(BaseListener.OnPopupMenuListener listener){
        this.listener = listener;
        return this;
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_popup_menu, null, false);
        ButterKnife.bind(this, view);
        popupWindow = new PopupWindow();
        popupWindow = new PopupWindow(
                view,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(this);
    }


    public void showMenu(View view) {
        Rect rc = new Rect();
        view.getWindowVisibleDisplayFrame(rc);
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        rc.offset(xy[0], xy[1]);
        weakReference.get().getMainActivity().enterReveal(xy[0], xy[1], new BaseListener.OnAnimationListener() {
            @Override
            public void onAnimationListener() {

            }
        });
        popupWindow.showAtLocation(view, Gravity.TOP | Gravity.LEFT, rc.left, rc.top);
        popupContainer.animate().setDuration(350).scaleX(1f).scaleY(1.0f).rotation(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        ivCancel.animate().setDuration(500).rotation(-90).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        ivRemove.animate().setDuration(500).rotation(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        ivSetting.animate().setDuration(500).rotation(-90).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        ivEdit.animate().setDuration(500).rotation(-90).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        ivMore.animate().setDuration(500).rotation(-90).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        ivShare.animate().setDuration(500).rotation(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
    }

    @OnClick(R.id.ivCancel)
    void onCancel() {
        popupWindow.dismiss();
    }

    @OnClick(R.id.ivShare)
    void onShare() {
        popupWindow.dismiss();
    }

    @OnClick(R.id.ivRemove)
    void ivRemove() {
        if (listener!= null)
            listener.onRemove();
        popupWindow.dismiss();
    }

    @OnClick(R.id.ivSetting)
    void ivSetting() {
        popupWindow.dismiss();
    }

    @OnClick(R.id.ivEdit)
    void ivEdit() {
        popupWindow.dismiss();
    }

    @OnClick(R.id.ivMore)
    void ivMore() {
        popupWindow.dismiss();
    }

    @Override
    public void onDismiss() {
        weakReference.get().getMainActivity().dismiss();
    }
}
