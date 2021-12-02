package com.kafka.dao;

import com.kafka.domain.Eai;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * @author : hoyao
 * @name : hoyao
 * <PRE>
 * </PRE>
 * @class : MemberDAOIMpl
 * date : 2021-11-04
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-04   오후 1:35     hoyao (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */

@Repository
public class MemberDAOImpl implements MemberDAO {

	private static final String NAMESPACE = "com.kafka.mapper.MemberMapper";
	private final SqlSession sqlSession;

	@Inject
	public MemberDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public String getTime() {
		return sqlSession.selectOne(NAMESPACE + ".getTime");
	}

	@Override
	public List<Eai> eaiListAll() {
		return sqlSession.selectList(NAMESPACE + ".kafkaEaiSdw");
	}

	@Override
	public int eaiColumnUpdate(Eai result) {
		System.err.println(result);
		return sqlSession.update(NAMESPACE + ".eaiColumnUpdate", result);
	}

	@Override
	public int insert(Eai eai) {
		return sqlSession.insert(NAMESPACE + ".insertData", eai);
	}


}
