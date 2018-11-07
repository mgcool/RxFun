package nstv.rxfun;

import android.app.Application;
import nstv.rxfun.data.repo.ConversionRepo;
import nstv.rxfun.data.repo.retrofit.RetrofitCurrencyRepo;

public class ConverterApplication extends Application {
    //Singletons
    private static ConversionRepo conversionRepo;

    @Override
    public void onCreate() {
        super.onCreate();

        conversionRepo = new RetrofitCurrencyRepo(this);
    }

    public static ConversionRepo getConversionRepo() {
        return conversionRepo;
    }
}
