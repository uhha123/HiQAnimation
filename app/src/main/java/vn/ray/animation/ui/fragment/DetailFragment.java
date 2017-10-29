package vn.ray.animation.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.ray.animation.R;
import vn.ray.animation.adapter.DetailFileAdapter;
import vn.ray.animation.adapter.FileAdapter;
import vn.ray.animation.base.BaseFragment;
import vn.ray.animation.base.BaseListener;
import vn.ray.animation.constant.DataFactory;
import vn.ray.animation.model.FileModel;
import vn.ray.animation.widget.CustomPopupMenu;

/**
 * Created by Ray on 10/28/17.
 */

public class DetailFragment extends BaseFragment implements BaseListener.OnItemClickedListener<FileModel> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.rvDetail)
    RecyclerView rvDetail;
    private String title = "";


    private DetailFileAdapter adapter;

    public static DetailFragment newInstance(String title) {
        DetailFragment fragment = new DetailFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public void init() {
        super.init();
        if (!isAdded())
            return;
        getMainActivity().showToolbar(true);
        tvTitle.setText(title);
        if (adapter == null) {
            adapter = new DetailFileAdapter();
            adapter.setList(DataFactory.getFiles());
        }
        adapter.setListener(this);
        rvDetail.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvDetail.setAdapter(adapter);
    }

    @Override
    public void onItemClickedListener(View view, FileModel fileModel, final int position) {

        adapter.setAnimationId(android.R.anim.slide_in_left);
        CustomPopupMenu customPopupMenu = new CustomPopupMenu(this);
        customPopupMenu.setListener(new BaseListener.OnPopupMenuListener() {
            @Override
            public void onRemove() {
                adapter.remove(position);
            }

            @Override
            public void onShare() {

            }
        }).showMenu(view);
    }

    @Override
    public void destroyView() {
        super.destroyView();
        if (adapter != null)
            adapter.setAnimationId(R.anim.slide_to_up);
    }
}
