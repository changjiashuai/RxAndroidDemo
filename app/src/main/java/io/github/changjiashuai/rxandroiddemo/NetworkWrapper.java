package io.github.changjiashuai.rxandroiddemo;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/2/23 21:34.
 */
public class NetworkWrapper {
    private static final String[] mFamousUsers = {
            "changjiashuai", "JakeWharton", "rock3r", "Takhion", "dextorer", "Mariuxtheone"
    };

    public static void getUsersInfo(UserListAdapter adapter) {
        GitHubService gitHubService = ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);
        Observable.from(mFamousUsers)
                .flatMap(gitHubService::getUserData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::addUser);
    }

    public static void getRepoInfo(String username, RepoListAdapter adapter) {
        GitHubService gitHubService = ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);
        gitHubService.getRepoData(username)
                .flatMap(Observable::from)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::addRepo);
    }

}
