package vn.ray.animation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ray on 10/28/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;

    public abstract int getLayoutId();

    public abstract int fragmentContainerId();

    public void init() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    public void changeFragment(BaseFragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!addBackStack) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            for (int i = 0; i < backStackEntryCount; i++) {
                fragmentManager.popBackStackImmediate();
            }
        }
        transaction.replace(fragmentContainerId(), fragment, fragment.getClass().getSimpleName());
        if (addBackStack)
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }
}
