/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// (FYI: Formatted and sorted with Eclipse)

package org.apache.commons.codec.language;

import junit.framework.Assert;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.Test;

/**
 * Tests {@link Soundex}.
 * 
 * <p>Keep this file in UTF-8 encoding for proper Javadoc processing.</p>
 * 
 * @author Apache Software Foundation
 * @version $Id: SoundexTest.java 1157597 2011-08-14 18:58:54Z sebb $
 */
public class SoundexTest extends StringEncoderAbstractTest {

    @Override
    protected StringEncoder createStringEncoder() {
        return new Soundex();
    }

    /**
     * @return Returns the encoder.
     */
    public Soundex getSoundexEncoder() {
        return (Soundex)this.getStringEncoder();
    }

    @Test
    public void testB650() throws EncoderException {
        this.checkEncodingVariations("B650", (new String[]{
            "BARHAM",
            "BARONE",
            "BARRON",
            "BERNA",
            "BIRNEY",
            "BIRNIE",
            "BOOROM",
            "BOREN",
            "BORN",
            "BOURN",
            "BOURNE",
            "BOWRON",
            "BRAIN",
            "BRAME",
            "BRANN",
            "BRAUN",
            "BREEN",
            "BRIEN",
            "BRIM",
            "BRIMM",
            "BRINN",
            "BRION",
            "BROOM",
            "BROOME",
            "BROWN",
            "BROWNE",
            "BRUEN",
            "BRUHN",
            "BRUIN",
            "BRUMM",
            "BRUN",
            "BRUNO",
            "BRYAN",
            "BURIAN",
            "BURN",
            "BURNEY",
            "BYRAM",
            "BYRNE",
            "BYRON",
            "BYRUM"}));
    }

    @Test
    public void testBadCharacters() {
        Assert.assertEquals("H452", this.getSoundexEncoder().encode("HOL>MES"));

    }

    @Test
    public void testDifference() throws EncoderException {
        // Edge cases
        Assert.assertEquals(0, this.getSoundexEncoder().difference(null, null));
        Assert.assertEquals(0, this.getSoundexEncoder().difference("", ""));
        Assert.assertEquals(0, this.getSoundexEncoder().difference(" ", " "));
        // Normal cases
        Assert.assertEquals(4, this.getSoundexEncoder().difference("Smith", "Smythe"));
        Assert.assertEquals(2, this.getSoundexEncoder().difference("Ann", "Andrew"));
        Assert.assertEquals(1, this.getSoundexEncoder().difference("Margaret", "Andrew"));
        Assert.assertEquals(0, this.getSoundexEncoder().difference("Janet", "Margaret"));
        // Examples from http://msdn.microsoft.com/library/default.asp?url=/library/en-us/tsqlref/ts_de-dz_8co5.asp
        Assert.assertEquals(4, this.getSoundexEncoder().difference("Green", "Greene"));
        Assert.assertEquals(0, this.getSoundexEncoder().difference("Blotchet-Halls", "Greene"));
        // Examples from http://msdn.microsoft.com/library/default.asp?url=/library/en-us/tsqlref/ts_setu-sus_3o6w.asp
        Assert.assertEquals(4, this.getSoundexEncoder().difference("Smith", "Smythe"));
        Assert.assertEquals(4, this.getSoundexEncoder().difference("Smithers", "Smythers"));
        Assert.assertEquals(2, this.getSoundexEncoder().difference("Anothers", "Brothers"));
    }

    @Test
    public void testEncodeBasic() {
        Assert.assertEquals("T235", this.getSoundexEncoder().encode("testing"));
        Assert.assertEquals("T000", this.getSoundexEncoder().encode("The"));
        Assert.assertEquals("Q200", this.getSoundexEncoder().encode("quick"));
        Assert.assertEquals("B650", this.getSoundexEncoder().encode("brown"));
        Assert.assertEquals("F200", this.getSoundexEncoder().encode("fox"));
        Assert.assertEquals("J513", this.getSoundexEncoder().encode("jumped"));
        Assert.assertEquals("O160", this.getSoundexEncoder().encode("over"));
        Assert.assertEquals("T000", this.getSoundexEncoder().encode("the"));
        Assert.assertEquals("L200", this.getSoundexEncoder().encode("lazy"));
        Assert.assertEquals("D200", this.getSoundexEncoder().encode("dogs"));
    }

