package nstv.rxfun;

import android.app.Application;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nstv.rxfun.data.SchedulersProvider;
import nstv.rxfun.data.repo.ConversionRepo;
import nstv.rxfun.data.repo.retrofit.rx.RetrofitCurrencyRepo;

public class ConverterApplication extends Application {
    //Singletons
    private static ConversionRepo conversionRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        conversionRepo = new RetrofitCurrencyRepo(this, new SchedulersProvider() {
            @Override
            public Scheduler mainScheduler() {
                return AndroidSchedulers.mainThread();
            }

            @Override
            public Scheduler backgroundScheduler() {
                return Schedulers.io();
            }
        });
    }

    public static ConversionRepo getConversionRepo(){
        return conversionRepo;
    }
}
