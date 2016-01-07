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
        
        System.out.println();
        System.out.println("TESTING :: JsonObject");
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
     * Test for getting an item from one level deep in a JsonObject
     * @throws co.louiscap.lib.compat.json.JsonObject.JsonTraversalException
     */
    @Test
    public void testGetFromDeepIdentifierOneLevel() throws JsonObject.JsonTraversalException {
        System.out.println("One Level getFromDeepIdentifier");
        String ident = "name.first";
        
        JsonValue expResult = new JsonString("Louis");
        JsonValue result = deepObject.getFromDeepIdentifier(ident);
        
        System.out.println("\tExpected " + expResult.toJsonString());
        System.out.println("\tGot " + result.toJsonString());
        assertEquals(expResult, result);
    }

    /**
     * Test for getting an item from root level in a JsonObject
     * @throws co.louiscap.lib.compat.json.JsonObject.JsonTraversalException
     */
    @Test
    public void testGetFromDeepIdentifierFlat() throws JsonObject.JsonTraversalException {
        System.out.println("Flat getFromDeepIdentifier");
        String ident = "name";
        
        JsonObject expResult = new JsonObject();
        expResult.put("first", new JsonString("Louis"));
        expResult.put("last", new JsonString("Capitanchik"));
        
        JsonValue result = deepObject.getFromDeepIdentifier(ident);
        
        System.out.println("\tExpected " + expResult.toJsonString());
        System.out.println("\tGot " + result.toJsonString());
        assertEquals(expResult, result);
    }
    
    /**
     * Test for attempting to access a property at a deeper level that can be reached. Should throw
     * a {@link JsonObject.JsonTraversalException}. This is different than attempting to access a
     * property that does not exist at a level that can be reached (which should return
     * {@link JsonValue#NULL})
     */
    @Test
    public void testGetFromDeepIdentifierTraversalError() {
        System.out.println("Traversal Exception getFromDeepIdentifier");
        try {
            deepObject.getFromDeepIdentifier("likes.music.pop");
            fail("Did not throw Traversal Error for accessing non existant "
                    + "property");
        } catch(JsonObject.JsonTraversalException e) {}
    }
}
