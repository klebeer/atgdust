
package test;

import atg.servlet.ServletTestUtils;
import atg.servlet.ServletTestUtils.TestingDynamoHttpServletRequest;
import atg.servlet.ServletTestUtils.TestingDynamoHttpServletResponse;
import atg.test.AtgDustCase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class SimpleFormHandlerTest extends AtgDustCase {
    private ServletTestUtils mServletTestUtils;

    @Before
    public void setUp() throws Exception {
        // We can reuse this instance for all tests
        if (mServletTestUtils == null)
            mServletTestUtils = new ServletTestUtils();
        copyConfigurationFiles(
                new String[] {
                    "src/test/resources/" + this.getClass().getSimpleName()
                            + "/config".replace("/", File.separator)
                },
                "target/test-classes/" + this.getClass().getSimpleName()
                        + "/config".replace("/", File.separator),
                ".svn");
    }

    @Test
    public void testSimpleFormHandler() throws Exception {
        SimpleFormHandler simpleFormHandler = (SimpleFormHandler) resolveNucleusComponent("/atg/test/SimpleFormHandler");
        assertNotNull(simpleFormHandler);
        Map<String, String> params = new HashMap<String, String>();
        // Add a request parameter
        String redirectPage = "/success.jsp";
        params.put(SimpleFormHandler.REDIRECT_URL_PARAM_NAME,redirectPage);
        // let's try 128k, should be more than enough
        int bufferSize = 128 * 1024;
        // Setup request/response pair
        TestingDynamoHttpServletRequest request = mServletTestUtils.createDynamoHttpServletRequest(params, bufferSize, "GET");
        TestingDynamoHttpServletResponse response = mServletTestUtils.createDynamoHttpServletResponse();
        response.setRequest(request);
        request.prepareForRead();
        // invoke handleRedirect
        assertFalse(simpleFormHandler.handleRedirect(request,response));    
      }
}
