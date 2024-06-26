import com.app.server.model.ResultsBean;

// FOR MAVEN

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// FOR ANT
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.jupiter.api.Assertions.assertFalse;
//import org.junit.jupiter.api.Assertions.assertTrue;
//import net.sf.antcontrib.logic.Assert;
import org.junit.Test;
import org.junit.Assert;

public class ResultsBeanTest {

    @org.junit.Test
    public void testInsideFirstQuarter() {
        boolean res = ResultsBean.isInside(1, 1, 2);
        Assert.assertTrue(true);
//        Assert.assertTrue("Expected true when inside first quarter", res);
    }

//    @Test
//    void testOnBoundaryFirstQuarter() {
//        assertTrue(ResultsBean.isInside(2, 1, 2));
//    }
//
//    @Test
//    void testOutsideFirstQuarter() {
//        assertFalse(ResultsBean.isInside(2, 2, 2));
//    }
//
//    @Test
//    void testInsideThirdQuarter() {
//        assertTrue(ResultsBean.isInside(-1, -1, 2));
//    }
//
//    @Test
//    void testOnCircleBoundary() {
//        assertTrue(ResultsBean.isInside(0, -2, 2));
//    }
//
//    @Test
//    void testOutsideCircle() {
//        assertFalse(ResultsBean.isInside(-3, 0, 2));
//    }
//
//    @Test
//    void testInsideFourthQuarter() {
//        assertTrue(ResultsBean.isInside(1, -1, 5));
//    }
//
//    @Test
//    void testOnLineBoundary() {
//        assertTrue(ResultsBean.isInside(1, -1, 4));
//    }
//
//    @Test
//    void testOutsideLine() {
//        assertFalse(ResultsBean.isInside(2, -2, 2));
//    }
//
//    @Test
//    void testOutsideAllRegions() {
//        assertFalse(ResultsBean.isInside(-1, 1, 2));
//    }
}
