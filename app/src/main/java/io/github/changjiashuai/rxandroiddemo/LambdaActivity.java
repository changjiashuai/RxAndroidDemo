package io.github.changjiashuai.rxandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class LambdaActivity extends AppCompatActivity {

    @Bind(R.id.tv_simple_text)
    TextView mTvText;
    String[] mManyWords = {"Hello", "I", "am", "RxAndroid"};
    List<String> mManyWordsList = Arrays.asList(mManyWords);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        ButterKnife.bind(this);

        Observable<String> obShow = Observable.just(sayHello());
        obShow.observeOn(AndroidSchedulers.mainThread())
                .map(String::toUpperCase)
                .subscribe(mTvText::setText);

        Observable<String> obMap = Observable.from(mManyWords);
        obMap.observeOn(AndroidSchedulers.mainThread())
                .map(String::toUpperCase)
                .subscribe(this::showToast);

        Observable.just(mManyWordsList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::from)
                .reduce(this::mergeString)
                .subscribe(this::showToast);
    }

    private String sayHello(){
        return "Hello, I am RxAndroid";
    }

    private void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private String mergeString(String s1, String s2){
        return String.format("%s %s", s1, s2);
    }
}