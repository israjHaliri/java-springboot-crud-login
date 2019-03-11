package com.haliri.israj.appcore.content;

import com.haliri.israj.appcore.domain.content.Profile;
import com.haliri.israj.appcore.strategy.content.impl.ProfileStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileStrategyTests {

	@Autowired
    ProfileStrategy profileStrategy;

	@Test
	public void getDataTests() throws SQLException {
		profileStrategy.getData();
	}

	@Test
	public void inserProfile() throws SQLException {
		Profile profile = new Profile();
		profile.setAddress("jln goal para");
		profile.setEmail("admin.content@gmail.com");
		profile.setLat(new Float(-6.121435));
		profile.setLon(new Float(106.774124));
		profile.setPhone("+6285862624149");
//		profileStrategy.saveOrUpdate(profile);
	}
}
