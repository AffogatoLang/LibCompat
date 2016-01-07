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
 * Represents the base methods a class needs to implement in order to be
 * compatible with other LibCompat.json JSON types. All implementors must 
 * return the JsonType corresponding to the valid JSON data type that they
 * represent, as defined in RFC 7159
 * @author Louis Capitanchik
 */
public interface JsonValue {
    /**
     * Converts the native java data held by this instance into the a 
     * representation that satisfies the JSON grammar defined for this objects
     * JsonType
     * @return The valid JSON string for this object
     */
    public String toJsonString();
    /**
     * Get the type of JSON value that this object represents. Guaranteed to
     * correspond to the value types defined in RFC 7159
     * @return The valid JSON type of this object
     */
    public JsonType getType();
    
    /**
     * Represents the JSON bareword "true", typically used for storing boolean
     * data
     */
    public static JsonValue TRUE = new JsonValue() {
        @Override
        public String toJsonString() {
            return "true";
        }

        @Override
        public JsonType getType() {
            return JsonType.TRUE;
        }
    };
    
    /**
     * Represents the JSON bareword "false", typically used for storing boolean
     * data
     */
    public static JsonValue FALSE = new JsonValue() {
        @Override
        public String toJsonString() {
            return "false";
        }

        @Override
        public JsonType getType() {
            return JsonType.FALSE;
        }
    };
    
    /**
     * Represents the JSON bareword "null", typically used for showing a lack
     * of data
     */
    public static JsonValue NULL = new JsonValue() {
        @Override
        public String toJsonString() {
            return "null";
        }

        @Override
        public JsonType getType() {
            return JsonType.NULL;
        }
    };
}
