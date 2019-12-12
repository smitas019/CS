package com.app.cs.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.app.cs.controller.HomeControllerTest;
import com.app.cs.service.SocialMediaServiceTest;

@RunWith(Suite.class)
@SuiteClasses({HomeControllerTest.class, SocialMediaServiceTest.class})
public class AllTests {

}
