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
package co.louiscap.lib.compat.arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Louis Capitanchik &lt;contact@louiscap.co&gt;
 */
public class ArrayUtilTest {
    
    public ArrayUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Test of combineStringArrWithSep method, of class ArrayUtil.
     */
    @Test
    public void testCombineStringArrWithSepSpaces() {
        System.out.println("Spaces combineStringArrWithSep");
        String[] arr = {"This", "is", "a", "sentance"};
        String sep = " ";
        String expResult = "This is a sentance";
        String result = ArrayUtil.combineStringArrWithSep(arr, sep);
        assertEquals(expResult, result);
    }

    /**
     * Test of combineStringArrWithSep method, of class ArrayUtil.
     */
    @Test
    public void testCombineStringArrWithSepMultiChar() {
        System.out.println("Multiple Characters combineStringArrWithSep");
        String[] arr = {"Jam", "Eggs", "Beans"};
        String sep = ", ";
        String expResult = "Jam, Eggs, Beans";
        String result = ArrayUtil.combineStringArrWithSep(arr, sep);
        assertEquals(expResult, result);
    }

    /**
     * Test of combineStringArrWithSep method, of class ArrayUtil.
     */
    @Test
    public void testCombineStringArrWithSepNone() {
        System.out.println("No Characters combineStringArrWithSep");
        String[] arr = {"anti", "dises", "tablish", "mentar", "ianism"};
        String sep = "";
        String expResult = "antidisestablishmentarianism";
        String result = ArrayUtil.combineStringArrWithSep(arr, sep);
        assertEquals(expResult, result);
    }
    
}
