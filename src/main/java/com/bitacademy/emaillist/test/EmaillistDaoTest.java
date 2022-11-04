package com.bitacademy.emaillist.test;

import java.util.List;

import com.bitacademy.dao.emaillist.EmaillistDao;
import com.bitacademy.emaillist.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		testFindAll();
	}

	private static void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for(EmaillistVo vo : list) {
			System.out.println(vo);
		}
	}
	
	

}
