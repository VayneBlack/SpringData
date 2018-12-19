package com.springdata.test;

import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

/**
 * 1.Repository是一个空接口,即是一个标记接口
 * 2.若我们定义的接口继承了Repository,则该接口会被IOC容器识别为一个Repository Bean.
 * 纳入到IOC容器中,进而可以在该接口中定义满足一定规范的方法. 3.实际上,也可以通过@RepositoryDefinition
 * 注解来替代继承Repository 接口
 *
 */
//传入的两个泛型:1.要处理的实体类的类型2.主键的类型

@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
public interface PersonRepsotory {

	/*
	 * 在Repository子接口中声明方法 1.不是随便声明的.而需要符合一定的规范 2.查询方法以find | read | get开头
	 * 3.涉及条件查询时,条件的属性用关键字连接 4.要注意的是:条件属性以首字母大写.
	 * 5.支持属性的级联查询(左连接和右连接),若当前类有符合条件的属性,则优先使用,而不使用级联属性. 若需要使用级联属性,则属性之间使用 _ 进行连接.
	 */
	// 根据lastName 来获取对应的Person
	Person getByLastName(String lastName);

	// WHERE lastName LIKE ?% AND id < ?(以一个模糊做开始和id小于的条件做查询)
	List<Person> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

	// WHERE lastName LIKE %? AND id < ?(以一个模糊做结束和id小于的条件做查询)
	List<Person> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

	// WHERE email IN (?,?,?) OR birth < ?
	List<Person> getByEmailInOrBirthLessThan(List<String> emails, Date birth);

	// WHERE a.id > ?
	List<Person> getByAddressIdGreaterThan(Integer id);

	// 查询id值最大的那个Person对象
	// 使用@Query 注解可以自定义 JPQL 语句以实现灵活的查询
	@Query("SELECT p FROM Person p WHERE p.id = (SELECT max(p2.id) FROM Person p2)")
	Person getMaxIdPerson();

	// 为@Query 注解传递参数的方式1:使用占位符.这种方式参数顺序必须一致
	@Query("SELECT p FROM Person p WHERE p.lastName = ? AND p.email = ?")
	List<Person> testQueryAnnotationParms1(String lastName, String email);

	// @Query注解传递参数方式2:命名参数的方式,对参数名字进行绑定,可以不按顺序
	@Query("SELECT p FROM Person p WHERE p.lastName = :lastName AND p.email = :email")
	List<Person> testQueryAnnotationParms2(@Param("email") String email, @Param("lastName") String lastName);

	//SpringData 允许在占位符上添加%%
	@Query("SELECT P FROM Person p WHERE p.lastName LIKE %?1% OR p.email LIKE %?2%")
	List<Person> testQueryAnnotationLikeParam(String lastName, String email);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
