package com.alex.mapper;

import com.alex.pojo.DegreeStatDTO;
import com.alex.pojo.Student;
import com.alex.pojo.StudentQueryReuslt;
import com.alex.pojo.ViolationData;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    public List<Student> list(StudentQueryReuslt studentQueryReuslt);

    public void insert(Student student);

    public Student getById(Integer id);

    public void update(Student student);

    public void delete(List<Integer> ids);

    public ViolationData countViolation(Integer id);

    public void updateViolation(Integer violationCount,Integer violationScore,Integer id);

    public void updateUpdateTime(LocalDateTime updateTime, Integer id);

    @MapKey("pos")
    public List<Map<String,Object>> getStudentCountData();

    @MapKey("pos")
    public List<Map<String,Object>> getStudentDegreeData();


    @Select("SELECT degree AS name, COUNT(*) AS value " +
            "FROM student " +
            "GROUP BY degree " +
            "ORDER BY FIELD(degree, '初中', '高中', '大专', '本科', '硕士')")
    List<DegreeStatDTO> countByDegree();

}