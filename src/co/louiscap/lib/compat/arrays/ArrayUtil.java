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

import java.util.Arrays;

/**
 * A series of static utilities to help with common array operations
 * @author Louis Capitanchik
 */
public class ArrayUtil {
    
    /**
     * Takes an array with String elements, and combines them into a single
     * string, with the given separator in between each element
     * @param arr The array of string to combine
     * @param sep The separator to put in between each of the elements
     * @return One of three things depending on the contents of {@code arr}. If
     * {@code arr} is empty, an empty String will be returned. If {@code arr}
     * contains only one element, that element will be returned. Otherwise,
     * the values of {@code arr} will be concatenated, with the provided 
     * separator {@code sep} placed in between each element.
     */
    public static String combineStringArrWithSep(String[] arr, String sep) {
        if(arr.length == 0) {
            return "";
        } else if (arr.length == 1) {
            return arr[0];
        }
        
        StringBuilder s = new StringBuilder();
        s.append(arr[0]);
        
        Arrays.stream(Arrays.copyOfRange(arr, 1, arr.length)).forEach(e -> {
            s.append(sep);
            s.append(e);
        });
        
        return s.toString();
    }
}
