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

	//成员变量
	private ApplicationContext  ctx = null;
	PersonRepsotory personRepsotory = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepsotory = ctx.getBean(PersonRepsotory.class);
	}
	
	/*
	 * 占位符上使用%进行模糊查询
	 */
	@Test
	public void testQueryAnnotationLikeParam() {
//		List<Person> Param = personRepsotory.testQueryAnnotationLikeParam("%c%", "%bb%");
//		System.out.println(Param);
		
		List<Person> Param = personRepsotory.testQueryAnnotationLikeParam("c", "bb");
		System.out.println(Param);
	}
	/*
	 * @Query传递参数--占位符:按顺序传递参数
	 */
	@Test
	public void testQueryAnnotationParms1() {
		List<Person> Parms1 = personRepsotory.testQueryAnnotationParms1("tom", "tom@163.com");
		System.out.println(Parms1);
	}
	
	/*
	 * @Query传递参数--命名参数的方式
	 */
	@Test
	public void testQueryAnnotationParms2() {
		List<Person> Parms2 = personRepsotory.testQueryAnnotationParms2("tom@163.com", "tom");
		System.out.println(Parms2);
	}
	
	/*
	 * 条件查询的方法:以某字符开头并且id小于?的方法
	 */
	@Test
	public void testKeyWords() {
		List<Person> person = personRepsotory.getByLastNameStartingWithAndIdLessThan("c", 6);
		System.out.println(person);
		
		//(以一个模糊做结束和id小于的条件做查询)方法
		List<Person> person1 = personRepsotory.getByLastNameEndingWithAndIdLessThan("c", 6);
		System.out.println(person1);
		
		//关键词or
		List<Person> person3 = personRepsotory.getByEmailInOrBirthLessThan(Arrays.asList("tom@163.com","bb@163.com"), new Date());
		System.out.println("size:"+person3.size());
	}
	
	/**
	 * 联合查询
	 */
	@Test
	public void testKeyWords2() {
		List<Person> person = personRepsotory.getByAddressIdGreaterThan(1);
		System.out.println(person);
	}
	
	/*
	 *根据lastName查询Person对象 
	 */
	@Test
	public void testGet() {
		Person person = personRepsotory.getByLastName("tom");
		System.out.println(person);
	}
	
	/*
	 *生成数据库表的方法 
	 */
	@Test
	public void testJpa() {
		
	}
	/**
	 * 测试数据库是否正常连接
	 * @throws SQLException
	 */
	@Test
	void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
