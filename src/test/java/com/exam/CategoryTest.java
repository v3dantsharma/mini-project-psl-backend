package com.exam;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;

@SpringBootTest
class CategoryTest {
	
	@Autowired
	private CategoryService categoryservice;
	
	static long cid;
	
	@Test
	void testAddCategory() {
		Category category = new Category();
		category.setTitle("test");
		
		category = this.categoryservice.addCategory(category);
		cid = category.getCid();
		
		assertThat(this.categoryservice.getCategory(cid).getTitle()).isEqualTo("test");
	}

	@Test
	void testUpdate() {
		System.out.println(cid);
		Category category = this.categoryservice.getCategory(cid);
		category.setTitle("test1");
		this.categoryservice.updateCategory(category);
		assertThat(this.categoryservice.getCategory(cid).getTitle()).isEqualTo("test1");
		this.categoryservice.deleteCategory(cid);
	}
	
//	@Test
//	void testDelete() {
//		Category category = this.categoryservice.getCategory(cid);
//		this.categoryservice.deleteCategory(cid);
//		category = this.categoryservice.getCategory(cid);
//		assertThat(category).isNull();
//	}
}
