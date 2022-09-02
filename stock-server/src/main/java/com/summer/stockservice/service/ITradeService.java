package com.summer.stockservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.stockservice.entity.Trade;
import com.summer.stockservice.pojo.requestBody.TradeRequestBody;
import com.summer.stockservice.pojo.responseBody.TradeResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
public interface ITradeService extends IService<Trade> {

  TradeResponseBody.TradeRecords queryTradeRecord(TradeRequestBody.QueryTradeRecord req);

  List<Map<String, Object>> getChartData(String frequency);

  int makeTrade(TradeRequestBody.TraTrade req);

  int makeNlpTrade(TradeRequestBody.NlpTrade req);

  TradeResponseBody.StatisticRecord statisticRecord(TradeRequestBody.StatisticReq req);
}
