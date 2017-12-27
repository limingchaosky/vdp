/**
 *
 */
package cn.goldencis.vdp.common.mybatis.plugins;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * <br/>
 *
 * @author liuzhh.
 * @date Feb 27, 2017
 * @version 1.0.0-SNAPSHOT
 */
public class PrimariKeyGenerator extends PluginAdapter {

    private String primaryKeyName;

    @Override
    public boolean validate(List<String> arg0) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateGetPrimaryKey(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String primariKey = introspectedTable.getTableConfiguration().getProperty("primariKey");
        if (primariKey != null) {
            primaryKeyName = primariKey;
        }
    }

    protected void generateGetPrimaryKey(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setReturnType(new FullyQualifiedJavaType("java.lang.String"));
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("getPrimaryKey");

        List<Method> methods = topLevelClass.getMethods();
        if (CollectionUtils.isNotEmpty(methods)) {
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase("get" + primaryKeyName)) {
                    method.addBodyLine("return " + m.getName() + "();");
                }
            }
        }
        topLevelClass.addMethod(method);
    }

}
