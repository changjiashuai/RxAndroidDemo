package io.github.changjiashuai.rxandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NetworkActivity extends AppCompatActivity {

    @Bind(R.id.rv_network_list)
    RecyclerView mRvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(linearLayoutManager);
        UserListAdapter adapter = new UserListAdapter(this::gotoDetailPage);
        NetworkWrapper.getUsersInfo(adapter);
        mRvList.setAdapter(adapter);
    }

    private void gotoDetailPage(String name){
        startActivity(NetworkDetailActivity.from(this, name));
    }

    public interface UserClickCallback{
        void onItemClicked(String name);
    }
}