    /**
     * Examples from http://www.bradandkathy.com/genealogy/overviewofsoundex.html
     */
    @Test
    public void testEncodeBatch2() {
        Assert.assertEquals("A462", this.getSoundexEncoder().encode("Allricht"));
        Assert.assertEquals("E166", this.getSoundexEncoder().encode("Eberhard"));
        Assert.assertEquals("E521", this.getSoundexEncoder().encode("Engebrethson"));
        Assert.assertEquals("H512", this.getSoundexEncoder().encode("Heimbach"));
        Assert.assertEquals("H524", this.getSoundexEncoder().encode("Hanselmann"));
        Assert.assertEquals("H431", this.getSoundexEncoder().encode("Hildebrand"));
        Assert.assertEquals("K152", this.getSoundexEncoder().encode("Kavanagh"));
        Assert.assertEquals("L530", this.getSoundexEncoder().encode("Lind"));
        Assert.assertEquals("L222", this.getSoundexEncoder().encode("Lukaschowsky"));
        Assert.assertEquals("M235", this.getSoundexEncoder().encode("McDonnell"));
        Assert.assertEquals("M200", this.getSoundexEncoder().encode("McGee"));
        Assert.assertEquals("O155", this.getSoundexEncoder().encode("Opnian"));
        Assert.assertEquals("O155", this.getSoundexEncoder().encode("Oppenheimer"));
        Assert.assertEquals("R355", this.getSoundexEncoder().encode("Riedemanas"));
        Assert.assertEquals("Z300", this.getSoundexEncoder().encode("Zita"));
        Assert.assertEquals("Z325", this.getSoundexEncoder().encode("Zitzmeinn"));
    }

    /**
     * Examples from http://www.archives.gov/research_room/genealogy/census/soundex.html
     */
    @Test
    public void testEncodeBatch3() {
        Assert.assertEquals("W252", this.getSoundexEncoder().encode("Washington"));
        Assert.assertEquals("L000", this.getSoundexEncoder().encode("Lee"));
        Assert.assertEquals("G362", this.getSoundexEncoder().encode("Gutierrez"));
        Assert.assertEquals("P236", this.getSoundexEncoder().encode("Pfister"));
        Assert.assertEquals("J250", this.getSoundexEncoder().encode("Jackson"));
        Assert.assertEquals("T522", this.getSoundexEncoder().encode("Tymczak"));
        // For VanDeusen: D-250 (D, 2 for the S, 5 for the N, 0 added) is also
        // possible.
        Assert.assertEquals("V532", this.getSoundexEncoder().encode("VanDeusen"));
    }

    /**
     * Examples from: http://www.myatt.demon.co.uk/sxalg.htm
     */
    @Test
    public void testEncodeBatch4() {
        Assert.assertEquals("H452", this.getSoundexEncoder().encode("HOLMES"));
        Assert.assertEquals("A355", this.getSoundexEncoder().encode("ADOMOMI"));
        Assert.assertEquals("V536", this.getSoundexEncoder().encode("VONDERLEHR"));
        Assert.assertEquals("B400", this.getSoundexEncoder().encode("BALL"));
        Assert.assertEquals("S000", this.getSoundexEncoder().encode("SHAW"));
        Assert.assertEquals("J250", this.getSoundexEncoder().encode("JACKSON"));
        Assert.assertEquals("S545", this.getSoundexEncoder().encode("SCANLON"));
        Assert.assertEquals("S532", this.getSoundexEncoder().encode("SAINTJOHN"));

    }

