package io.github.changjiashuai.rxandroiddemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/2/23 21:21.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder>{

    private List<GitHubUser> mUsers;
    private NetworkActivity.UserClickCallback mCallback;

    public UserListAdapter(NetworkActivity.UserClickCallback callback){
        mUsers = new ArrayList<>();
        mCallback = callback;
    }

    public void addUser(GitHubUser user){
        mUsers.add(user);
        notifyItemInserted(mUsers.size()-1);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_network_user, parent, false);
        return new UserViewHolder(item, mCallback);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bindTo(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.network_item_iv_user_picture)
        ImageView mIvUserPicture;
        @Bind(R.id.network_item_iv_repo_name)
        TextView mTvUserName;
        @Bind(R.id.network_item_tv_user_login)
        TextView mTvUserLogin;
        @Bind(R.id.network_item_tv_user_page)
        TextView mTvUserPage;

        public UserViewHolder(View itemView, NetworkActivity.UserClickCallback callback) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> callback.onItemClicked(mTvUserLogin.getText().toString()));
        }

        public void bindTo(GitHubUser user){
            mTvUserName.setText(user.name);
            mTvUserLogin.setText(user.login);
            mTvUserPage.setText(user.repos_url);
            Picasso.with(mIvUserPicture.getContext())
                    .load(user.avatar_url)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .into(mIvUserPicture);
        }
    }

    public static class GitHubUser{
        public String login;
        public String avatar_url;
        public String name;
        public String repos_url;
    }
}
