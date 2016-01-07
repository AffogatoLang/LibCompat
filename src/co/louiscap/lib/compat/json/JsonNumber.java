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

/**
 * @author Louis Capitanchik
 * @param <T> The type of number (boxed) that is being held
 */
public abstract class JsonNumber<T> extends BoxedJsonValue<T> {
    
    protected JsonNumber (T src) {
        super(src);
    }
    
    @Override
    public JsonType getType() {
        return JsonType.NUMBER;
    }
    
    /**
     * Create a JsonNumber from a primitive double
     * @param src The double to represent as a JsonNumber
     * @return A valid JsonNumber that represents a Double
     */
    public static JsonNumber fromDouble (double src) {
        return new DoubleNumber(src);
    }
    
    /**
     * Create a JsonNumber from a primitive float
     * @param src The double to represent as a JsonNumber
     * @return A valid JsonNumber that represents a Float
     */
    public static JsonNumber fromFloat (float src) {
        return new DoubleNumber(src);
    }
    
    /**
     * Create a JsonNumber from a primitive int
     * @param src The double to represent as a JsonNumber
     * @return A valid JsonNumber that represents an Integer
     */
    public static JsonNumber fromInteger (int src) {
        return new LongNumber(src);
    }
    
    /**
     * Create a JsonNumber from a primitive long
     * @param src The long to represent as a JsonNumber
     * @return A valid JsonNumber that represents a Long
     */
    public static JsonNumber fromLong (long src) {
        return new LongNumber(src);
    }
    
    private static class DoubleNumber extends JsonNumber<Double> {
        public DoubleNumber(double src) {
            super(src);
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof DoubleNumber) {
                return val.equals(((DoubleNumber)o).val);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return val.hashCode();
        }
    }
    
    private static class LongNumber extends JsonNumber<Long> {
        public LongNumber(long src) {
            super(src);
        }
        
        @Override
        public boolean equals(Object o) {
            if(o instanceof LongNumber) {
                return val.equals(((LongNumber)o).val);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return val.hashCode();
        }
    }
}
