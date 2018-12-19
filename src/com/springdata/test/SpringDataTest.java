package com.springdata.test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class SpringDataTest {

	//��Ա����
	private ApplicationContext  ctx = null;
	PersonRepsotory personRepsotory = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepsotory = ctx.getBean(PersonRepsotory.class);
	}
	
	/*
	 * ռλ����ʹ��%����ģ����ѯ
	 */
	@Test
	public void testQueryAnnotationLikeParam() {
//		List<Person> Param = personRepsotory.testQueryAnnotationLikeParam("%c%", "%bb%");
//		System.out.println(Param);
		
		List<Person> Param = personRepsotory.testQueryAnnotationLikeParam("c", "bb");
		System.out.println(Param);
	}
	/*
	 * @Query���ݲ���--ռλ��:��˳�򴫵ݲ���
	 */
	@Test
	public void testQueryAnnotationParms1() {
		List<Person> Parms1 = personRepsotory.testQueryAnnotationParms1("tom", "tom@163.com");
		System.out.println(Parms1);
	}
	
	/*
	 * @Query���ݲ���--���������ķ�ʽ
	 */
	@Test
	public void testQueryAnnotationParms2() {
		List<Person> Parms2 = personRepsotory.testQueryAnnotationParms2("tom@163.com", "tom");
		System.out.println(Parms2);
	}
	
	/*
	 * ������ѯ�ķ���:��ĳ�ַ���ͷ����idС��?�ķ���
	 */
	@Test
	public void testKeyWords() {
		List<Person> person = personRepsotory.getByLastNameStartingWithAndIdLessThan("c", 6);
		System.out.println(person);
		
		//(��һ��ģ����������idС�ڵ���������ѯ)����
		List<Person> person1 = personRepsotory.getByLastNameEndingWithAndIdLessThan("c", 6);
		System.out.println(person1);
		
		//�ؼ���or
		List<Person> person3 = personRepsotory.getByEmailInOrBirthLessThan(Arrays.asList("tom@163.com","bb@163.com"), new Date());
		System.out.println("size:"+person3.size());
	}
	
	/**
	 * ���ϲ�ѯ
	 */
	@Test
	public void testKeyWords2() {
		List<Person> person = personRepsotory.getByAddressIdGreaterThan(1);
		System.out.println(person);
	}
	
	/*
	 *����lastName��ѯPerson���� 
	 */
	@Test
	public void testGet() {
		Person person = personRepsotory.getByLastName("tom");
		System.out.println(person);
	}
	
	/*
	 *�������ݿ��ķ��� 
	 */
	@Test
	public void testJpa() {
		
	}
	/**
	 * �������ݿ��Ƿ���������
	 * @throws SQLException
	 */
	@Test
	void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
