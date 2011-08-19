/*
 * Created on 03/ago/2011
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
package org.sejda.core.manipulation.model.parameter;

import static org.mockito.Mockito.mock;

import java.io.InputStream;

import org.junit.Test;
import org.sejda.core.TestUtils;
import org.sejda.core.manipulation.model.input.PdfSource;
import org.sejda.core.manipulation.model.input.PdfStreamSource;
import org.sejda.core.manipulation.model.output.TaskOutput;

/**
 * @author Andrea Vacondio
 * 
 */
public class SimpleSplitParametersTest {

    @Test
    public void testEquals() {
        SimpleSplitParameters eq1 = new SimpleSplitParameters(SimpleSplitType.BURST);
        SimpleSplitParameters eq2 = new SimpleSplitParameters(SimpleSplitType.BURST);
        SimpleSplitParameters eq3 = new SimpleSplitParameters(SimpleSplitType.BURST);
        SimpleSplitParameters diff = new SimpleSplitParameters(SimpleSplitType.ODD_PAGES);
        TestUtils.testEqualsAndHashCodes(eq1, eq2, eq3, diff);
    }

    @Test
    public void testInvalidParameters() {
        SimpleSplitParameters victim = new SimpleSplitParameters(null);
        TaskOutput output = mock(TaskOutput.class);
        victim.setOutput(output);
        InputStream stream = mock(InputStream.class);
        PdfSource input = PdfStreamSource.newInstanceNoPassword(stream, "name");
        victim.setSource(input);
        TestUtils.assertInvalidParameters(victim);
    }
}
