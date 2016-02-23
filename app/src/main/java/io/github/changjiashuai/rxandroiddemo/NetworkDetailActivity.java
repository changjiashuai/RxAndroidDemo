package io.github.changjiashuai.rxandroiddemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NetworkDetailActivity extends AppCompatActivity {

    private static final String USER_KEY = "network_detail_activity.user";
    @Bind(R.id.rv_network_item_detail)RecyclerView mRvItemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_detail);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvItemDetail.setLayoutManager(linearLayoutManager);
        RepoListAdapter adapter = new RepoListAdapter();
        NetworkWrapper.getRepoInfo(getIntent().getStringExtra(USER_KEY), adapter);
        mRvItemDetail.setAdapter(adapter);
    }

    public static Intent from(Context context, String username){
        Intent intent = new Intent(context, NetworkDetailActivity.class);
        intent.putExtra(USER_KEY, username);
        return intent;
    }
}
