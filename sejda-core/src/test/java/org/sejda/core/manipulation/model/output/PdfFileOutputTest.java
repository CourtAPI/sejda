/*
 * Created on 23/gen/2011
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.core.manipulation.model.output;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sejda.core.TestUtils;

/**
 * @author Andrea Vacondio
 * 
 */
public class PdfFileOutputTest {

    private File file;

    @Before
    public void setUp() {
        file = mock(File.class);
        when(file.isFile()).thenReturn(Boolean.TRUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFile() {
        PdfFileOutput.newInstance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFile() {
        when(file.isFile()).thenReturn(Boolean.FALSE);
        PdfFileOutput.newInstance(file);
    }

    @Test
    public void testValidFile() {
        PdfFileOutput instance = PdfFileOutput.newInstance(file);
        assertNotNull(instance);
    }

    @Test
    public void testEquals() {
        File diffFile = mock(File.class);
        when(diffFile.isFile()).thenReturn(Boolean.TRUE);
        PdfFileOutput eq1 = PdfFileOutput.newInstance(file);
        PdfFileOutput eq2 = PdfFileOutput.newInstance(file);
        PdfFileOutput eq3 = PdfFileOutput.newInstance(file);
        PdfFileOutput diff = PdfFileOutput.newInstance(diffFile);
        TestUtils.testEqualsAndHashCodes(eq1, eq2, eq3, diff);
    }
}
