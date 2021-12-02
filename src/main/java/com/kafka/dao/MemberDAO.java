package com.kafka.dao;

import com.kafka.domain.Eai;

import java.util.List;

/**
 * @author : hoyao
 * @name : hoyao
 * <PRE>
 * </PRE>
 * @class : MemberDAO
 * date : 2021-11-04
 * @History <PRE>
 * NO  Date         time          Author                                      Desc
 * --------------------------------------------------------------------------------------------------------------
 * 1   2021-11-04   오후 1:28     hoyao (hoyaof@lgupluspartners.co.kr)        최초작성
 * </PRE>
 */
public interface MemberDAO {

	// 현재시간체크
	public String getTime();

	List<Eai> eaiListAll();

	int eaiColumnUpdate(Eai result);

	int insert(Eai eai);

	// 상태값 업데이트

}
