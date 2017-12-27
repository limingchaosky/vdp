package cn.goldencis.vdp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.goldencis.vdp.core.dao.CTDictionaryMapper;
import cn.goldencis.vdp.core.dao.TDictionaryMapper;
import cn.goldencis.vdp.core.entity.TDictionary;
import cn.goldencis.vdp.core.entity.TDictionaryCriteria;
import cn.goldencis.vdp.core.entity.TDictionaryCriteria.Criteria;
import cn.goldencis.vdp.core.service.ITDictionaryService;

@Component("tDictionaryService")
public class TDictionaryServiceImpl implements ITDictionaryService {
    @Autowired
    private TDictionaryMapper dictionaryMapper;

    @Autowired
    private CTDictionaryMapper cdictionaryMapper;

    public List<TDictionary> queryTDictionaryList(String type) {
        TDictionaryCriteria example = new TDictionaryCriteria();
        Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        example.setOrderByClause("sort_by");
        List<TDictionary> list = dictionaryMapper.selectByExample(example);
        return list;
    }

    @Override
    public TDictionary getDictByTypeAndValue(String type, String value) {
        TDictionaryCriteria example = new TDictionaryCriteria();
        Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andValueEqualTo(value);
        List<TDictionary> list = dictionaryMapper.selectByExample(example);
        return list.get(0);
    }

    @Override
    public boolean addOrUpdateByType(TDictionary record) {
        if (record.getId() == null) {
            TDictionaryCriteria example = new TDictionaryCriteria();
            Criteria criteria = example.createCriteria();
            criteria.andTypeEqualTo(record.getType());
            example.setOrderByClause("sort_by");
            List<TDictionary> list = dictionaryMapper.selectByExample(example);
            int sort = 0;
            //Integer value = 0;
            if (list != null && list.size() > 0) {
                TDictionary dict = list.get(list.size() - 1);
                sort = dict.getSortBy();
                //value = Integer.parseInt(dict.getValue());
            }

            record.setSortBy(++sort);
            if (record.getValue() == null) {
                record.setValue(sort + "");
            }
            dictionaryMapper.insertSelective(record);
            return true;
        }
        dictionaryMapper.updateByPrimaryKeySelective(record);
        return true;
    }

    @Override
    public List<TDictionary> queryPage(String type, Integer start, Integer length) {
        return cdictionaryMapper.queryPage(type, start, length);
    }

    @Override
    public Integer selectCount(String type) {
        TDictionaryCriteria example = new TDictionaryCriteria();
        Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        example.setOrderByClause("sort_by");
        List<TDictionary> list = dictionaryMapper.selectByExample(example);
        return list.size();
    }

    @Override
    public boolean checkDataRepeat(TDictionary record) {

        return dictionaryMapper.checkDataRepeat(record.getId(), record.getDescription()) > 0 ? true : false;
    }
}
