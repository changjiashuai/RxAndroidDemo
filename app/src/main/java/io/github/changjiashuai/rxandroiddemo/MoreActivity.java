package io.github.changjiashuai.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class MoreActivity extends AppCompatActivity {

    @Bind(R.id.tv_simple_text)
    TextView mTvText;

    String[] mManyWords = {"Hello", "I", "am", "RxAndroid"};
    List<String> mManyWordList = Arrays.asList(mManyWords);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        Observable<String> obShow = Observable.just(sayHello());
        obShow.observeOn(AndroidSchedulers.mainThread())
                .map(mUpperLetterFunc)
                .subscribe(mTextViewAction);

        Observable<String> obMap = Observable.from(mManyWords);
        obMap.observeOn(AndroidSchedulers.mainThread())
                .map(mUpperLetterFunc)
                .subscribe(mToastAction);

        Observable.just(mManyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(mOneLetterFunc)
                .reduce(mMergeStringFunc)
                .subscribe(mToastAction);
    }

    private Action1<String> mTextViewAction = new Action1<String>() {
        @Override
        public void call(String s) {
            mTvText.setText(s);
        }
    };

    private Action1<String> mToastAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(MoreActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };

    private Func1<List<String>, Observable<String>> mOneLetterFunc = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> strings) {
            return Observable.from(strings);
        }
    };

    private Func1<String, String> mUpperLetterFunc = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };

    private Func2<String, String, String> mMergeStringFunc = new Func2<String, String, String>() {
        @Override
        public String call(String s, String s2) {
            return String.format("%s %s", s, s2);
        }
    };

    private String sayHello(){
        return "Hello, RxAndroid";
    }
}
