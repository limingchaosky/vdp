package cn.goldencis.vdp.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.goldencis.vdp.core.entity.TDictionary;
import cn.goldencis.vdp.core.service.ITDictionaryService;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/common/dictionary")
public class TDictionaryController {

    @Autowired
    ITDictionaryService dictionaryService;

    @RequestMapping(value = "/getDictByType", method = RequestMethod.GET)
    public @ResponseBody List<TDictionary> getDictByType(String type) {
        return dictionaryService.queryTDictionaryList(type);
    }

    @RequestMapping(value = "/addOrUpdateByType", method = RequestMethod.POST)
    public @ResponseBody String addOrUpdateByType(TDictionary record) {
        if (dictionaryService.checkDataRepeat(record)) {
            return "repeat";
        }
        dictionaryService.addOrUpdateByType(record);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/queryPage", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> queryPage(Integer length, Integer start, String type, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();

        Integer count = dictionaryService.selectCount(type);
        List<TDictionary> list = dictionaryService.queryPage(type, start, length);

        model.put("recordsTotal", count);
        model.put("recordsFiltered", count);
        model.put("data", toJson(list));
        model.put("exportlength", length);
        model.put("exportstart", start);
        return model;
    }

    private JSONArray toJson(List<TDictionary> list) {
        JSONArray array = new JSONArray();
        for (TDictionary tmp : list) {
            JSONArray array1 = new JSONArray();
            array1.add(tmp.getDescription());
            array1.add(tmp.getId());
            array.add(array1);
        }
        return array;
    }
}
