package nstv.rxfun.data.repo.retrofit;

import nstv.rxfun.data.model.cryptocompare.CoinsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinsService {

    //TODO: change to observable
    @GET("/api/data/coinlist/")
    Call<CoinsResponse> getCoins();

}
