package com.javashitang.part4.main;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part4Main2 {

    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config4.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(true);
            Map<String, Object> searchMap = new HashMap<>();
            searchMap.put("ids", Arrays.asList(1, 2));
            List<Object> result = sqlSession.selectList("selectBookByIdV2", searchMap);
            System.out.println(result.size());
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
