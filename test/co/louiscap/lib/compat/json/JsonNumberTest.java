/*
 Copyright (c) 2015, Louis Capitanchik
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.

 * Neither the name of Affogato nor the names of its associated properties or
 contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package co.louiscap.lib.compat.json;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Louis Capitanchik &lt;contact@louiscap.co&gt;
 */
public class JsonNumberTest {
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println();
        System.out.println("TESTING :: JsonNumber");
    }

    /**
     * Test of getType method, of class JsonNumber.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        JsonNumber instance = JsonNumber.fromInteger(0);
        JsonType expResult = JsonType.NUMBER;
        JsonType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of fromDouble method, of class JsonNumber.
     */
    @Test
    public void testFromDouble() {
        System.out.println("fromDouble");
        
        double src = 5.1d;
        String str = "5.1";
        JsonNumber result = JsonNumber.fromDouble(src);
        
        assertEquals(str, result.toJsonString());
        assertEquals(src, result.val);
    }
    /**
     * Test of fromDouble method, of class JsonNumber.
     */
    @Test
    public void testFromFloat() {
        System.out.println("fromFloat");
        
        float src = 8.7f;
        String str = "8.7";
        JsonNumber result = JsonNumber.fromFloat(src);
        
        assertEquals(str, result.toJsonString());
        assertEquals(src, result.val);
    }

    /**
     * Test of fromInteger method, of class JsonNumber.
     */
    @Test
    public void testFromInteger() {
        System.out.println("fromInteger");
        
        int src = 1378;
        String str = "1378";
        JsonNumber result = JsonNumber.fromInteger(src);
        
        assertEquals(str, result.toJsonString());
        assertEquals(src, result.val);
    }

    /**
     * Test of fromLong method, of class JsonNumber.
     */
    @Test
    public void testFromLong() {
        System.out.println("fromLong");
        
        long src = 1378124l;
        String str = "1378124";
        JsonNumber result = JsonNumber.fromLong(src);
        
        assertEquals(str, result.toJsonString());
        assertEquals(src, result.val);
    }
}
