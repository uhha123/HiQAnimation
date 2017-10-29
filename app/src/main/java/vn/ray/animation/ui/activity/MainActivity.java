package vn.ray.animation.ui.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.ray.animation.R;
import vn.ray.animation.base.BaseActivity;
import vn.ray.animation.base.BaseListener;
import vn.ray.animation.ui.fragment.HomeFragment;

/**
 * Created by Ray on 10/28/17.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bgReveal)
    View bgReveal;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int fragmentContainerId() {
        return R.id.fragmentContainer;
    }

    @Override
    public void init() {
        super.init();
        changeFragment(HomeFragment.newInstance(), false);
    }

    public MainActivity showToolbar(boolean is) {
        toolbar.setVisibility(is ? View.VISIBLE : View.GONE);
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.ivLeft)
    void onLeftClick() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void enterReveal(int cx, int cy, final BaseListener.OnAnimationListener listener) {
        // get the final radius for the clipping circle
        cx = cx + cx / 2;
        cy = cy + cy / 4;
        int finalRadius = Math.max(bgReveal.getWidth(), bgReveal.getHeight()) ;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(bgReveal, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        bgReveal.setVisibility(View.VISIBLE);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (listener!= null)
                    listener.onAnimationListener();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        anim.start();
    }

    public void dismiss() {
        if (bgReveal != null)
            bgReveal.setVisibility(View.GONE);
    }
}
