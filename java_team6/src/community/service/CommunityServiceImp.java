package community.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.CommunityDAO;

public class CommunityServiceImp implements CommunityService {
	
	private CommunityDAO communityDao;
	
	public CommunityServiceImp() {
		String resource = "community/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			communityDao = session.getMapper(CommunityDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
