package com.example.business.controller;


import com.example.business.pojo.requestBody.TradeRequestBody;
import com.example.business.pojo.responseBody.TradeResponseBody;
import com.example.business.service.ITradeService;
import com.example.business.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
@RestController
@CrossOrigin
@RequestMapping("/trade")
public class TradeController {

  @Resource
  private ITradeService tradeService;

  @RequestMapping("traditionalTrade")
  public Result<Void> traditionalTrade() {
    try {
      // 进行股票验证，用户验证
      // 交易入库
      return Result.success();
    } catch (Exception e) {
      return Result.error();
    }
  }

  @PostMapping("queryTradeRecord")
  public Result<TradeResponseBody.TradeRecords> queryTradeRecord(@RequestBody TradeRequestBody.QueryTradeRecord req) {
    try {
      return Result.success(tradeService.queryTradeRecord(req));
    } catch (Exception e) {
      return Result.error();
    }
  }

  @PostMapping("statisticRecord")
  public Result<TradeResponseBody.StatisticRecord> statisticRecord(@RequestBody TradeRequestBody.StatisticReq req) {
    try {
      return Result.success(tradeService.statisticRecord(req));
    } catch (Exception e) {
      return Result.error();
    }
  }


  @ApiOperation(value="traditionaltrade",notes="交易")
  @PostMapping("makeTrade")
  public Result<String> makeTrade(@RequestBody TradeRequestBody.TraTrade req) {
    try {
      int insert = tradeService.makeTrade(req);
      if(insert == 1){
        return Result.success();
      }else{
        return Result.error();
      }
    } catch (Exception e) {
      return Result.error(e.getMessage());
    }
  }

  @PostMapping("chartdata")
  public List<Map<String, Object>> getChart(String frequency) {
    return tradeService.getChartData(frequency);
  }

  @ApiOperation(value="traditionaltrade",notes="交易")
  @PostMapping("makeNlpTrade")
  public Result<String> makeNlpTrade(@RequestBody TradeRequestBody.NlpTrade req) {
    try {
      int insert = tradeService.makeNlpTrade(req);
      if(insert == 1){
        return Result.success();
      }else{
        return Result.error();
      }
    } catch (Exception e) {
      return Result.error(e.getMessage());
    }
  }

}

