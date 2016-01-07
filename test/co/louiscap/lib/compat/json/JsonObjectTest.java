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
 * @author Louis Capitanchik
 */
public class JsonObjectTest {
    
    static JsonObject deepObject;
    
    /**
     * Creates the following deep object for tests:<br>
     * <pre><code>{
     *  "name":{
     *      "first": "Louis",
     *      "last": "Capitanchik"
     *  },
     *  "likes": {
     *      "music": [
     *          "blah",
     *          "blee",
     *          "blop"
     *      ],
     *      "films": [
     *          "blah",
     *          "blee",
     *          "blop"
     *      ]
     *  },
     *  "dislikes": {
     *      "music": [
     *          "blah",
     *          "blee",
     *          "blop"
     *      ],
     *      "films": [
     *          "blah",
     *          "blee",
     *          "blop"
     *      ]
     *  }
     * }</code></pre>
     */
    @BeforeClass
    public static void setUpClass() {
        deepObject = new JsonObject();
        JsonObject name = new JsonObject();
        name.put("first", new JsonString("Louis"));
        name.put("last", new JsonString("Capitanchik"));
        
        deepObject.put("name", name);
        
        JsonObject likes = new JsonObject();
        JsonArray likesMusic = new JsonArray();
        likesMusic.add(new JsonString("NoFx"));
        likesMusic.add(new JsonString("Vampire Weekend"));
        likes.put("music", likesMusic);
        
        JsonArray likesFilms = new JsonArray();
        likesFilms.add(new JsonString("Pulp Fiction"));
        likesFilms.add(new JsonString("Donny Darko"));
        likes.put("films", likesFilms);
        
        deepObject.put("likes", likes);
        
        JsonObject dislikes = new JsonObject();
        JsonArray dislikesMusic = new JsonArray();
        dislikesMusic.add(new JsonString("Justin Beiber"));
        dislikesMusic.add(new JsonString("Kanye West"));
        dislikes.put("music", dislikesMusic);
        
        JsonArray dislikesFilms = new JsonArray();
        dislikesFilms.add(new JsonString("That One"));
        dislikesFilms.add(new JsonString("That Other One"));
        dislikes.put("films", dislikesFilms);
        
        deepObject.put("dislikes", dislikes);
    }
    
    /**
     * Test of toJsonString when underlying JsonObject has children & nested
     * objects.
     */
    @Test
    public void testToJsonStringNested() {
        System.out.println("Nested toJsonString");
        JsonObject instance = new JsonObject();
        JsonObject nested = new JsonObject();
        nested.put("bar", new JsonString("Hello"));
        instance.put("foo", nested);
        String expResult = "{\"foo\":{\"bar\":\"Hello\"}}";
        String result = instance.toJsonString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toJsonString when underlying JsonObject has simple values and
     * no nested objects
     */
    @Test
    public void testToJsonStringFlat() {
        System.out.println("Flat toJsonString");
        JsonObject instance = new JsonObject();
        instance.put("foo", new JsonString("bar"));
        instance.put("baz", JsonValue.TRUE);
        String expResult = "{\"foo\":\"bar\",\"baz\":true}";
        String result = instance.toJsonString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toJsonString when underlying JsonObject has no values
     */
    @Test
    public void testToJsonStringEmpty() {
        System.out.println("Empty toJsonString");
        JsonObject instance = new JsonObject();
        String expResult = "{}";
        String result = instance.toJsonString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class JsonObject.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        JsonObject instance = new JsonObject();
        JsonType expResult = JsonType.OBJECT;
        JsonType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFromDeepIdentifier method, of class JsonObject.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetFromDeepIdentifierOneLevel() throws Exception {
        System.out.println("One Level getFromDeepIdentifier");
        String ident = "name.first";
        JsonObject instance = deepObject;
        JsonValue expResult = new JsonString("Louis");
        JsonValue result = instance.getFromDeepIdentifier(ident);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of traverseForDeepValue method, of class JsonObject.
//     */
//    @Test
//    public void testTraverseForDeepValue_JsonObject_String() throws Exception {
//        System.out.println("traverseForDeepValue");
//        JsonObject obj = null;
//        String ident = "";
//        JsonValue expResult = null;
//        JsonValue result = JsonObject.traverseForDeepValue(obj, ident);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of traverseForDeepValue method, of class JsonObject.
//     */
//    @Test
//    public void testTraverseForDeepValue_JsonObject_StringArr() throws Exception {
//        System.out.println("traverseForDeepValue");
//        JsonObject obj = null;
//        String[] identParts = null;
//        JsonValue expResult = null;
//        JsonValue result = JsonObject.traverseForDeepValue(obj, identParts);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
