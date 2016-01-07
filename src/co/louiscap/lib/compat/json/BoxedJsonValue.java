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
 * @param <T> The native Java type being represented by this JsonValue
 */
public abstract class BoxedJsonValue<T> implements JsonValue {

    /**
     * The underlying java value represented by this {@link BoxedJsonValue}
     * instance
     */
    protected T val;
    
    /**
     * Creates a new BoxedJsonValue that represents the provided source element
     * @param src The java object that this BoxedJsonValue represents
     */
    public BoxedJsonValue(T src) {
        val = src;
    }
    
    /**
     * Retrieve the java value of this BoxedJsonValue
     * @return The value given either to the constructor, or the most recent
     * call to {@link BoxedJsonValue#set(java.lang.Object) set(T)}
     */
    public T get() {
        return val;
    }
    /**
     * Sets the value represented by this BoxedJsonValue. Will replace the
     * current value being represented; if modifications to the underlying
     * object are needed (adding elements to an ArrayList for example), the
     * element should be retrieved with {@link BoxedJsonValue#get() get()} and
     * modified directly instead.
     * @param src The new value to represent with this instance of
     * BoxedJsonObject
     */
    public void set(T src) {
        val = src;
    }
    
    @Override
    public String toJsonString() {
        return String.valueOf(val);
    }
}
