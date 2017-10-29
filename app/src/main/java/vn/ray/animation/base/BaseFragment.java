package vn.ray.animation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import vn.ray.animation.ui.activity.MainActivity;

/**
 * Created by Ray on 10/28/17.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private BaseActivity activity;
    public CompositeDisposable compositeDisposable;

    public abstract int getLayoutId();

    public void init() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (BaseActivity) getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        init();
    }

    @Override
    public void onDestroyView() {
        destroyView();
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable.dispose();
        }
    }

    public void destroyView() {

    }

    public MainActivity getMainActivity() {
        if (activity instanceof MainActivity)
            return (MainActivity) activity;
        return null;
    }

    public void changeFragment(BaseFragment fragment, boolean addBackStack) {
        activity.changeFragment(fragment, addBackStack);
    }
}
