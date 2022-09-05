package com.summer.stockservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.stockservice.FeignClient.UserServiceFeignClient;
import com.summer.stockservice.entity.Rate;
import com.summer.stockservice.entity.Stock;
import com.summer.stockservice.entity.StockTotalView;
import com.summer.stockservice.entity.Trade;
import com.summer.stockservice.mapper.RateMapper;
import com.summer.stockservice.mapper.StockMapper;
import com.summer.stockservice.mapper.StockTotalViewMapper;
import com.summer.stockservice.mapper.TradeMapper;
import com.summer.stockservice.pojo.requestBody.TradeRequestBody;
import com.summer.stockservice.pojo.responseBody.TradeResponseBody;
import com.summer.stockservice.service.ITradeService;
import com.summer.userservice.entity.Users;
import exception.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements ITradeService {
  @Resource
  private StockTotalViewMapper stockTotalViewMapper;

  @Resource
  private StockMapper stockMapper;

  @Resource
  private RateMapper rateMapper;

  @Resource
  private TradeMapper tradeMapper;

  @Resource
  private UserServiceFeignClient userServiceFeignClient;


  @Override
  public TradeResponseBody.TradeRecords queryTradeRecord(TradeRequestBody.QueryTradeRecord req) {
    Page<StockTotalView> pageHelper = new Page<>(req.getCurrentPage(), req.getPageSize());
    Page<StockTotalView> stockTotalViewPage = stockTotalViewMapper.selectPage(pageHelper,
            new LambdaQueryWrapper<StockTotalView>()
                    .eq(!"Both".equals(req.getClientSide()), StockTotalView::getClientSide, req.getClientSide())
                    .eq(!"Both".equals(req.getHt_pt()), StockTotalView::getHtPt, req.getHt_pt())
                    .apply(("1D").equals(req.getFrequency()), "date_format(`date`,'%Y-%m-%d') = curdate()")
                    .apply(("1W").equals(req.getFrequency()), "(curdate() - interval 7 day) < date_format(`date`,'%Y-%m-%d')")
                    .apply(("2W").equals(req.getFrequency()), "(curdate() - interval 14 day) < date_format(`date`,'%Y-%m-%d')")
                    .apply(("1M").equals(req.getFrequency()), "(curdate() - interval 1 month) < date_format(`date`,'%Y-%m-%d')")
                    .apply(("3M").equals(req.getFrequency()), "(curdate() - interval 3 month) < date_format(`date`,'%Y-%m-%d')")
                    .apply(("6M").equals(req.getFrequency()), "(curdate() - interval 6 month) < date_format(`date`,'%Y-%m-%d')")
                    .apply(("1Y").equals(req.getFrequency()), "(curdate() - interval 1 year) < date_format(`date`,'%Y-%m-%d')")
                    .apply(("YTD").equals(req.getFrequency()), "year(`date`) = year(curdate())")
                    .orderBy(req.getDateSort() != 0, req.getDateSort() == 1, StockTotalView::getDate)
                    .orderBy(req.getClientNameSort() != 0, req.getClientNameSort() == 1, StockTotalView::getClientName)
                    .orderBy(req.getTickerSort() != 0, req.getTickerSort() == 1, StockTotalView::getTicker)
                    .orderBy(req.getRicSort() != 0, req.getRicSort() == 1, StockTotalView::getRic)
                    .orderBy(req.getSizeSort() != 0, req.getSizeSort() == 1, StockTotalView::getSize)
                    .orderBy(req.getPriceSort() != 0, req.getPriceSort() == 1, StockTotalView::getPrice)
                    .orderBy(req.getNotionalUsdSort() != 0, req.getNotionalUsdSort() == 1, StockTotalView::getNotionalUsd)
                    .orderBy(req.getCurrencySort() != 0, req.getCurrencySort() == 1, StockTotalView::getCurrency)
                    .orderBy(req.getIssuerSectorSort() != 0, req.getIssuerSectorSort() == 1, StockTotalView::getIssuerSector)
                    .orderBy(req.getSalesPersonSort() != 0, req.getSalesPersonSort() == 1, StockTotalView::getSalesperson));

    List<TradeResponseBody.TradeRecord> tradeRecordList = new ArrayList<>();
    for (StockTotalView stockTotalView : stockTotalViewPage.getRecords()) {
      TradeResponseBody.TradeRecord tradeRecord = TradeResponseBody.TradeRecord.builder()
              .clientName(stockTotalView.getClientName())
              .clientSide(stockTotalView.getClientSide())
              .currency(stockTotalView.getCurrency())
              .date(stockTotalView.getDate())
              .issuerSector(stockTotalView.getIssuerSector())
              .notionalUsd(stockTotalView.getNotionalUsd())
              .price(stockTotalView.getPrice())
              .ric(stockTotalView.getRic())
              .salesperson(stockTotalView.getSalesperson())
              .size(stockTotalView.getSize())
              .ticker(stockTotalView.getTicker())
              .htPt(stockTotalView.getHtPt())
              .build();
      tradeRecordList.add(tradeRecord);
    }
    return TradeResponseBody.TradeRecords.builder()
            .tradeRecordList(tradeRecordList)
            .total(stockTotalViewPage.getTotal())
            .build();
  }

  //chart图表
  @Override
  public List<Map<String, Object>> getChartData(String frequency) {
    QueryWrapper<StockTotalView> wrapper = new QueryWrapper<>();
    QueryWrapper<StockTotalView> stockTotalViewQueryWrapper = wrapper.select("sum((case when(`client_side` = 'Buy') then `notional_usd` else 0 end)) AS notional_usd_buy, sum((case when(client_side = 'Sell') then `notional_usd` else 0 end)) AS " +
                    "notional_usd_sell, date_format(date, '%Y-%m-%d') as date")
            .groupBy("date_format(date," + "'%Y-%m-%d')")
            .apply(("1D").equals(frequency), "date_format(`date`,'%Y-%m-%d') = curdate()")
            .apply(("1W").equals(frequency), "(curdate() - interval 7 day) < date_format(`date`,'%Y-%m-%d')")
            .apply(("2W").equals(frequency), "(curdate() - interval 14 day) < date_format(`date`,'%Y-%m-%d')")
            .apply(("1M").equals(frequency), "(curdate() - interval 1 month) < date_format(`date`,'%Y-%m-%d')")
            .apply(("3M").equals(frequency), "(curdate() - interval 3 month) < date_format(`date`,'%Y-%m-%d')")
            .apply(("6M").equals(frequency), "(curdate() - interval 6 month) < date_format(`date`,'%Y-%m-%d')")
            .apply(("1Y").equals(frequency), "(curdate() - interval 1 year) < date_format(`date`,'%Y-%m-%d')")
            .apply(("YTD").equals(frequency), "year(`date`) = year(curdate())")
            .orderByAsc("date");
    return stockTotalViewMapper.selectMaps(stockTotalViewQueryWrapper);
  }

  //traditional trade
  public int makeTrade(TradeRequestBody.TraTrade req) {
    //查询ClientName名字是否存在
    QueryWrapper<Users> qname = new QueryWrapper<>();
    qname.lambda().eq(Users::getUserName, req.getClientName());
    Users users = userServiceFeignClient.getUser(req.getClientName());
    boolean exists = users != null;


    //用户所要买的股票信息是否存在
    //用户填写的数据，同时符合ric，ticker，price，isuusesector，currency，
    QueryWrapper<Stock> qStock = new QueryWrapper<>();
    qStock.lambda().eq(Stock::getTicker, req.getTicker()).eq(Stock::getRic, req.getRic())
            .eq(Stock::getCurrency, req.getCurrency()).eq(Stock::getIssuerSector, req
                    .getIssueSector());
    boolean exists1 = stockMapper.exists(qStock);

    //查询salesman是否存在
    QueryWrapper<Users> qSales = new QueryWrapper<>();
    qSales.lambda().eq(Users::getUserName, req.getSalesperson());
    Users salesperson = userServiceFeignClient.getUser(req.getSalesperson());
    boolean exists2 = salesperson != null;

    //查找rateId
    QueryWrapper<Rate> qRateId = new QueryWrapper<>();
    qRateId.lambda().eq(Rate::getCurrency, req.getCurrency());

    if (!exists) {
      throw new UserNotFoundException();
    } else {
      if (!exists1) {
        throw new StockInfoErrorException();
      } else if (!exists2) {
        throw new SalespersonNotExistException();
      } else {
        //用户所填信息是正确的
        //查找userid
        Integer userId = users.getUserId();

        //根据userid统计用户已买的这支股票的大小
        QueryWrapper<Trade> qBuy = new QueryWrapper<>();
        QueryWrapper<Trade> qBuyEx = new QueryWrapper<>();
        qBuyEx.eq("client_side", "Buy").eq("user_id",
                userId).eq("RIC", req.getRic()).eq("Ticker", req.getTicker());
        qBuy.select("sum(size) AS buysize").eq("client_side", "Buy").eq("user_id",
                userId).eq("RIC", req.getRic()).eq("Ticker", req.getTicker());
        boolean exists3 = tradeMapper.exists(qBuyEx);
        int buySize;
        if (exists3) {
          List<Map<String, Object>> maps = tradeMapper.selectMaps(qBuy);
          Object obj = maps.get(0).get("buysize");
          buySize = Integer.parseInt(obj.toString());
        } else {
          buySize = 0;
        }

        //统计用户卖出的大小
        QueryWrapper<Trade> qSell = new QueryWrapper<>();
        QueryWrapper<Trade> qSellEx = new QueryWrapper<>();
        qSell.select("sum(size) AS sellsize").eq("client_side", "Sell").eq("user_id",
                userId).eq("RIC", req.getRic()).eq("Ticker", req.getTicker());
        qSellEx.eq("client_side", "Sell").eq("user_id",
                userId).eq("RIC", req.getRic()).eq("Ticker", req.getTicker());
        boolean exists4 = tradeMapper.exists(qSellEx);
        int sellSize;
        if (exists4) {
          List<Map<String, Object>> maps1 = tradeMapper.selectMaps(qSell);
          Object obj1 = maps1.get(0).get("sellsize");
          sellSize = Integer.parseInt(obj1.toString());
        } else {
          sellSize = 0;
        }
        //查找rateId
        Rate rate = rateMapper.selectOne(qRateId);
        Integer rateId = rate.getRateId();


        //封装成trade数据插入数据库
        Trade t = new Trade();
        Date d = new Date();
        t.setDate(d);
        t.setClientSide(req.getClientSide());
        t.setUserId(userId);
        t.setSize(req.getSize());
        t.setRic(req.getRic());
        t.setTicker(req.getTicker());
        t.setPrice(req.getPrice());
        t.setRateId(rateId);
        t.setHtPt(req.getHt_pt());
        t.setSalesperson(req.getSalesperson());


        //查看能否购买，可以就修改数据库中的size
        Stock stock = stockMapper.selectOne(qStock);
        Integer size = stock.getSize();//数据库中size
        UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();//buy的update
        UpdateWrapper<Stock> updateWrapper1 = new UpdateWrapper<>();//sell的update
        updateWrapper.lambda().eq(Stock::getRic, req.getRic()).eq(Stock::getTicker,
                req.getTicker()).set(Stock::getSize, size - req.getSize());
        updateWrapper1.lambda().eq(Stock::getRic, req.getRic()).eq(Stock::getTicker,
                req.getTicker()).set(Stock::getSize, size + req.getSize());

        //先检查size关系
        if (size<=0) {
          throw new ParamErrorException();
        }
        //如果买，不能大于全部股票size；如果卖，不能大于自己持股size
        if (req.getClientSide().equals("Buy")) {
          if (req.getSize() > size) {
            throw new StockNotEnoughException();
          }
          stockMapper.update(null, updateWrapper);
          return tradeMapper.insert(t);
        } else {//如果是卖不能大于自己已经有的
          if (req.getSize() > buySize - sellSize) {
            throw new UserStockNotEnoughException();
          }
          stockMapper.update(null, updateWrapper1);
          return tradeMapper.insert(t);
        }
      }
    }
  }

  //traditional trade
  public int makeNlpTrade(TradeRequestBody.NlpTrade req) {
    //查询ClientName名字是否存在
    QueryWrapper<Users> qName = new QueryWrapper<>();
    qName.lambda().eq(Users::getUserName, req.getClientName());
    Users users = userServiceFeignClient.getUser(req.getClientName());
    boolean exists = users != null;

    //查找ticker是否存在
    //如果存在，就找出size
    //找出股票信息
    QueryWrapper<Stock> qStock=new QueryWrapper<>();
    qStock.lambda().eq(Stock::getTicker,req.getTicker());
    boolean exists1 = stockMapper.exists(qStock);

    //查询salesman是否存在
    QueryWrapper<Users> qSales=new QueryWrapper<>();
    qSales.lambda().eq(Users::getUserName,req.getSalesperson());
    Users salesperson = userServiceFeignClient.getUser(req.getSalesperson());
    boolean exists2 = salesperson != null;

    if (!exists) {
      throw new UserNotFoundException();
    } else {
      if (!exists1) {
        throw new StockInfoErrorException();
      } else if (!exists2) {
        throw new SalespersonNotExistException();
      } else {
        //用户所填信息是正确的
        //查找userid
        Integer userId = users.getUserId();

        //根据ric找到stock信息
        Stock stock1 = stockMapper.selectOne(qStock);

        //根据userid统计用户已买的这支股票的大小
        QueryWrapper<Trade> qBuy = new QueryWrapper<>();
        QueryWrapper<Trade> qBuyEx = new QueryWrapper<>();
        qBuyEx.eq("client_side", "Buy").eq("user_id",
                userId).eq("Ticker", req.getTicker());
        qBuy.select("sum(size) AS buysize").eq("client_side", "Buy").eq("user_id",
                userId).eq("Ticker", req.getTicker());
        boolean exists3 = tradeMapper.exists(qBuyEx);
        int buySize;
        if (exists3) {
          List<Map<String, Object>> maps = tradeMapper.selectMaps(qBuy);
          Object obj = maps.get(0).get("buysize");
          buySize = Integer.parseInt(obj.toString());
        } else {
          buySize = 0;
        }

        //统计用户卖出的大小
        QueryWrapper<Trade> qSell = new QueryWrapper<>();
        QueryWrapper<Trade> qSellEx = new QueryWrapper<>();
        qSell.select("sum(size) AS sellsize").eq("client_side", "Sell").eq("user_id",
                userId).eq("Ticker", req.getTicker());
        qSellEx.eq("client_side", "Sell").eq("user_id",
                userId).eq("Ticker", req.getTicker());
        boolean exists4 = tradeMapper.exists(qSellEx);
        int sellSize;
        if (exists4) {
          List<Map<String, Object>> maps1 = tradeMapper.selectMaps(qSell);
          Object obj1 = maps1.get(0).get("sellsize");
          sellSize = Integer.parseInt(obj1.toString());
        } else {
          sellSize = 0;
        }


        //查找rateId
        QueryWrapper<Rate> qRateId = new QueryWrapper<>();
        qRateId.lambda().eq(Rate::getCurrency,stock1.getCurrency());
        Rate rate = rateMapper.selectOne(qRateId);
        Integer rateId = rate.getRateId();


        //封装成trade数据插入数据库
        Trade t = new Trade();
        Date d = new Date();
        t.setDate(d);
        t.setClientSide(req.getClientSide());
        t.setUserId(userId);
        t.setSize(req.getSize());
        t.setRic(stock1.getRic());
        t.setTicker(req.getTicker());
        t.setPrice(stock1.getPrice());
        t.setRateId(rateId);
        t.setHtPt(req.getHt_pt());
        t.setSalesperson(req.getSalesperson());


        //查看能否购买，可以就修改数据库中的size
        Integer size = stock1.getSize();//数据库中size
        UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();//buy的update
        UpdateWrapper<Stock> updateWrapper1 = new UpdateWrapper<>();//sell的update
        updateWrapper.lambda().eq(Stock::getRic, stock1.getRic()).eq(Stock::getTicker,
                req.getTicker()).set(Stock::getSize, size - req.getSize());
        updateWrapper1.lambda().eq(Stock::getRic, stock1.getRic()).eq(Stock::getTicker,
                req.getTicker()).set(Stock::getSize, size + req.getSize());

        //先检查size关系
        if (size<=0) {
          throw new ParamErrorException();
        }
        //如果买，不能大于全部股票size；如果卖，不能大于自己持股size
        if (req.getClientSide().equals("Buy")) {
          if (req.getSize() > size) {
            throw new StockNotEnoughException();
          }
          stockMapper.update(null, updateWrapper);
          return tradeMapper.insert(t);
        } else {//如果是卖不能大于自己已经有的
          if (req.getSize() > buySize - sellSize) {
            throw new UserStockNotEnoughException();
          }
          stockMapper.update(null, updateWrapper1);
          return tradeMapper.insert(t);
        }
      }
    }
  }

  @Override
  public TradeResponseBody.StatisticRecord statisticRecord(TradeRequestBody.StatisticReq req) {
    List<StockTotalView> stockTotalViews = stockTotalViewMapper.selectList(new LambdaQueryWrapper<StockTotalView>()
            .eq(!"Both".equals(req.getClientSide()), StockTotalView::getClientSide, req.getClientSide())
            .eq(!"Both".equals(req.getHt_pt()), StockTotalView::getHtPt, req.getHt_pt())
            .apply(("1D").equals(req.getFrequency()), "date_format(`date`,'%Y-%m-%d') = curdate()")
            .apply(("1W").equals(req.getFrequency()), "(curdate() - interval 7 day) < date_format(`date`,'%Y-%m-%d')")
            .apply(("2W").equals(req.getFrequency()), "(curdate() - interval 14 day) < date_format(`date`,'%Y-%m-%d')")
            .apply(("1M").equals(req.getFrequency()), "(curdate() - interval 1 month) < date_format(`date`,'%Y-%m-%d')")
            .apply(("3M").equals(req.getFrequency()), "(curdate() - interval 3 month) < date_format(`date`,'%Y-%m-%d')")
            .apply(("6M").equals(req.getFrequency()), "(curdate() - interval 6 month) < date_format(`date`,'%Y-%m-%d')")
            .apply(("1Y").equals(req.getFrequency()), "(curdate() - interval 1 year) < date_format(`date`,'%Y-%m-%d')")
            .apply(("YTD").equals(req.getFrequency()), "year(`date`) = year(curdate())"));
    int totalBuy = 0, totalSell = 0, quantity;
    double totalBuyNotional = 0, totalSellNotional = 0, quantityNotional;
    for (StockTotalView stockTotalView : stockTotalViews) {
      if ("Buy".equals(stockTotalView.getClientSide())) {
        totalBuy += stockTotalView.getSize();
        totalBuyNotional += stockTotalView.getNotionalUsd();
      } else {
        totalSell += stockTotalView.getSize();
        totalSellNotional += stockTotalView.getNotionalUsd();
      }
    }
    quantity = totalBuy - totalSell;
    quantityNotional = totalBuyNotional - totalSellNotional;
    return TradeResponseBody.StatisticRecord.builder()
            .quantity(quantity)
            .quantityNotional(quantityNotional)
            .totalBuy(totalBuy)
            .totalBuyNotional(totalBuyNotional)
            .totalSell(totalSell)
            .totalSellNotional(totalSellNotional)
            .build();
  }
}
