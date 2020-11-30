package de.hfu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class UtilTest 
{
    @Test
    public void testIstErstesHalbjahrGoodNumbers()
    {
        assertTrue(Util.istErstesHalbjahr(1));
        assertTrue(Util.istErstesHalbjahr(4));
        assertTrue(Util.istErstesHalbjahr(6));
        assertTrue(Util.istErstesHalbjahr(2));
        
        assertFalse(Util.istErstesHalbjahr(7));
        assertFalse(Util.istErstesHalbjahr(10));
        assertFalse(Util.istErstesHalbjahr(12));
    }
    
    @Test
    public void testIstErstesHalbjahrBadNumbersMinus()
    {
    	try {
    		Util.istErstesHalbjahr(-1);
    		fail("Erwartete Ausnahme wurde nicht geworfen");
    	}catch(IllegalArgumentException e) {
    	}
    	
    	try {
    		Util.istErstesHalbjahr(13);
    		fail("Erwartete Ausnahme wurde nicht geworfen");
    	}catch(IllegalArgumentException e) {
    	}
    }
    
    @Test(expected=IllegalArgumentException.class, timeout=1000)
    public void testIstErstesHalbjahrBadNumbersPlus()
    {
    	Util.istErstesHalbjahr(0);
    }
}