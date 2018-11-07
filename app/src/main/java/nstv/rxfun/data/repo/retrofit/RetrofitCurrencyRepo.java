package nstv.rxfun.data.repo.retrofit;

import android.content.Context;
import io.reactivex.Observable;
import nstv.rxfun.BuildConfig;
import nstv.rxfun.data.model.Conversion;
import nstv.rxfun.data.model.cryptocompare.Coin;
import nstv.rxfun.data.model.cryptocompare.ConversionResponse;
import nstv.rxfun.data.repo.ConversionRepo;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RetrofitCurrencyRepo implements ConversionRepo {

    private CoinsService coinsService;
    private ConversionService conversionService;

    public RetrofitCurrencyRepo(Context context) {

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(new File(context.getCacheDir(), "responses"), 10 * 1024 * 1024))
                .build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                //TODO: add rx adapter
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client);

        coinsService = retrofitBuilder
                .baseUrl(BuildConfig.COIN_SERVICE_BASE)
                .build()
                .create(CoinsService.class);

        conversionService = retrofitBuilder
                .baseUrl(BuildConfig.CONVERSION_SERVICE_BASE)
                .build()
                .create(ConversionService.class);

    }

    @Override
    public Observable<List<Conversion>> getTopConversions(String to) {
        //TODO return top 10 coins conversions
        return null;
    }

    @Override
    public Observable<List<String>> getCoinsList() {
        //TODO: get coinList
        return null;
    }

    @Override
    public Observable<Conversion> convert(String from, String to) {
        //TODO: get conversion from coin
        return null;
    }

    private Observable<List<Conversion>> convert(List<Coin> coins, String to) {
        StringBuilder stringBuilder = new StringBuilder();

        //build coins string
        String sep = "";
        for (Coin coin : coins) {
            stringBuilder.append(sep);
            stringBuilder.append(coin.getName());
            sep = ",";
        }

        return conversionService.convert(stringBuilder.toString(), to.toUpperCase())
                .flatMap(conversionResponse -> {
                    if (conversionResponse.getMessage() != null) {
                        throw new Error(conversionResponse.getMessage());
                    }
                    List<Conversion> conversions = new ArrayList<>();
                    for (String from : conversionResponse.getRaw().keySet()) {
                        Map<String, ConversionResponse.Conversion> rawConversions = conversionResponse.getRaw().get(from);
                        Map<String, ConversionResponse.Conversion> displayConversions = conversionResponse.getDisplay().get(from);
                        for (String to1 : rawConversions.keySet()) {
                            String fromSymbol = displayConversions.get(to1).getFrom();
                            String fromUrl = getCoinUrl(coins, from);

                            String toSymbol = displayConversions.get(to1).getTo();
                            String toUrl = String.format("http://s.xe.com/themes/xe/images/flags/big/%s.png", to1.toLowerCase());

                            String priceDisplay = displayConversions.get(to1).getPrice();
                            double change = Double.parseDouble(rawConversions.get(to1).getChange());
                            double price = Double.parseDouble(rawConversions.get(to1).getPrice());

                            Conversion conversion = new Conversion(fromSymbol, fromUrl, toSymbol, toUrl, change, priceDisplay, price);
                            conversions.add(conversion);
                        }
                    }
                    return Observable.just(conversions);
                });
    }

    private String getCoinUrl(List<Coin> coins, String coin) {
        for (Coin item : coins) {
            if (item.getName().equals(coin.toUpperCase())) {
                return "https://www.cryptocompare.com" + item.getImageUrl();
            }
        }
        return null;
    }
}
