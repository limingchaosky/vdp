package cn.goldencis.vdp.common.utils;

import cn.goldencis.vdp.common.annotation.MyFieldAnnotation;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonPoiCsv<T> {

    public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            StringBuffer sb = new StringBuffer();
            for (String header : headers) {
                sb.append(header + ",");
            }
            //写标题
            bw.write((sb.toString().length() == 0 ? "" : sb.toString().substring(0, sb.toString().length() - 1))
                    + "\t\n");
            //遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            boolean flag = true;
            List<String> methodList = null;
            while (it.hasNext()) {
                T t = (T) it.next();
                if (flag) {
                    methodList = doSomeThing(t);
                    flag = false;
                }
                try {
                    sb = new StringBuffer();
                    for (String method : methodList) {
                        Class<? extends Object> tCls = t.getClass();
                        Method getMethod = tCls.getMethod(method, new Class[] {});
                        Object value = getMethod.invoke(t, new Object[] {});
                        //判断值的类型后进行强制类型转换
                        String textValue = null;
                        //这里目前只需要转换Date类型 待增加
                        if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else {
                            textValue = value == null ? "" : value.toString();
                        }
                        sb.append(textValue + ",");
                    }
                    //写标题
                    bw.write((sb.toString().length() == 0 ? "" : sb.toString().substring(0, sb.toString().length() - 1))
                            + "\t\n");

                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
            }
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (Exception e) {
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public List<String> doSomeThing(T t) {
        //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
        Field[] fields = t.getClass().getDeclaredFields();
        List<MyFieldAnnotation> list = new ArrayList<MyFieldAnnotation>();
        for (short i = 0; i < fields.length; i++) {
            Field field = fields[i];
            MyFieldAnnotation oMyFieldAnnotation = field.getAnnotation(MyFieldAnnotation.class);
            if (oMyFieldAnnotation != null && "export".equals(oMyFieldAnnotation.desc())) {
                list.add(oMyFieldAnnotation);
            }
        }
        //排序一下
        Collections.sort(list, new Comparator<MyFieldAnnotation>() {
            @Override
            public int compare(MyFieldAnnotation o1, MyFieldAnnotation o2) {
                // TODO Auto-generated method stub
                if (o1.order() < o2.order()) {
                    return -1;
                } else if (o1.order() == o2.order()) {
                    return 0;
                } else {
                    return 1;
                }
            }

        });
        List<String> methodList = new ArrayList<String>();
        //排序后 获得方法
        for (MyFieldAnnotation annotation : list) {
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                MyFieldAnnotation oMyFieldAnnotation = field.getAnnotation(MyFieldAnnotation.class);
                if (oMyFieldAnnotation != null && "export".equals(oMyFieldAnnotation.desc())
                        && oMyFieldAnnotation.order() == annotation.order()) {
                    String fieldName = field.getName();
                    methodList.add("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                }
            }
        }

        return methodList;
    }
}
