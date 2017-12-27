package cn.goldencis.vdp.core.service;

import java.util.List;

import cn.goldencis.vdp.core.entity.TDictionary;

public interface ITDictionaryService {
    List<TDictionary> queryTDictionaryList(String type);

    boolean addOrUpdateByType(TDictionary record);

    TDictionary getDictByTypeAndValue(String type, String value);

    List<TDictionary> queryPage(String type, Integer start, Integer length);

    Integer selectCount(String type);

    boolean checkDataRepeat(TDictionary record);
}
