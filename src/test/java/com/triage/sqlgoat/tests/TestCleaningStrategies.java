
package com.triage.sqlgoat.tests;

import com.google.json.JsonSanitizer;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author dcowden
 */
public class TestCleaningStrategies {
    final String JSON = "{ \"foo\": 2, \"bar\": \"bazz\" }";
    public TestCleaningStrategies() {
    }

    protected String cleanNoHtml(String input){
        return Jsoup.clean(input, Whitelist.none());
    }
    protected String cleanJson(String input){
        return JsonSanitizer.sanitize(input);
    }
    
    @Test
    public void testHtmlXssClean(){
        String bad = "TESTVALUE<img src=\"a\" onError=\"alert('Xss');\">";
        assertEquals(cleanNoHtml(bad),"TESTVALUE");
    }
    
    @Test
    public void testJsonClean(){
        assertEquals(cleanJson(JSON),JSON);
    }
    
    @Test
    public void testJsonBadClean(){
        String json = "{ \"foo\": 2, \"bar\": \"bazz<script>bad</script>\" }";
        assertEquals(cleanJson(json),"{ \"foo\": 2, \"bar\": \"bazz<script>bad<\\/script>\" }");        
    }
}
