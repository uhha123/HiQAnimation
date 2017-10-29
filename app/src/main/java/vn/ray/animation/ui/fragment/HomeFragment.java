package vn.ray.animation.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import vn.ray.animation.R;
import vn.ray.animation.adapter.FileAdapter;
import vn.ray.animation.base.BaseFragment;
import vn.ray.animation.base.BaseListener;
import vn.ray.animation.constant.Constant;
import vn.ray.animation.constant.DataFactory;
import vn.ray.animation.model.FileModel;
import vn.ray.animation.util.Utils;

/**
 * Created by Ray on 10/28/17.
 */

public class HomeFragment extends BaseFragment implements Constant, BaseListener.OnItemClickedListener<FileModel> {

    @BindView(R.id.ivMain)
    ImageView ivMain;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.rvFile)
    RecyclerView rvFile;

    private FileAdapter adapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        super.init();
        if (!isAdded())
            return;
        Glide.with(getContext()).load(MAIN_IMAGE_URL).apply(RequestOptions.centerCropTransform()).into(ivMain);
        getMainActivity().showToolbar(false);

        if (adapter == null) {
            adapter = new FileAdapter();
            adapter.setList(DataFactory.getFiles());
        }
        adapter.setListener(this);
        rvFile.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvFile.setAdapter(adapter);

        compositeDisposable.add(Flowable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return Utils.timeFormat(new Date().getTime());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        tvTime.setText(s + "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }));
    }

    @Override
    public void onItemClickedListener(View view, FileModel fileModel, int position) {
        changeFragment(DetailFragment.newInstance(fileModel.getTitle()), true);
    }
}
