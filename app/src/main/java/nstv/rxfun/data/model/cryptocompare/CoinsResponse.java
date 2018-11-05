package nstv.rxfun.data.model.cryptocompare;

import com.squareup.moshi.Json;

import java.util.Map;

public class CoinsResponse {
    @Json(name = "Response")
    private String response;
    @Json(name = "BaseImageUrl")
    private String baseImageUrl;
    @Json(name = "BaseLinkUrl")
    private String baseLinkUrl;
    @Json(name = "Data")
    private Map<String, Coin> coins;

    public String getResponse() {
        return response;
    }

    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public String getBaseLinkUrl() {
        return baseLinkUrl;
    }

    public Map<String, Coin> getCoins() {
        return coins;
    }

}
