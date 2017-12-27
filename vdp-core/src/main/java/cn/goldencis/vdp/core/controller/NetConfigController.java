package cn.goldencis.vdp.core.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.core.entity.NetConfigDO;
import cn.goldencis.vdp.core.entity.NetConfigsForm;
import cn.neiwang.omsjni.IFConfig;

@Controller
@RequestMapping("/netconfig")
public class NetConfigController {

    /**
     * 网络配置
     * @return
     */
    @RequestMapping(value = "/netconfig", method = RequestMethod.GET)
    public ModelAndView netconfig() {
        // 如果开始小于1默认为1
        ModelAndView model = new ModelAndView();
        try {
            JSONObject obj = new JSONObject(IFConfig.getIFConfig());
            List<Map<String, Object>> datalist = new ArrayList<>();
            if (obj != null) {
                Iterator<String> it = obj.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    Map<String, Object> eth = new HashMap<>();
                    JSONObject ethObj = (JSONObject) obj.get(key);
                    eth.put("name", key);
                    eth.put("addr", ethObj.get("addr"));
                    eth.put("gateway", ethObj.get("gateway"));
                    eth.put("mask", ethObj.get("mask"));
                    datalist.add(eth);
                }
            }
            Collections.reverse(datalist);
            model.addObject("data", datalist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        model.setViewName("netconfig/netconfig");
        return model;
    }

    /**
     * 保存网络配置
     * @param config
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savenetconfig", produces = "application/json", method = RequestMethod.POST)
    public String savenetconfig(NetConfigsForm netConfigsForm) {
        List<NetConfigDO> netConfigs = netConfigsForm.getNetConfigs();
        if (netConfigs != null && !netConfigs.isEmpty()) {
            Map<String, Object> configMap = new HashMap<>();
            if (netConfigs != null) {
                for (int i = 0; netConfigs.size() > i; i++) {
                    NetConfigDO netdo = netConfigs.get(i);
                    Map<String, String> map = new HashMap<>();
                    map.put("addr", netdo.getAddr());
                    map.put("gateway", netdo.getGateway());
                    map.put("mask", netdo.getMask());
                    JSONObject json = new JSONObject(map);
                    configMap.put(netdo.getEthname(), json);
                }
            }
            JSONObject json = new JSONObject(configMap);
            /*"{\"eth0\":{\"addr\":\"192.168.3.90\",\"gateway\":\"192.168.3.1\",\"mask\":\"255.255.255.0\"}}"*/
            boolean flag = IFConfig.setIFConfig(json.toString());
            if (flag) {
                return "success";
            } else {
                return "failed";
            }
        }
        return "nodata";
    }
}
