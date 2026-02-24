package com.alex.mapper;

import com.alex.pojo.ClassQueryResult;
import com.alex.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClassMapper {
    //分页查询
    public List<Clazz> list(ClassQueryResult classQueryResult);

    //保存班级数据
    public void insertClass(Clazz clazz);

    //根据ID进行查询班级
    @Select("SELECT c.*,e.name AS masterName " +
            "FROM clazz c LEFT OUTER JOIN emp e " +
            "ON c.master_id = e.id " +
            "WHERE c.id = #{id}")
    public Clazz getById(Integer id);

    //修改班级数据
    public void update(Clazz clazz);

    //删除班级数据
    public void delete(Integer id);

    //删除班级数据前进行的是否存在关联学生的检测
    public Integer countStudent(Integer id);

    //查询所有班级数据
    @Select("SELECT * FROM clazz")
    public List<Clazz> listAll();

    /**
     * 查询所有班级名称
     */
    @Select("SELECT name FROM clazz ORDER BY id")
    List<String> getAllClazzNames();

    /**
     * 统计每个班级的学生人数（JOIN查询方式）
     */
    @Select("SELECT c.name AS clazzName, COUNT(s.id) AS studentCount " +
            "FROM clazz c " +
            "LEFT JOIN student s ON c.id = s.clazz_id " +
            "GROUP BY c.id, c.name " +
            "ORDER BY c.id")
    List<Map<String, Object>> countStudentByClazz();
}
