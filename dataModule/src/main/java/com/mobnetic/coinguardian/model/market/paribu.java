package com.mobnetic.coinguardian.model.market;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONObject;

import com.mobnetic.coinguardian.model.CheckerInfo;
import com.mobnetic.coinguardian.model.Market;
import com.mobnetic.coinguardian.model.Ticker;
import com.mobnetic.coinguardian.model.currency.Currency;
import com.mobnetic.coinguardian.model.currency.VirtualCurrency;

public class Paribu extends Market {

	private final static String NAME = "Paribu";
	private final static String TTS_NAME = "Paribu";
	public final static String URL_BTC = "https://www.paribu.com/ticker";
	public final static HashMap<String, CharSequence[]> CURRENCY_PAIRS = new LinkedHashMap<String, CharSequence[]>();
	static {
		CURRENCY_PAIRS.put(VirtualCurrency.BTC, new String[]{
				Currency.TRY
			});
		}
	
	public Paribu() {
		super(NAME, TTS_NAME, CURRENCY_PAIRS);
	}
	
	@Override
	public String getUrl(int requestId, CheckerInfo checkerInfo) {
		if(VirtualCurrency.LTC.equals(checkerInfo.getCurrencyBase())) {
			return URL_LTC;
		}
		return URL_BTC;
	}
	
	@Override
	protected void parseTickerFromJsonObject(int requestId, JSONObject jsonObject, Ticker ticker, CheckerInfo checkerInfo) throws Exception {
		ticker.bid = tickerJsonObject.getDouble("highestBid");
				ticker.ask = tickerJsonObject.getDouble("lowestAsk");
				ticker.vol = tickerJsonObject.getDouble("volume");
				ticker.high = tickerJsonObject.getDouble("high24hr");
				ticker.low = tickerJsonObject.getDouble("low24hr");
				ticker.last = tickerJsonObject.getDouble("last");
				ticker.timestamp = (long) (tickerJsonObject.getDouble("timestamp") * TimeUtils.MILLIS_IN_SECOND);
	}
}
