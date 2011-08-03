/*
 * Created on 17/set/2010
 *
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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sejda.core.TestUtils;
import org.sejda.core.manipulation.model.pdf.PdfVersion;
import org.sejda.core.manipulation.model.pdf.encryption.PdfAccessPermission;

/**
 * @author Andrea Vacondio
 * 
 */
public class EncryptParametersTest {

    @Test
    public void testAdd() {
        EncryptParameters victim = new EncryptParameters();
        victim.addPermission(PdfAccessPermission.ANNOTATION);
        assertEquals(1, victim.getPermissions().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableList() {
        EncryptParameters victim = new EncryptParameters();
        victim.addPermission(PdfAccessPermission.ANNOTATION);
        victim.getPermissions().clear();
    }

    @Test
    public void testClear() {
        EncryptParameters victim = new EncryptParameters();
        victim.addPermission(PdfAccessPermission.ANNOTATION);
        assertEquals(1, victim.getPermissions().size());
        victim.clearPermissions();
        assertEquals(0, victim.getPermissions().size());
    }

    @Test
    public void testEqual() {
        EncryptParameters eq1 = new EncryptParameters();
        eq1.addPermission(PdfAccessPermission.COPY);
        EncryptParameters eq2 = new EncryptParameters();
        eq2.addPermission(PdfAccessPermission.COPY);
        EncryptParameters eq3 = new EncryptParameters();
        eq3.addPermission(PdfAccessPermission.COPY);
        EncryptParameters diff = new EncryptParameters();
        diff.addPermission(PdfAccessPermission.ASSEMBLE);
        diff.setVersion(PdfVersion.VERSION_1_2);
        TestUtils.testEqualsAndHashCodes(eq1, eq2, eq3, diff);
    }
}
