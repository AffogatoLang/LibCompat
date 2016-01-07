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

import co.louiscap.lib.compat.arrays.ArrayUtil;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents an object in the JSON data interchange format
 * @author Louis Capitanchik
 */
public class JsonObject extends HashMap<String, JsonValue> implements JsonValue {

    @Override
    public String toJsonString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        
        this.entrySet().forEach((e) -> {
            s.append(JsonString.asJsonString(e.getKey()));
            s.append(":");
            s.append(e.getValue().toJsonString());
            s.append(",");
        });
        
        if(s.length() > 1) {
            s.replace(s.length()-1, s.length(), "}");
        } else {
            s.append("}");
        }
        return s.toString();
    }

    @Override
    public JsonType getType() {
        return JsonType.OBJECT;
    }
    
    /**
     * <p>Retrieves the JsonValue from JsonObjects nested within this instance,
     * with the given dot-separated string identifier.</p>
     * <p>For Example, using the identifier "foo.bar" on a JsonObject 
     * representing this structure</p>
     * <code>{
     *  "baz": 13,
     *  "foo": {
     *      "bar": "Hello"
     *  }
     * }</code>
     * <p>would return "Hello" wrapped in a JsonString object
     * @param ident The identifier containing one or more levels of object keys,
     * separated with a single dot to denote nested levels of identifiers. e.g.
     * "foo.bar" would retrieve the key "bar" from the "foo" object nested
     * under the root of the JsonObject
     * @return The JsonValue stored under the specified deep identifier, or 
     * {@link co.louiscap.lib.compat.json.JsonValue#NULL JsonValue.Null}
     * if the key does not exist (but has successfully traversed to the end of 
     * the identifier chain)
     * @throws co.louiscap.lib.compat.json.JsonObject.JsonTraversalException If
     * the specified identifier attempts to navigate a level deeper into a 
     * value that is not a JsonObject (the identifier specifies a key that 
     * cannot be reached).
     */
    public JsonValue getFromDeepIdentifier(String ident) throws JsonTraversalException {
        return traverseForDeepValue(this, ident);
    }
    
    /**
     * <p>Retrieves the JsonValue from JsonObjects nested within a given 
     * JsonObject instance, with the given dot-separated string identifier.</p>
     * <p>For Example, using the identifier "foo.bar" on a JsonObject 
     * representing this structure</p>
     * <code>{
     *  "baz": 13,
     *  "foo": {
     *      "bar": "Hello"
     *  }
     * }</code>
     * <p>would return "Hello" wrapped in a JsonString object
     * @param obj The instance of JsonObject to traverse deeper into
     * @param ident The identifier containing one or more levels of object keys,
     * separated with a single dot to denote nested levels of identifiers. e.g.
     * "foo.bar" would retrieve the key "bar" from the "foo" object nested
     * under the root of the JsonObject
     * @return The JsonValue stored under the specified deep identifier, or 
     * {@link co.louiscap.lib.compat.json.JsonValue#NULL JsonValue.Null}
     * if the key does not exist (but has successfully traversed to the end of 
     * the identifier chain)
     * @throws co.louiscap.lib.compat.json.JsonObject.JsonTraversalException 
     * Thrown if the specified identifier attempts to navigate a level deeper 
     * into a value that is not a JsonObject (the identifier specifies a key 
     * that cannot be reached).
     */
    public static JsonValue traverseForDeepValue(JsonObject obj, String ident) throws JsonTraversalException {
        return traverseForDeepValue(obj, ident.split("\\."));
    }
    
    /**
     * <p>Retrieves the JsonValue from JsonObjects nested within a given 
     * JsonObject instance, with the given array being used in place of a dot
     * seperated identifier.</p>
     * <p>For Example, using the identifier "foo.bar" (equivelant 
     * "['foo', 'bar']") on a JsonObject 
     * representing this structure</p>
     * <code>{
     *  "baz": 13,
     *  "foo": {
     *      "bar": "Hello"
     *  }
     * }</code>
     * <p>would return "Hello" wrapped in a JsonString object
     * @param obj The instance of JsonObject to traverse deeper into
     * @param identParts An array of String keys that will be used to traverse
     * the provided JsonObject. The identifier at index 0 will be the key 
     * retrieved from the given JsonObject, and subsequent elements will be used
     * as keys for nested JsonObject instances. If identParts only contains one
     * element, the JsonValue stored under that element will be returned. e.g.
     * "['foo', 'bar']" would retrieve the key "bar" from the "foo" object 
     * nested under the root of the JsonObject
     * @return The JsonValue stored under the specified deep identifier, or 
     * {@link co.louiscap.lib.compat.json.JsonValue#NULL JsonValue.Null}
     * if the key does not exist (but has successfully traversed to the end of 
     * the identifier chain)
     * @throws co.louiscap.lib.compat.json.JsonObject.JsonTraversalException 
     * Thrown if the specified identifier attempts to navigate a level deeper 
     * into a value that is not a JsonObject (the identifier specifies a key 
     * that cannot be reached).
     */
    public static JsonValue traverseForDeepValue(JsonObject obj, String[] identParts) throws JsonTraversalException {
        if(identParts.length < 1) {
            return JsonValue.NULL;
        } else if(identParts.length == 1) {
            return obj.getOrDefault(identParts[0], JsonValue.NULL);
        } else {
            JsonValue val = obj.getOrDefault(identParts[0], JsonValue.NULL);
            if (val.getType().equals(JsonType.OBJECT)) {
                return traverseForDeepValue((JsonObject)val, Arrays.copyOfRange(identParts, 1, identParts.length));
            } else {
                throw new JsonTraversalException(identParts[0], identParts);
            }
        }
    }
    
    /**
     * Represents an attempt to access a property of a JsonValue that is not
     * of type JsonObject (i.e a JsonValue that does not support properties)
     */
    public static class JsonTraversalException extends Exception {
        public JsonTraversalException(String key, String[] stack) {
            super("Cannot find key " + key + " from stack " + ArrayUtil.combineStringArrWithSep(stack, "."));
        }
    }
}
