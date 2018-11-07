package nstv.rxfun.data.repo.retrofit;

import io.reactivex.Observable;
import nstv.rxfun.data.model.cryptocompare.ConversionResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConversionService {

    @GET("/data/pricemultifull")
    Observable<ConversionResponse> convert(@Query("fsyms") String from, @Query("tsyms") String to);

}
