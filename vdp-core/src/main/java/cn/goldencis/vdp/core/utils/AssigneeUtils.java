package cn.goldencis.vdp.core.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AssigneeUtils {
    public static String doAssigneeList(String assigneeListJson) {
        if (assigneeListJson == null || "".equals(assigneeListJson.trim())) {
            return null;
        } else {
            JSONArray ja = JSONArray.parseArray(assigneeListJson);
            //先循环一遍 获取id去重
            List<String> ids = new ArrayList<String>();
            Iterator<Object> it = ja.iterator();
            while (it.hasNext()) {
                JSONObject obj = (JSONObject) it.next();
                if (!ids.contains(obj.getString("userId"))) {
                    ids.add(obj.getString("userId"));
                }
            }
            StringBuffer sb = new StringBuffer();
            //再循环一遍 获取真实姓名
            for (String id : ids) {
                for (int j = 0; j < ja.size(); j++) {
                    JSONObject obj = ja.getJSONObject(j);
                    if (id.equals(obj.getString("userId"))) {
                        sb.append(obj.getString("userRealName") + ",");
                        break;
                    }
                }
            }
            return sb.toString().length() == 0 ? "" : sb.toString().substring(0, sb.toString().length() - 1);

        }
    }
}
