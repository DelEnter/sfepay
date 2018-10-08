package com.ecpss.action.pay;

public interface TradeManagerDao {
    public int intBySql(String sql);
    public Double getAcount(Long merno);
}
