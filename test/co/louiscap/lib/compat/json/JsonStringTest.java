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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Louis Capitanchik
 */
public class JsonStringTest {
    
    /**
     * Test of toJsonString method, of class JsonString.
     */
    @Test
    public void testToJsonString() {
        System.out.println("toJsonString");
        JsonString instance = new JsonString("Hello World!");
        String expResult = "\"Hello World!\"";
        String result = instance.toJsonString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class JsonString.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        JsonString instance = new JsonString("Test");
        JsonType expResult = JsonType.STRING;
        JsonType result = instance.getType();
        assertEquals(expResult, result);
    }
    
    // No test for hashCode and equals because they simply proxy the String methods

    /**
     * Test of asJsonString method, of class JsonString.
     */
    @Test
    public void testAsJsonString() {
        System.out.println("asJsonString");
        String str = "Testing";
        String expResult = "\"Testing\"";
        String result = JsonString.asJsonString(str);
        assertEquals(expResult, result);
    }
    
}
