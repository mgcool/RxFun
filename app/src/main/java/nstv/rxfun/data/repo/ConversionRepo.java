package nstv.rxfun.data.repo;


import io.reactivex.Observable;
import nstv.rxfun.data.model.Conversion;

import java.util.List;

public interface ConversionRepo {

    Observable<List<Conversion>> getTopConversions(String to);
    Observable<Conversion> convert(String from, String to);
    Observable<List<String>> getCoinsList();

}
