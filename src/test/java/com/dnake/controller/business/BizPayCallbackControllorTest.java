package com.dnake.controller.business;

import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext.xml"})
public class BizPayCallbackControllorTest extends JUnitActionBase{

	@Test
	@Ignore
	public void test() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        request.setRequestURI("/bb/aa/hehe");  
        request.addParameter("P_RQ", "2011-11-29");  
        request.setMethod("GET");  
          
        // 执行URI对应的action    
        this.excuteAction(request, response);  
        String result = response.getContentAsString();  
        Assert.assertNotNull(result);  
		fail("Not yet implemented");
	}

}
