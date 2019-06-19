package com.fzzz.mydemo.ui.rxjava;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.TimeUtil;
import com.fzzz.mydemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-20
 * update:
 */
@Route(path = Constants.PATH_APP_RXJAVA)
public class RxJavaActivity extends BaseActivity {
    public static final String TAG = "RxJavaActivity";

    @BindView(R.id.content)
    TextView content;

    private Disposable mDisposable;

    @Override
    public int getLayoutID() {
        return R.layout.activity_rxjava;
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9,
            R.id.bt10, R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15, R.id.bt16, R.id.bt17,
            R.id.bt18, R.id.bt19, R.id.bt20, R.id.bt21, R.id.bt22, R.id.bt23, R.id.bt24})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                test1();
                break;
            case R.id.bt2:
                test2();
                break;
            case R.id.bt3:
                test3();
                break;
            case R.id.bt4:
                test4();
                break;
            case R.id.bt5:
                test5();
                break;
            case R.id.bt6:
                test6();
                break;
            case R.id.bt7:
                test7();
                break;
            case R.id.bt8:
                test8();
                break;
            case R.id.bt9:
                test9();
                break;
            case R.id.bt10:
                test10();
                break;
            case R.id.bt11:
                test11();
                break;
            case R.id.bt12:
                test12();
                break;
            case R.id.bt13:
                test13();
                break;
            case R.id.bt14:
                test14();
                break;
            case R.id.bt15:
                test15();
                break;
            case R.id.bt16:
                test16();
                break;
            case R.id.bt17:
                test17();
                break;
            case R.id.bt18:
                test18();
                break;
            case R.id.bt19:
                test19();
                break;
            case R.id.bt20:
                test20();
                break;
            case R.id.bt21:
                test21();
                break;
            case R.id.bt22:
                test22();
                break;
            case R.id.bt23:
                test23();
                break;
            case R.id.bt24:
                test24();
                break;

        }
    }

    /**
     * 2019-05-21 15:54:02.490 19564-19564/com.fzzz.mydemo I/System.out: 1
     * 2019-05-21 15:54:02.490 19564-19564/com.fzzz.mydemo I/System.out: 2
     * 2019-05-21 15:54:02.490 19564-19564/com.fzzz.mydemo I/System.out: 3
     */
    private void test1() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        });
        mDisposable = observable.subscribe(System.out::println);
        //等价
        Observable.just(1, 2, 3, 4).subscribe(System.out::println);
    }

    /**
     * 2019-05-21 15:53:48.127 19564-19564/com.fzzz.mydemo E/RxJavaActivity: onSubscribe : false
     * 2019-05-21 15:53:48.128 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Observable emit 1
     * 2019-05-21 15:53:48.128 19564-19564/com.fzzz.mydemo E/RxJavaActivity: onNext : value : 1
     * 2019-05-21 15:53:48.129 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Observable emit 2
     * 2019-05-21 15:53:48.130 19564-19564/com.fzzz.mydemo E/RxJavaActivity: onNext : value : 2
     * 2019-05-21 15:53:48.131 19564-19564/com.fzzz.mydemo E/RxJavaActivity: onNext : isDisposable : true
     * 2019-05-21 15:53:48.133 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Observable emit 3
     * 2019-05-21 15:53:48.134 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Observable emit 4
     * <p>
     * 在发射事件中，我们在发射了数值 3 之后，直接调用了 e.onComlete()，虽然无法接收事件，但发送事件还是继续的。
     * 另外一个值得注意的点是，在 RxJava 2.x 中，可以看到发射事件方法相比 1.x 多了一个 throws Excetion，意味着我们做一些特定操作再也不用 try-catch 了。
     * 并且 2.x 中有一个 Disposable 概念，这个东西可以直接调用切断，可以看到，当它的 isDisposed() 返回为 false 的时候，接收器能正常接收事件，
     * 但当其为 true 的时候，接收器停止了接收。所以可以通过此参数动态控制接收事件了。
     */
    private void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                content.append("Observable emit 1" + "\n");
                Log.e(TAG, "Observable emit 1" + "\n");
                emitter.onNext(1);
                content.append("Observable emit 2" + "\n");
                Log.e(TAG, "Observable emit 2" + "\n");
                emitter.onNext(2);
                content.append("Observable emit 3" + "\n");
                Log.e(TAG, "Observable emit 3" + "\n");
                emitter.onNext(3);
                emitter.onComplete();
                content.append("Observable emit 4" + "\n");
                Log.e(TAG, "Observable emit 4" + "\n");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                content.append("onSubscribe : " + d.isDisposed() + "\n");
                Log.e(TAG, "onSubscribe : " + d.isDisposed() + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                content.append("onNext : value : " + integer + "\n");
                Log.e(TAG, "onNext : value : " + integer + "\n");
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                    content.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                    Log.e(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(Throwable e) {
                content.append("onError : value : " + e.getMessage() + "\n");
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                content.append("onComplete" + "\n");
                Log.e(TAG, "onComplete" + "\n");
            }
        });
    }

    /**
     * 2019-05-21 15:53:36.084 19564-19564/com.fzzz.mydemo E/RxJavaActivity: accept : This is result 1
     * 2019-05-21 15:53:36.085 19564-19564/com.fzzz.mydemo E/RxJavaActivity: accept : This is result 2
     * 2019-05-21 15:53:36.087 19564-19564/com.fzzz.mydemo E/RxJavaActivity: accept : This is result 3
     * <p>
     * map 基本作用就是将一个 Observable 通过某种函数关系，转换为另一种 Observable，上面例子中就是把我们的 Integer 数据变成了 String 类型
     */
    private void test3() {
        mDisposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                content.append("accept : " + s + "\n");
                Log.e(TAG, "accept : " + s + "\n");
            }
        });
    }

    /**
     * 2019-05-21 15:52:08.706 19564-19564/com.fzzz.mydemo E/RxJavaActivity: String emit : A
     * 2019-05-21 15:52:08.707 19564-19564/com.fzzz.mydemo E/RxJavaActivity: String emit : B
     * 2019-05-21 15:52:08.708 19564-19564/com.fzzz.mydemo E/RxJavaActivity: String emit : C
     * 2019-05-21 15:52:08.708 19564-19564/com.fzzz.mydemo E/RxJavaActivity: zip : accept : A1
     * 2019-05-21 15:52:08.709 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Integer emit : 1
     * 2019-05-21 15:52:08.710 19564-19564/com.fzzz.mydemo E/RxJavaActivity: zip : accept : B2
     * 2019-05-21 15:52:08.710 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Integer emit : 2
     * 2019-05-21 15:52:08.711 19564-19564/com.fzzz.mydemo E/RxJavaActivity: zip : accept : C3
     * 2019-05-21 15:52:08.712 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Integer emit : 3
     * 2019-05-21 15:52:08.713 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Integer emit : 4
     * 2019-05-21 15:52:08.714 19564-19564/com.fzzz.mydemo E/RxJavaActivity: Integer emit : 5
     * <p>
     * zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，并且一个事件只能被使用一次，
     * 组合的顺序是严格按照事件发送的顺序来进行的，
     * 所以上面截图中，可以看到，1 永远是和 A 结合的，2 永远是和 B 结合的。
     * 最终接收器收到的事件数量是和发送器发送事件最少的那个发送器的发送事件数目相同
     */
    private void test4() {
        mDisposable = Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, Object>() {
            @Override
            public Object apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                String s = (String) o;
                content.append("zip : accept : " + s + "\n");
                Log.e(TAG, "zip : accept : " + s + "\n");
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext("A");
                    content.append("String emit : A \n");
                    Log.e(TAG, "String emit : A \n");
                    emitter.onNext("B");
                    content.append("String emit : B \n");
                    Log.e(TAG, "String emit : B \n");
                    emitter.onNext("C");
                    content.append("String emit : C \n");
                    Log.e(TAG, "String emit : C \n");
                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(1);
                    content.append("Integer emit : 1 \n");
                    Log.e(TAG, "Integer emit : 1 \n");
                    emitter.onNext(2);
                    content.append("Integer emit : 2 \n");
                    Log.e(TAG, "Integer emit : 2 \n");
                    emitter.onNext(3);
                    content.append("Integer emit : 3 \n");
                    Log.e(TAG, "Integer emit : 3 \n");
                    emitter.onNext(4);
                    content.append("Integer emit : 4 \n");
                    Log.e(TAG, "Integer emit : 4 \n");
                    emitter.onNext(5);
                    content.append("Integer emit : 5 \n");
                    Log.e(TAG, "Integer emit : 5 \n");
                }
            }
        });
    }

    /**
     * 2019-05-21 16:05:35.651 19884-19884/com.fzzz.mydemo E/RxJavaActivity: concat : 1
     * 2019-05-21 16:05:35.651 19884-19884/com.fzzz.mydemo E/RxJavaActivity: concat : 2
     * 2019-05-21 16:05:35.652 19884-19884/com.fzzz.mydemo E/RxJavaActivity: concat : 3
     * 2019-05-21 16:05:35.654 19884-19884/com.fzzz.mydemo E/RxJavaActivity: concat : 4
     * 2019-05-21 16:05:35.655 19884-19884/com.fzzz.mydemo E/RxJavaActivity: concat : 5
     * 2019-05-21 16:05:35.656 19884-19884/com.fzzz.mydemo E/RxJavaActivity: concat : 6
     * <p>
     * 发射器 B 把自己的三个孩子送给了发射器 A，让他们组合成了一个新的发射器，有条不紊的排序接收。
     */
    private void test5() {
        mDisposable = Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("concat : " + integer + "\n");
                        Log.e(TAG, "concat : " + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-21 16:57:28.378 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 3
     * 2019-05-21 16:57:28.379 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 1
     * 2019-05-21 16:57:28.380 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 3
     * 2019-05-21 16:57:28.381 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 3
     * 2019-05-21 16:57:28.383 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 1
     * 2019-05-21 16:57:28.384 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 1
     * 2019-05-21 16:57:28.384 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 2
     * 2019-05-21 16:57:28.387 20894-20894/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 2
     * <p>
     * 采用一个随机数，生成一个时间，然后通过 delay（后面会讲）操作符，做一个小延时操作，而查看 Log 日志也确认验证了我们上面的说法，它是无序的。
     */
    private void test6() {
        mDisposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        content.append("flatMap : accept : " + s + "\n");
                        Log.e(TAG, "flatMap : accept : " + s + "\n");
                    }
                });
    }

    /**
     * 2019-05-21 17:01:41.769 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 1
     * 2019-05-21 17:01:41.770 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 1
     * 2019-05-21 17:01:41.770 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 1
     * 2019-05-21 17:01:41.771 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 2
     * 2019-05-21 17:01:41.772 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 2
     * 2019-05-21 17:01:41.772 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 2
     * 2019-05-21 17:01:41.773 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 3
     * 2019-05-21 17:01:41.773 21030-21030/com.fzzz.mydemo I/chatty: uid=10131(com.fzzz.mydemo) identical 1 line
     * 2019-05-21 17:01:41.774 21030-21030/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : I am value 3
     * <p>
     * 和flatMap一致，但这是有序的
     */
    private void test7() {
        mDisposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        content.append("flatMap : accept : " + s + "\n");
                        Log.e(TAG, "flatMap : accept : " + s + "\n");
                    }
                });
    }

    /**
     * 2019-05-21 17:15:32.611 21616-21616/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 1
     * 2019-05-21 17:15:32.611 21616-21616/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 2
     * 2019-05-21 17:15:32.612 21616-21616/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 3
     * 2019-05-21 17:15:32.613 21616-21616/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 4
     * 2019-05-21 17:15:32.614 21616-21616/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 5
     * <p>
     * 去掉重复值
     */
    private void test8() {
        mDisposable = Observable.just(1, 1, 2, 3, 4, 4, 4, 5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("flatMap : accept : " + integer + "\n");
                        Log.e(TAG, "flatMap : accept : " + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-21 17:17:55.921 21741-21741/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 32
     * 2019-05-21 17:17:55.922 21741-21741/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 41
     * 2019-05-21 17:17:55.922 21741-21741/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 41
     * 2019-05-21 17:17:55.923 21741-21741/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 42
     * 2019-05-21 17:17:55.923 21741-21741/com.fzzz.mydemo E/RxJavaActivity: flatMap : accept : 53
     * <p>
     * 过滤器，接受一个参数，让其过滤掉不符合我们条件的值
     */
    private void test9() {
        mDisposable = Observable.just(13, 12, 22, 32, 41, 41, 42, 53)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer >= 30;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("flatMap : accept : " + integer + "\n");
                        Log.e(TAG, "flatMap : accept : " + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-21 17:26:26.612 22120-22120/com.fzzz.mydemo E/RxJavaActivity: buffer size : 3
     * 2019-05-21 17:26:26.612 22120-22120/com.fzzz.mydemo E/RxJavaActivity: buffer value : [1, 2, 3]
     * 2019-05-21 17:26:26.613 22120-22120/com.fzzz.mydemo E/RxJavaActivity: 1
     * 2019-05-21 17:26:26.614 22120-22120/com.fzzz.mydemo E/RxJavaActivity: 2
     * 2019-05-21 17:26:26.615 22120-22120/com.fzzz.mydemo E/RxJavaActivity: 3
     * 2019-05-21 17:26:26.616 22120-22120/com.fzzz.mydemo E/RxJavaActivity: buffer size : 3
     * 2019-05-21 17:26:26.617 22120-22120/com.fzzz.mydemo E/RxJavaActivity: buffer value : [3, 4, 5]
     * 2019-05-21 17:26:26.618 22120-22120/com.fzzz.mydemo E/RxJavaActivity: 3
     * 2019-05-21 17:26:26.618 22120-22120/com.fzzz.mydemo E/RxJavaActivity: 4
     * 2019-05-21 17:26:26.619 22120-22120/com.fzzz.mydemo E/RxJavaActivity: 5
     * 2019-05-21 17:26:26.619 22120-22120/com.fzzz.mydemo E/RxJavaActivity: buffer size : 1
     * 2019-05-21 17:26:26.620 22120-22120/com.fzzz.mydemo E/RxJavaActivity: buffer value : [5]
     * 2019-05-21 17:26:26.620 22120-22120/com.fzzz.mydemo E/RxJavaActivity: 5
     * <p>
     * buffer(count,skip)，作用是将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，然后生成一个  Observable
     */
    private void test10() {
        mDisposable = Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        content.append("buffer size : " + integers.size() + "\n");
                        Log.e(TAG, "buffer size : " + integers.size() + "\n");
                        content.append("buffer value : " + integers.toString() + "\n");
                        Log.e(TAG, "buffer value : " + integers.toString() + "\n");
                        for (Integer i : integers) {
                            content.append(i + "\n");
                            Log.e(TAG, i + "\n");
                        }
                    }
                });
    }

    /**
     * 2019-05-21 17:34:21.064 22420-22420/com.fzzz.mydemo E/RxJavaActivity: timer start : 2019-05-21 17:34:21
     * 2019-05-21 17:34:23.160 22420-22420/com.fzzz.mydemo E/RxJavaActivity: timer :0 at 2019-05-21 17:34:23
     * <p>
     * 一个定时任务。在 1.x 中它还可以执行间隔逻辑，但在 2.x 中此功能被交给了 interval，下一个会介绍。但需要注意的是，timer 和 interval 均默认在新线程。
     */
    private void test11() {
        content.append("timer start : " + TimeUtil.getLongTime() + "\n");
        Log.e(TAG, "timer start : " + TimeUtil.getLongTime() + "\n");
        mDisposable = Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        content.append("timer :" + aLong + " at " + TimeUtil.getLongTime() + "\n");
                        Log.e(TAG, "timer :" + aLong + " at " + TimeUtil.getLongTime() + "\n");
                    }
                });
    }

    /**
     * 2019-05-21 17:40:43.915 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer start : 2019-05-21 17:40:43
     * 2019-05-21 17:40:46.995 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer :0 at 2019-05-21 17:40:46
     * 2019-05-21 17:40:48.993 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer :1 at 2019-05-21 17:40:48
     * 2019-05-21 17:40:50.995 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer :2 at 2019-05-21 17:40:50
     * 2019-05-21 17:40:52.993 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer :3 at 2019-05-21 17:40:52
     * 2019-05-21 17:40:54.992 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer :4 at 2019-05-21 17:40:54
     * 2019-05-21 17:40:56.993 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer :5 at 2019-05-21 17:40:56
     * 2019-05-21 17:40:58.991 22576-22576/com.fzzz.mydemo E/RxJavaActivity: timer :6 at 2019-05-21 17:40:58
     * <p>
     * interval 操作符用于间隔时间执行某个操作，其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位
     * 当我们的Activity 都销毁的时候，实际上这个操作还依然在进行，所以需要在onDestroy把返回值Disposable释放
     */
    private void test12() {
        content.append("timer start : " + TimeUtil.getLongTime() + "\n");
        Log.e(TAG, "timer start : " + TimeUtil.getLongTime() + "\n");
        mDisposable = Observable.interval(3, 2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        content.append("timer :" + aLong + " at " + TimeUtil.getLongTime() + "\n");
                        Log.e(TAG, "timer :" + aLong + " at " + TimeUtil.getLongTime() + "\n");
                    }
                });
    }

    /**
     * 2019-05-21 17:54:24.956 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext 保存 1成功
     * 2019-05-21 17:54:24.957 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext :1
     * 2019-05-21 17:54:24.958 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext 保存 2成功
     * 2019-05-21 17:54:24.959 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext :2
     * 2019-05-21 17:54:24.960 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext 保存 3成功
     * 2019-05-21 17:54:24.961 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext :3
     * 2019-05-21 17:54:24.962 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext 保存 4成功
     * 2019-05-21 17:54:24.964 22761-22761/com.fzzz.mydemo E/RxJavaActivity: doOnNext :4
     * <p>
     * 它的作用是让订阅者在接收到数据之前干点事情
     */
    private void test13() {
        mDisposable = Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        content.append("doOnNext 保存 " + integer + "成功" + "\n");
                        Log.e(TAG, "doOnNext 保存 " + integer + "成功" + "\n");
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        content.append("doOnNext :" + integer + "\n");
                        Log.e(TAG, "doOnNext :" + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-22 10:08:08.863 24183-24183/com.fzzz.mydemo E/RxJavaActivity: single : onSuccess : -1755299070
     * <p>
     * Single 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()
     */
    private void test14() {
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        content.append("single : onSuccess : " + integer + "\n");
                        Log.e(TAG, "single : onSuccess : " + integer + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        content.append("single : onError : " + e.getMessage() + "\n");
                        Log.e(TAG, "single : onError : " + e.getMessage() + "\n");
                    }
                });
    }

    private void test15() {
        mDisposable = Observable.just(1, 2, 3, 4, 5, 6, 7)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("doOnNext :" + integer + "\n");
                        Log.e(TAG, "doOnNext :" + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-22 10:35:52.948 24881-24881/com.fzzz.mydemo E/RxJavaActivity: debounce :2
     * 2019-05-22 10:35:53.544 24881-24881/com.fzzz.mydemo E/RxJavaActivity: debounce :4
     * 2019-05-22 10:35:54.150 24881-24881/com.fzzz.mydemo E/RxJavaActivity: debounce :5
     * <p>
     * 去除发送频率过快的项，去除发送间隔时间小于 500 毫秒的发射事件，所以 1 和 3 被去掉了。
     */
    private void test16() {
        mDisposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1); // skip
                TimeUnit.MILLISECONDS.sleep(400);
//                Thread.sleep(400);
                emitter.onNext(2); // deliver
                TimeUnit.MILLISECONDS.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("debounce :" + integer + "\n");
                        Log.e(TAG, "debounce :" + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-22 10:44:38.766 25106-25106/com.fzzz.mydemo E/RxJavaActivity: defer : 1
     * 2019-05-22 10:44:38.767 25106-25106/com.fzzz.mydemo E/RxJavaActivity: defer : 2
     * 2019-05-22 10:44:38.768 25106-25106/com.fzzz.mydemo E/RxJavaActivity: defer : 3
     * 2019-05-22 10:44:38.769 25106-25106/com.fzzz.mydemo E/RxJavaActivity: defer : 4
     * 2019-05-22 10:44:38.770 25106-25106/com.fzzz.mydemo E/RxJavaActivity: defer : onComplete
     * <p>
     * 每次订阅都会创建一个新的 Observable，并且如果没有被订阅，就不会产生新的 Observable。
     */
    private void test17() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3, 4);
            }
        });

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                content.append("defer : " + integer + "\n");
                Log.e(TAG, "defer : " + integer + "\n");
            }

            @Override
            public void onError(Throwable e) {
                content.append("defer : onError : " + e.getMessage() + "\n");
                Log.e(TAG, "defer : onError : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                content.append("defer : onComplete\n");
                Log.e(TAG, "defer : onComplete\n");
            }
        });
    }

    /**
     * 2019-05-22 10:49:17.134 25571-25571/com.fzzz.mydemo E/RxJavaActivity: last : 3
     * <p>
     * last 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项。没有则输出默认值4
     */
    private void test18() {
        mDisposable = Observable.just(1, 2, 3)
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("last : " + integer + "\n");
                        Log.e(TAG, "last : " + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-22 10:52:51.511 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :1
     * 2019-05-22 10:52:51.512 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :2
     * 2019-05-22 10:52:51.513 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :3
     * 2019-05-22 10:52:51.514 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :4
     * 2019-05-22 10:52:51.515 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :5
     * 2019-05-22 10:52:51.515 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :6
     * 2019-05-22 10:52:51.516 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :9
     * 2019-05-22 10:52:51.517 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :8
     * 2019-05-22 10:52:51.517 25698-25698/com.fzzz.mydemo E/RxJavaActivity: accept: merge :7
     * <p>
     * merge 的作用是把多个 Observable 结合起来，接受可变参数，也支持迭代器集合。
     * 注意: 它和 concat 的区别在于，不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
     */
    private void test19() {
        mDisposable = Observable.merge(Observable.just(1, 2, 3, 4, 5, 6), Observable.just(9, 8, 7))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        content.append("merge :" + integer + "\n");
                        Log.e(TAG, "accept: merge :" + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-22 10:56:38.180 25813-25813/com.fzzz.mydemo E/RxJavaActivity: accept: reduce : 10
     * <p>
     * reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值。
     * 中间采用 reduce ，支持一个 function 为两数值相加，所以应该最后的值是：1 + 2 = 3 + 3 = 6 + 4 = 10， Log日志完美解决了我们的问题。
     */
    private void test20() {
        mDisposable = Observable.just(1, 2, 3, 4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("reduce : " + integer + "\n");
                        Log.e(TAG, "accept: reduce : " + integer + "\n");
                    }
                });
    }

    /**
     * 2019-05-22 11:02:30.514 26272-26272/com.fzzz.mydemo E/RxJavaActivity: accept: scan 1
     * 2019-05-22 11:02:30.515 26272-26272/com.fzzz.mydemo E/RxJavaActivity: accept: scan 4
     * 2019-05-22 11:02:30.516 26272-26272/com.fzzz.mydemo E/RxJavaActivity: accept: scan 9
     * 2019-05-22 11:02:30.516 26272-26272/com.fzzz.mydemo E/RxJavaActivity: accept: scan 16
     * <p>
     * scan 操作符作用和上面的 reduce 一致，唯一区别是 reduce 是个只追求结果的坏人，而 scan 会始终如一地把每一个步骤都输出。
     */
    private void test21() {
        mDisposable = Observable.just(1, 3, 5, 7)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        content.append("scan " + integer + "\n");
                        Log.e(TAG, "accept: scan " + integer + "\n");
                    }
                });

    }

    /**
     * 2019-05-22 11:14:38.746 26584-26584/com.fzzz.mydemo E/RxJavaActivity: window
     * 2019-05-22 11:14:38.759 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Sub Divide begin...
     * 2019-05-22 11:14:39.053 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:0
     * 2019-05-22 11:14:39.353 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:1
     * 2019-05-22 11:14:39.651 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Sub Divide begin...
     * 2019-05-22 11:14:39.659 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:2
     * 2019-05-22 11:14:39.952 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:3
     * 2019-05-22 11:14:40.253 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:4
     * 2019-05-22 11:14:40.550 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Sub Divide begin...
     * 2019-05-22 11:14:40.556 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:5
     * 2019-05-22 11:14:40.855 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:6
     * 2019-05-22 11:14:41.155 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:7
     * 2019-05-22 11:14:41.449 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Sub Divide begin...
     * 2019-05-22 11:14:41.456 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:8
     * 2019-05-22 11:14:41.754 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:9
     * 2019-05-22 11:14:42.053 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:10
     * 2019-05-22 11:14:42.349 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Sub Divide begin...
     * 2019-05-22 11:14:42.355 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:11
     * 2019-05-22 11:14:42.653 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:12
     * 2019-05-22 11:14:42.956 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:13
     * 2019-05-22 11:14:43.256 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Sub Divide begin...
     * 2019-05-22 11:14:43.262 26584-26584/com.fzzz.mydemo E/RxJavaActivity: Next:14
     * <p>
     * 按照实际划分窗口，将数据发送给不同的 Observable
     */
    private void test22() {
        content.append("window" + "\n");
        Log.e(TAG, "window" + "\n");
        mDisposable = Observable.interval(300, TimeUnit.MILLISECONDS) //300毫秒发一次
                .take(15) //最多发15次
                .window(900, TimeUnit.MILLISECONDS) //600毫秒一组
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        content.append("Sub Divide begin...\n");
                        Log.e(TAG, "Sub Divide begin...\n");
                        mDisposable = longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        content.append("Next:" + aLong + "\n");
                                        Log.e(TAG, "Next:" + aLong + "\n");
                                    }
                                });
                    }
                });
    }

    /**
     * 2019-05-23 15:15:47.698 30828-30828/com.fzzz.mydemo E/RxJavaActivity: doOnSubscribe after trampoline: main
     * 2019-05-23 15:15:47.700 30828-30899/com.fzzz.mydemo E/RxJavaActivity: doOnSubscribe after main: RxComputationThreadPool-1
     * 2019-05-23 15:15:47.705 30828-30900/com.fzzz.mydemo E/RxJavaActivity: doOnSubscribe after new Thread: RxCachedThreadScheduler-1
     * 2019-05-23 15:15:47.711 30828-30902/com.fzzz.mydemo E/RxJavaActivity: doOnSubscribe: RxNewThreadScheduler-1
     * 2019-05-23 15:15:47.711 30828-30902/com.fzzz.mydemo E/RxJavaActivity: doOnNext: RxNewThreadScheduler-1
     * 2019-05-23 15:15:47.712 30828-30902/com.fzzz.mydemo E/RxJavaActivity: map thread: RxNewThreadScheduler-1
     * 2019-05-23 15:15:47.712 30828-30902/com.fzzz.mydemo E/RxJavaActivity: doOnNext after new Thread: RxNewThreadScheduler-1
     * 2019-05-23 15:15:47.712 30828-30828/com.fzzz.mydemo E/RxJavaActivity: map after main: main
     * 2019-05-23 15:15:47.713 30828-30828/com.fzzz.mydemo E/RxJavaActivity: doOnNext after main: main
     * 2019-05-23 15:15:47.714 30828-30903/com.fzzz.mydemo E/RxJavaActivity: doOnNext after trampoline: RxCachedThreadScheduler-2
     * 2019-05-23 15:15:47.714 30828-30903/com.fzzz.mydemo E/RxJavaActivity: onNext : RxCachedThreadScheduler-2
     * <p>
     * doondoOnSubscribe 线程为后边指定的subscribeOn线程，未指定为main,指定多个subscribeOn，只第一个生效
     * doondoOnSubscribe 越在后边越先执行
     * doOnSubscribe after trampoline: main
     * doOnSubscribe after main: RxComputationThreadPool-1
     * doOnSubscribe after new Thread: RxCachedThreadScheduler-1
     * doOnSubscribe: RxNewThreadScheduler-1
     * doonnext 线程为当前线程 上个运行的是doOnSubscribe 在newThread
     * doOnNext: RxNewThreadScheduler-1
     * map thread: RxNewThreadScheduler-1
     * doOnNext after new Thread: RxNewThreadScheduler-1
     * observeOn 会改变以后的它线程
     * map after main: main
     * doOnNext after main: main
     * observeOn 可以随时改变线程
     * doOnNext after trampoline: RxCachedThreadScheduler-2
     * onNext : RxCachedThreadScheduler-2
     */
    private void test23() {
        mDisposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
            }
        })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe: " + Thread.currentThread().getName());
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "doOnNext: " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.single())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Log.e(TAG, "map thread: " + Thread.currentThread().getName());
                        return integer + "";
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe after new Thread: " + Thread.currentThread().getName());
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "doOnNext after new Thread: " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Log.e(TAG, "map after main: " + Thread.currentThread().getName());
                        return s + "1";
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "doOnNext after main: " + Thread.currentThread().getName());
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe after main: " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "doOnNext after trampoline: " + Thread.currentThread().getName());
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe after trampoline: " + Thread.currentThread().getName());
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "onNext : " + Thread.currentThread().getName());
                    }
                });
    }

    private void test24() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
