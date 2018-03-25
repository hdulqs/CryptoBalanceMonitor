package com.triador.cryptobalancemonitor.service;

import com.triador.cryptobalancemonitor.Utils.BalanceUtils;
import com.triador.cryptobalancemonitor.binance.BinanceService;
import com.binance.api.client.domain.account.AssetBalance;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.util.List;

import static com.triador.cryptobalancemonitor.Utils.BalanceUtils.getCoinMarketCapJsonObject;

public class BinanceBalance implements BalanceService {

    private final BinanceService binanceService;

    public BinanceBalance(BinanceService binanceService) {
        this.binanceService = binanceService;
    }

    @Override
    public BigDecimal getBalance() {

        JsonObject coinmarketcapJson = getCoinMarketCapJsonObject();
        List<AssetBalance> binanceBalance = binanceService.getAllAssets();

        return BalanceUtils.getExchangeTotalBalance(binanceBalance, coinmarketcapJson).setScale(1, 1);
    }
}
