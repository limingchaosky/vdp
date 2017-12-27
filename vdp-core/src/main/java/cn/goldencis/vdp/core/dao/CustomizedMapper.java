/**
 *
 */
package cn.goldencis.vdp.core.dao;

import java.util.List;

import cn.goldencis.vdp.core.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.goldencis.vdp.common.annotation.Mybatis;
import cn.goldencis.vdp.core.entity.DepartmentDO;

@Mybatis
public interface CustomizedMapper {

    @Select({"<script>", "SELECT name,id,tree_path treePath FROM t_department", "WHERE id=#{id} ", "</script>"})
    DepartmentDO getDepartmentById(@Param("id") String id);

    /**
     * 添加数据库
     */
    @Insert({"<script>", "INSERT INTO t_user_department (user_id,department_id) values",
            "<foreach item='did' index='index' collection='department' separator=',' >", "(#{userId},#{did})",
            "</foreach>", "</script>"})
    void insertDeptRole(@Param("userId") String userId, @Param("department") List<String> department);

    @Select({
            "<script>",
            "SELECT d.id, d.name, p.name as parentName,d.ip_part as ipPart,d.parent_id as parentId, d.owner,d.department_tel as departmentTel FROM t_department d left join t_department p on d.parent_id=p.id ",
            "WHERE ( d.status=1 ", "<if test='treePath != null'>", "AND d.tree_path like #{treePath} ", "</if>", ")",
            "<if test='pId != null and pId != 1'>", " or (d.id=#{pId}) ", "</if>", "<if test='ordercase != null '>",
            " order by ${ordercase} ", "</if>", "<if test='startNum != null and pageSize != null'>",
            "limit #{startNum},#{pageSize} ", "</if>", "</script>"})
    List<DepartmentDO> getDeptarMentList(@Param("startNum") Integer startNum, @Param("pageSize") Integer pageSize, @Param("pId") Integer pId, @Param("treePath") String treePath, @Param("ordercase") String ordercase);

    @Select({"<script>", "SELECT count(*) FROM t_department d left join t_department p on d.parent_id=p.id ",
            "WHERE ( 1=1 ", "<if test='treePath != null'>", "AND d.tree_path like #{treePath} ", "</if>", ")",
            "<if test='pId != null and pId != 1'>", " or (d.id=#{pId}) ", "</if>", "</script>"})
    long getDeptarMentCount(@Param("pId") Integer pId, @Param("treePath") String treePath);

    List<UserDO> getUserList(UserDO user);

    List<UserDO> getAllUser();

    @Select({
            "<script>",
            "SELECT d.id, d.name, d.user_name as userName,d.phone,d.email, d.role_type as roleType,d.`status` FROM t_department p , t_user_department dp , t_user d    ",
            "WHERE  1=1 AND p.id=dp.department_id AND d.id=dp.user_id  ",
            "<if test='dname != null'>",
            "AND (p.id in (SELECT substring_index(substring_index(td.tree_path,',', 3),',',-1 ) FROM t_department td  WHERE td.name=#{dname}) or p.id=1 OR p.`name`=#{dname} )",
            "</if>", "</script>"})
    List<UserDO> getUserByDname(@Param("dname") String dname);

    @Select({
            "<script>",
            "SELECT d.id, d.name, d.parent_id as parentId,d.ip_part as ipPart ,d.tree_path AS treePath, d.owner,d.department_tel as departmentTel  ",
            "FROM t_user_department ud left join t_department d on ud.department_id=d.id ",
            "WHERE  1=1 AND ud.user_id = #{userId} ", "</script>"})
    List<DepartmentDO> getUserDepartmentByUser(@Param("userId") String userId);

    @Select({
            "<script>",
            "SELECT d.id, d.name, d.parent_id as parentId,d.ip_part as ipPart ,d.tree_path AS treePath, d.owner,d.department_tel as departmentTel  ",
            "FROM  t_department d  ", "WHERE 1 &lt;&gt; 1   ", "<if test='isNodes'>", " OR d.id=1 ", "</if>",
            "<if test='ids != null and ids.size()>0 '>", " OR d.id in ",
            "<foreach item='did' index='index' collection='ids' open='(' separator=',' close=')'>", "#{did}",
            "</foreach>", "</if>", "<if test='treePaths != null and treePaths.size()>0 '>",
            "<foreach item='treePath' index='index' collection='treePaths' >", " OR d.tree_path LIKE #{treePath} ",
            "</foreach>", "</if>", "</script>"})
    List<DepartmentDO> getUserRoleDepartmentByUser(@Param("ids") List<Integer> ids, @Param("treePaths") List<String> treePaths, @Param("isNodes") boolean isNodes);

    /**
     * 与getUserRoleDepartmentByUser以后所不同,不用 like 用INSTR, 只需要传入一级菜单id,减少遍历次数
     *
     * @param ids
     * @return List<DepartmentDO>
     * @author mll
     */
    @Select({
            "<script>",
            "SELECT d.id, d.name, d.parent_id as parentId,d.ip_part as ipPart ,d.tree_path AS treePath, d.owner,d.department_tel as departmentTel  ",
            "FROM  t_department d  WHERE ",
            "<foreach item='did' index='index' collection='ids' open='(' separator='OR' close=')'>",
            " INSTR(d.tree_path,',${did},') OR d.id=#{did}", "</foreach>", "</script>"})
    List<DepartmentDO> getUserRoleDepartmentByParentIdOptimised(@Param("ids") List<String> ids);

    @Select({"<script>", "DELETE FROM t_user_department WHERE user_id=#{userId}", "</script>"})
    void deleteDRole(@Param("userId") Integer userId);

    @Select({"<script>", "DELETE FROM t_user_navigation WHERE user_id=#{userId}", "</script>"})
    void deleteNRole(@Param("userId") Integer userId);

    @Select({
            "<script>",
            "INSERT INTO t_device_info (ip,mac,device_type,nick_name,remark) VALUES (#{ip},#{mac},#{deviceType},#{nickName},#{remark}) ON DUPLICATE KEY UPDATE ",
            "mac=#{mac},device_type=#{deviceType},nick_name=#{nickName},remark=#{remark} ", "</script>"})
    void updateInsert(@Param("ip") String ip, @Param("mac") String mac, @Param("deviceType") String deviceType, @Param("nickName") String nickName, @Param("remark") String remark);

    @Select({"<script>", "SELECT t.id,t.name,t.parent_id,t.tree_path FROM t_department t",
            "WHERE (t.parent_id=#{id} OR t.id=#{id}) AND t.status=1", "</script>"})
    List<DepartmentDO> getChildAndParentByParentId(@Param("id") Integer id);

    int selectCountByUserName(String username);

    void deleteBatchUserGroup(@Param("list") List<String> list);

    void updateByBatchUser(List<String> list);

    List<UserDO> getUserListByType(UserDO user);

    Long countUserList(UserDO user);

    List<UserDO> getUserListByName(UserDO user);

    void deleteByBatchUser(@Param("list") List<String> list);

    List<DepartmentDO> getFunctionNodesByLogin(@Param("list") List<DepartmentDO> list);
}
