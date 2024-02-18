package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.CommunityDAO;
import community.model.vo.Board;
import community.model.vo.Post;
import community.pagination.Criteria;

public class CommunityServiceImp implements CommunityService {
	
	private CommunityDAO communityDao;
	private InputStream inputStream;
	private SqlSession session;
	
	public CommunityServiceImp() {
		String resource = "community/config/mybatis-config.xml";
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