    @Test
    public void testEncodeIgnoreApostrophes() throws EncoderException {
        this.checkEncodingVariations("O165", (new String[]{
            "OBrien",
            "'OBrien",
            "O'Brien",
            "OB'rien",
            "OBr'ien",
            "OBri'en",
            "OBrie'n",
            "OBrien'"}));
    }

    /**
     * Test data from http://www.myatt.demon.co.uk/sxalg.htm
     * 
     * @throws EncoderException
     */
    @Test
    public void testEncodeIgnoreHyphens() throws EncoderException {
        this.checkEncodingVariations("K525", (new String[]{
            "KINGSMITH",
            "-KINGSMITH",
            "K-INGSMITH",
            "KI-NGSMITH",
            "KIN-GSMITH",
            "KING-SMITH",
            "KINGS-MITH",
            "KINGSM-ITH",
            "KINGSMI-TH",
            "KINGSMIT-H",
            "KINGSMITH-"}));
    }

    @Test
    public void testEncodeIgnoreTrimmable() {
        Assert.assertEquals("W252", this.getSoundexEncoder().encode(" \t\n\r Washington \t\n\r "));
    }

    /**
     * Consonants from the same code group separated by W or H are treated as one.
     */
    @Test
    public void testHWRuleEx1() {
        // From
        // http://www.archives.gov/research_room/genealogy/census/soundex.html:
        // Ashcraft is coded A-261 (A, 2 for the S, C ignored, 6 for the R, 1
        // for the F). It is not coded A-226.
        Assert.assertEquals("A261", this.getSoundexEncoder().encode("Ashcraft"));
    }

    /**
     * Consonants from the same code group separated by W or H are treated as one.
     * 
     * Test data from http://www.myatt.demon.co.uk/sxalg.htm
     */
    @Test
    public void testHWRuleEx2() {
        Assert.assertEquals("B312", this.getSoundexEncoder().encode("BOOTHDAVIS"));
        Assert.assertEquals("B312", this.getSoundexEncoder().encode("BOOTH-DAVIS"));
    }

    /**
     * Consonants from the same code group separated by W or H are treated as one.
     * 
     * @throws EncoderException
     */
    @Test
    public void testHWRuleEx3() throws EncoderException {
        Assert.assertEquals("S460", this.getSoundexEncoder().encode("Sgler"));
        Assert.assertEquals("S460", this.getSoundexEncoder().encode("Swhgler"));
        // Also S460:
        this.checkEncodingVariations("S460", (new String[]{
            "SAILOR",
            "SALYER",
            "SAYLOR",
            "SCHALLER",
            "SCHELLER",
            "SCHILLER",
            "SCHOOLER",
            "SCHULER",
            "SCHUYLER",
            "SEILER",
            "SEYLER",
            "SHOLAR",
            "SHULER",
            "SILAR",
            "SILER",
            "SILLER"}));
    }

    /**
     * Examples for MS SQLServer from
     * http://msdn.microsoft.com/library/default.asp?url=/library/en-us/tsqlref/ts_setu-sus_3o6w.asp
     */
    @Test
    public void testMsSqlServer1() {
        Assert.assertEquals("S530", this.getSoundexEncoder().encode("Smith"));
        Assert.assertEquals("S530", this.getSoundexEncoder().encode("Smythe"));
    }

    /**
     * Examples for MS SQLServer from
     * http://support.microsoft.com/default.aspx?scid=http://support.microsoft.com:80/support
     * /kb/articles/Q100/3/65.asp&NoWebContent=1
     * 
     * @throws EncoderException
     */
    @Test
    public void testMsSqlServer2() throws EncoderException {
        this.checkEncodingVariations("E625", (new String[]{"Erickson", "Erickson", "Erikson", "Ericson", "Ericksen", "Ericsen"}));
    }

