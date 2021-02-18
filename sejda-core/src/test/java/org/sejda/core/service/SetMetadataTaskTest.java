/*
 * Created on 09/lug/2010
 *
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * This file is part of the Sejda source code
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.core.service;

import org.junit.Ignore;
import org.junit.Test;
import org.sejda.model.input.PdfSource;
import org.sejda.model.output.ExistingOutputPolicy;
import org.sejda.model.parameter.SetMetadataParameters;
import org.sejda.model.pdf.PdfMetadataFields;
import org.sejda.model.pdf.PdfVersion;
import org.sejda.sambox.pdmodel.PDDocument;
import org.sejda.sambox.pdmodel.PDDocumentInformation;
import org.sejda.sambox.util.DateConverter;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test unit for the set metadata task
 * 
 * @author Andrea Vacondio
 * 
 */
@Ignore
public abstract class SetMetadataTaskTest extends BaseTaskTest<SetMetadataParameters> {
    private SetMetadataParameters parameters = new SetMetadataParameters();

    private void setUpParams(PdfSource<?> source) {
        parameters.setCompress(true);
        parameters.setVersion(PdfVersion.VERSION_1_7);
        parameters.put(PdfMetadataFields.AUTHOR, "test_author");
        parameters.put(PdfMetadataFields.KEYWORDS, "test_keywords");
        parameters.put(PdfMetadataFields.SUBJECT, "test_subject");
        parameters.put(PdfMetadataFields.TITLE, "test_title");
        parameters.put("CreationDate", "D:20150814090348+02'00'");
        parameters.put("ModDate", "D:20170814090348+02'00'");
        parameters.put("Producer", "test_producer");
        parameters.put("Custom field", "custom_field_value");
        parameters.setSource(source);
        parameters.setExistingOutputPolicy(ExistingOutputPolicy.OVERWRITE);
    }

    @Test
    public void testExecute() throws IOException {
        setUpParams(shortInput());
        doExecute();
    }

    @Test
    public void testExecuteEncrypted() throws IOException {
        setUpParams(stronglyEncryptedInput());
        doExecute();
    }

    @Test
    public void removingField() throws IOException {
        SetMetadataParameters parameters = new SetMetadataParameters();
        parameters.addFieldsToRemove(Arrays.asList("Creator", "Author", "RandomStringThatDoesNotExist"));
        parameters.setSource(shortInput());
        
        parameters.setExistingOutputPolicy(ExistingOutputPolicy.OVERWRITE);
        testContext.pdfOutputTo(parameters);
        execute(parameters);
        
        PDDocument document = testContext.assertTaskCompleted();
        PDDocumentInformation info = document.getDocumentInformation();
        assertNull(info.getAuthor());
        assertNull(info.getCreator());
    }

    private void doExecute() throws IOException {
        testContext.pdfOutputTo(parameters);
        execute(parameters);
        PDDocument document = testContext.assertTaskCompleted();
        testContext.assertCreator().assertVersion(PdfVersion.VERSION_1_7);
        PDDocumentInformation info = document.getDocumentInformation();
        assertEquals("test_author", info.getAuthor());
        assertEquals("test_keywords", info.getKeywords());
        assertEquals("test_subject", info.getSubject());
        assertEquals("test_title", info.getTitle());
        assertEquals(DateConverter.toCalendar("D:20150814090348+02'00'"), info.getCreationDate());
        assertEquals("custom_field_value", info.getCustomMetadataValue("Custom field"));

        assertEquals("test_producer", info.getProducer());
        assertEquals(DateConverter.toCalendar("D:20170814090348+02'00'"), info.getModificationDate());
    }

}
