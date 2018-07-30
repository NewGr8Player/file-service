package com.xavier;

import com.xavier.data.bean.FileInfo;
import com.xavier.data.dao.SystemRelationDao;
import com.xavier.data.service.FileInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastDFSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainTest {

	@Autowired
	private SystemRelationDao systemRelationDao;

	@Autowired
	private FileInfoService fileInfoService;

	@Test
	public void systemRelationDaoTestMethod() {
		this.systemRelationDao.searchBySystemCode("1").forEach(System.out::println);
	}

	@Test
	public void fileInfoServiceTestMethod() {
		FileInfo fileInfo = new FileInfo("1","/group1/bujidaole/1.jpg","re.jpg",1008611L,"img");
		System.out.println(this.fileInfoService.save(fileInfo));
		System.out.println(fileInfo);
	}
}