    /**
     * Examples for MS SQLServer from http://databases.about.com/library/weekly/aa042901a.htm
     */
    @Test
    public void testMsSqlServer3() {
        Assert.assertEquals("A500", this.getSoundexEncoder().encode("Ann"));
        Assert.assertEquals("A536", this.getSoundexEncoder().encode("Andrew"));
        Assert.assertEquals("J530", this.getSoundexEncoder().encode("Janet"));
        Assert.assertEquals("M626", this.getSoundexEncoder().encode("Margaret"));
        Assert.assertEquals("S315", this.getSoundexEncoder().encode("Steven"));
        Assert.assertEquals("M240", this.getSoundexEncoder().encode("Michael"));
        Assert.assertEquals("R163", this.getSoundexEncoder().encode("Robert"));
        Assert.assertEquals("L600", this.getSoundexEncoder().encode("Laura"));
        Assert.assertEquals("A500", this.getSoundexEncoder().encode("Anne"));
    }

    /**
     * https://issues.apache.org/jira/browse/CODEC-54 https://issues.apache.org/jira/browse/CODEC-56
     */
    @Test
    public void testNewInstance() {
        Assert.assertEquals("W452", new Soundex().soundex("Williams"));
    }

    @Test
    public void testNewInstance2() {
        Assert.assertEquals("W452", new Soundex(Soundex.US_ENGLISH_MAPPING_STRING.toCharArray()).soundex("Williams"));
    }

    @Test
    public void testNewInstance3() {
        Assert.assertEquals("W452", new Soundex(Soundex.US_ENGLISH_MAPPING_STRING).soundex("Williams"));
    }

    @Test
    public void testSoundexUtilsConstructable() {
        new SoundexUtils();
    }

    @Test
    public void testSoundexUtilsNullBehaviour() {
        Assert.assertEquals(null, SoundexUtils.clean(null));
        Assert.assertEquals("", SoundexUtils.clean(""));
        Assert.assertEquals(0, SoundexUtils.differenceEncoded(null, ""));
        Assert.assertEquals(0, SoundexUtils.differenceEncoded("", null));
    }

    /**
     * https://issues.apache.org/jira/browse/CODEC-54 https://issues.apache.org/jira/browse/CODEC-56
     */
    @Test
    public void testUsEnglishStatic() {
        Assert.assertEquals("W452", Soundex.US_ENGLISH.soundex("Williams"));
    }

    /**
     * Fancy characters are not mapped by the default US mapping.
     * 
     * http://issues.apache.org/bugzilla/show_bug.cgi?id=29080
     */
    @Test
    public void testUsMappingEWithAcute() {
        Assert.assertEquals("E000", this.getSoundexEncoder().encode("e"));
        if (Character.isLetter('\u00e9')) { // e-acute
            try {
                //         uppercase E-acute
                Assert.assertEquals("\u00c9000", this.getSoundexEncoder().encode("\u00e9"));
                Assert.fail("Expected IllegalArgumentException not thrown");
            } catch (IllegalArgumentException e) {
                // expected
            }
        } else {
            Assert.assertEquals("", this.getSoundexEncoder().encode("\u00e9"));
        }
    }

    /**
     * Fancy characters are not mapped by the default US mapping.
     * 
     * http://issues.apache.org/bugzilla/show_bug.cgi?id=29080
     */
    @Test
    public void testUsMappingOWithDiaeresis() {
        Assert.assertEquals("O000", this.getSoundexEncoder().encode("o"));
        if (Character.isLetter('\u00f6')) { // o-umlaut
            try {
                //         uppercase O-umlaut
                Assert.assertEquals("\u00d6000", this.getSoundexEncoder().encode("\u00f6"));
                Assert.fail("Expected IllegalArgumentException not thrown");
            } catch (IllegalArgumentException e) {
                // expected
            }
        } else {
            Assert.assertEquals("", this.getSoundexEncoder().encode("\u00f6"));
        }
    }
}
