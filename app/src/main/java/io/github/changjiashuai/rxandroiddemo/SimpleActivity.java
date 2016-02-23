package io.github.changjiashuai.rxandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 一个观察者(Observable),两个订阅者(Subscriber)
 */
public class SimpleActivity extends AppCompatActivity {

    @Bind(R.id.tv_simple_text)
    TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ButterKnife.bind(this);

        // 注册观察活动
        @SuppressWarnings("unchecked")
        Observable<String> observable = Observable.create(mObservableAction);

        // 分发订阅信息
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(mTextSubscriber);
        observable.subscribe(mToastSubscriber);
    }

    /*观察者 事件源*/
    private Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>(){
        @Override
        public void call(Subscriber<? super String> subscriber) {
            // send String
            subscriber.onNext(sayHello());
            // completed event
            subscriber.onCompleted();
        }
    };

    /*订阅者 接收字符串， 修改控件*/
    private Subscriber<String> mTextSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            mTvText.setText(s);
        }
    };

    /*订阅者 接收字符串 提示信息*/
    private Subscriber<String> mToastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(SimpleActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };

    private String sayHello(){
        return "Hello, RxAndroid";
    }
}
