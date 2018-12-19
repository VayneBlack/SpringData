package com.springdata.test;

import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

/**
 * 1.Repository��һ���սӿ�,����һ����ǽӿ�
 * 2.�����Ƕ���Ľӿڼ̳���Repository,��ýӿڻᱻIOC����ʶ��Ϊһ��Repository Bean.
 * ���뵽IOC������,���������ڸýӿ��ж�������һ���淶�ķ���. 3.ʵ����,Ҳ����ͨ��@RepositoryDefinition
 * ע��������̳�Repository �ӿ�
 *
 */
//�������������:1.Ҫ�����ʵ���������2.����������

@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
public interface PersonRepsotory {

	/*
	 * ��Repository�ӽӿ����������� 1.�������������.����Ҫ����һ���Ĺ淶 2.��ѯ������find | read | get��ͷ
	 * 3.�漰������ѯʱ,�����������ùؼ������� 4.Ҫע�����:��������������ĸ��д.
	 * 5.֧�����Եļ�����ѯ(�����Ӻ�������),����ǰ���з�������������,������ʹ��,����ʹ�ü�������. ����Ҫʹ�ü�������,������֮��ʹ�� _ ��������.
	 */
	// ����lastName ����ȡ��Ӧ��Person
	Person getByLastName(String lastName);

	// WHERE lastName LIKE ?% AND id < ?(��һ��ģ������ʼ��idС�ڵ���������ѯ)
	List<Person> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

	// WHERE lastName LIKE %? AND id < ?(��һ��ģ����������idС�ڵ���������ѯ)
	List<Person> getByLastNameEndingWithAndIdLessThan(String lastName, Integer id);

	// WHERE email IN (?,?,?) OR birth < ?
	List<Person> getByEmailInOrBirthLessThan(List<String> emails, Date birth);

	// WHERE a.id > ?
	List<Person> getByAddressIdGreaterThan(Integer id);

	// ��ѯidֵ�����Ǹ�Person����
	// ʹ��@Query ע������Զ��� JPQL �����ʵ�����Ĳ�ѯ
	@Query("SELECT p FROM Person p WHERE p.id = (SELECT max(p2.id) FROM Person p2)")
	Person getMaxIdPerson();

	// Ϊ@Query ע�⴫�ݲ����ķ�ʽ1:ʹ��ռλ��.���ַ�ʽ����˳�����һ��
	@Query("SELECT p FROM Person p WHERE p.lastName = ? AND p.email = ?")
	List<Person> testQueryAnnotationParms1(String lastName, String email);

	// @Queryע�⴫�ݲ�����ʽ2:���������ķ�ʽ,�Բ������ֽ��а�,���Բ���˳��
	@Query("SELECT p FROM Person p WHERE p.lastName = :lastName AND p.email = :email")
	List<Person> testQueryAnnotationParms2(@Param("email") String email, @Param("lastName") String lastName);

	//SpringData ������ռλ�������%%
	@Query("SELECT P FROM Person p WHERE p.lastName LIKE %?1% OR p.email LIKE %?2%")
	List<Person> testQueryAnnotationLikeParam(String lastName, String email);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
