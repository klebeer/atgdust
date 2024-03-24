/**
 * Copyright 2008 ATG DUST Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package test;

import atg.test.AtgDustCase;
import com.mycompany.SimpleComponent;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

/**
 * 
 * Unit test to see if the framework can 'handle' session and request based
 * components.
 * 
 * <br/><br/>Based on {@link AtgDustCase}
 * 
 * @author robert
 * 
 */
public class SimpleComponentTest extends AtgDustCase {

  private SimpleComponent simpleComponent;

    @Before
    public void setUp() throws Exception {


        // The different exclude pattern (.svn, CVS) is needed in
    // here because some other tests in this package (SimpleRepositoryTest)
    // don't use
    // AtgDusCase and therefore bypass the whole optimized property copying
    // way implemented in AtgDusCase. This will result in the original property
    // file with scope request overwriting the chnaged property file with forced
    // global scope. Because the pattern is different from the other tests a
    // re-copy and re-global-scope-force will be executed.

    copyConfigurationFiles(new String[] { "src/test/resources/config".replace(
        "/", File.separator) }, "target/test-classes/config".replace("/",
        File.separator), ".svn", "CVS");

    simpleComponent = (SimpleComponent) resolveNucleusComponent("/test/SimpleComponentRequestScope");

  }

  /**
   * Can it handle a non globally scoped component?
   * 
   */
  @Test
  public void testSimpleComponent() {
    assertNotNull(simpleComponent);
  }

  /**
   * Test to see if the output says something about "No config copy, because
   * they are still the same" ?
   * 
   */
  @Test
  public void testSimpleComponentAgain() {
    assertNotNull(simpleComponent);
  }

}
