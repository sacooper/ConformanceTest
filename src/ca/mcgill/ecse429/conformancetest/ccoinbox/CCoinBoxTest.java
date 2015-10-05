package ca.mcgill.ecse429.conformancetest.ccoinbox;
import ca.mcgill.ecse429.conformancetest.ccoinbox.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CCoinBoxTest {
  private CCoinBox klazz;

  @Before
  public void setUp() throws Exception {
    klazz = new CCoinBox();
    assertEquals("empty", klazz.getStateFullName());
  }
  @Test
  public void testCase0() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o5_0 = klazz.getCurQtrs() + 1;
    Object o5_1 = true;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o5_0);
    assertEquals(klazz.getAllowVend() , o5_1);
    assertEquals("allowed", klazz.getStateFullName());
    klazz.setCurQtrs(3 + 1);
    Object o11_0 = klazz.getTotalQtrs() + 2;
    Object o11_1 = klazz.getCurQtrs() - 2;
    klazz.vend();
    assertEquals(klazz.getTotalQtrs() , o11_0);
    assertEquals(klazz.getCurQtrs() , o11_1);
    assertEquals("allowed", klazz.getStateFullName());
  }

  @Test
  public void testCase1() {
    klazz.returnQtrs();
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase2() {
    Object o1_0 = 0;
    klazz.reset();
    assertEquals(klazz.getTotalQtrs() , o1_0);
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase3() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o3_0 = 0;
    klazz.returnQtrs();
    assertEquals(klazz.getCurQtrs() , o3_0);
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase4() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o4_0 = 0;
    Object o4_1 = 0;
    klazz.reset();
    assertEquals(klazz.getTotalQtrs() , o4_0);
    assertEquals(klazz.getCurQtrs() , o4_1);
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase5() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o5_0 = klazz.getCurQtrs() + 1;
    Object o5_1 = true;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o5_0);
    assertEquals(klazz.getAllowVend() , o5_1);
    assertEquals("allowed", klazz.getStateFullName());
    Object o6_0 = 0;
    Object o6_1 = false;
    klazz.returnQtrs();
    assertEquals(klazz.getCurQtrs() , o6_0);
    assertEquals(klazz.getAllowVend() , o6_1);
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase6() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o5_0 = klazz.getCurQtrs() + 1;
    Object o5_1 = true;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o5_0);
    assertEquals(klazz.getAllowVend() , o5_1);
    assertEquals("allowed", klazz.getStateFullName());
    Object o7_0 = 0;
    Object o7_1 = 0;
    Object o7_2 = false;
    klazz.reset();
    assertEquals(klazz.getTotalQtrs() , o7_0);
    assertEquals(klazz.getCurQtrs() , o7_1);
    assertEquals(klazz.getAllowVend() , o7_2);
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase7() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o5_0 = klazz.getCurQtrs() + 1;
    Object o5_1 = true;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o5_0);
    assertEquals(klazz.getAllowVend() , o5_1);
    assertEquals("allowed", klazz.getStateFullName());
    Object o8_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o8_0);
    assertEquals("allowed", klazz.getStateFullName());
  }

  @Test
  public void testCase8() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o5_0 = klazz.getCurQtrs() + 1;
    Object o5_1 = true;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o5_0);
    assertEquals(klazz.getAllowVend() , o5_1);
    assertEquals("allowed", klazz.getStateFullName());
    klazz.setCurQtrs(2);
    Object o9_0 = klazz.getTotalQtrs() + 2;
    Object o9_1 = 0;
    Object o9_2 = false;
    klazz.vend();
    assertEquals(klazz.getTotalQtrs() , o9_0);
    assertEquals(klazz.getCurQtrs() , o9_1);
    assertEquals(klazz.getAllowVend() , o9_2);
    assertEquals("empty", klazz.getStateFullName());
  }

  @Test
  public void testCase9() {
    Object o2_0 = klazz.getCurQtrs() + 1;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o2_0);
    assertEquals("notAllowed", klazz.getStateFullName());
    Object o5_0 = klazz.getCurQtrs() + 1;
    Object o5_1 = true;
    klazz.addQtr();
    assertEquals(klazz.getCurQtrs() , o5_0);
    assertEquals(klazz.getAllowVend() , o5_1);
    assertEquals("allowed", klazz.getStateFullName());
    klazz.setCurQtrs(3);
    Object o10_0 = klazz.getTotalQtrs() + 2;
    Object o10_1 = 1;
    Object o10_2 = false;
    klazz.vend();
    assertEquals(klazz.getTotalQtrs() , o10_0);
    assertEquals(klazz.getCurQtrs() , o10_1);
    assertEquals(klazz.getAllowVend() , o10_2);
    assertEquals("notAllowed", klazz.getStateFullName());
  }

}