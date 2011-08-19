/*
 * Created on 04/lug/2010
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
package org.sejda.core.manipulation.service;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.sejda.core.Sejda;
import org.sejda.core.manipulation.model.output.PdfFileOutput;
import org.sejda.core.manipulation.model.output.StreamOutput;
import org.sejda.core.manipulation.model.parameter.AbstractParameters;
import org.sejda.core.manipulation.model.pdf.PdfMetadataKey;
import org.sejda.core.manipulation.model.pdf.PdfVersion;

import com.itextpdf.text.pdf.PdfReader;

/**
 * Parent test class with common methods to read results or common asserts.
 * 
 * @author Andrea Vacondio
 * 
 */
@Ignore
public class PdfOutEnabledTest {

    private ByteArrayOutputStream out;
    private File outFile;

    /**
     * Initialize the input parameters with a new {@link StreamOutput}
     * 
     * @param parameters
     */
    void initializeNewStreamOutput(AbstractParameters parameters) {
        out = new ByteArrayOutputStream();
        StreamOutput pdfOut = StreamOutput.newInstance(out);
        parameters.setOutput(pdfOut);
    }

    /**
     * Initialize the input parameters with a new {@link PdfFileOutput}
     * 
     * @param parameters
     * @throws IOException
     */
    void initializeNewFileOutput(AbstractParameters parameters) throws IOException {
        outFile = File.createTempFile("SejdaTest", ".pdf");
        outFile.deleteOnExit();
        PdfFileOutput pdfOut = PdfFileOutput.newInstance(outFile);
        parameters.setOutput(pdfOut);
    }

    /**
     * 
     * @return a {@link PdfReader} opened on temporary output file containing the result of the manipulation.
     * @throws IOException
     */
    protected PdfReader getReaderFromResultFile() throws IOException {
        return new PdfReader(new FileInputStream(outFile));
    }

    /**
     * @param expectedFileName
     *            the expected name of the first file in the ZipInputStream
     * @param ownerPwd
     *            owner password
     * @return a {@link PdfReader} opened on the first resulting file found in the ZipInputStream coming form the manipulation.
     * @throws IOException
     */
    protected PdfReader getReaderFromResultStream(String expectedFileName, byte[] ownerPwd) throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        ZipInputStream zip = new ZipInputStream(input);
        ZipEntry entry = zip.getNextEntry();
        if (StringUtils.isNotBlank(expectedFileName)) {
            assertEquals(expectedFileName, entry.getName());
        }
        return new PdfReader(zip, ownerPwd);
    }

    /**
     * Assert the the generated output zip stream contains the given number of entries.
     * 
     * @param expectedNumberOfDocuments
     * @throws IOException
     */
    protected void assertOutputContainsDocuments(int expectedNumberOfDocuments) throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(out.toByteArray());
        ZipInputStream zip = new ZipInputStream(input);
        int counter = 0;
        while (zip.getNextEntry() != null) {
            counter++;
        }
        assertEquals(expectedNumberOfDocuments, counter);
    }

    /**
     * @param expectedFileName
     *            the expected name of the first file in the ZipInputStream
     * @return a {@link PdfReader} opened on the first resulting file found in the ZipInputStream coming form the manipulation.
     * @throws IOException
     */
    protected PdfReader getReaderFromResultStream(String expectedFileName) throws IOException {
        return getReaderFromResultStream(expectedFileName, null);
    }

    /**
     * @return a {@link PdfReader} opened on the first resulting file found in the ZipInputStream coming form the manipulation.
     * @throws IOException
     */
    protected PdfReader getReaderFromResult() throws IOException {
        return getReaderFromResultStream(null, null);
    }

    /**
     * @param ownerPwd
     *            owner password
     * @return a {@link PdfReader} opened on the first resulting file found in the ZipInputStream coming form the manipulation.
     * @throws IOException
     */
    protected PdfReader getReaderFromResult(byte[] ownerPwd) throws IOException {
        return getReaderFromResultStream(null, ownerPwd);
    }

    /**
     * Assert the correct creator
     * 
     * @param reader
     */
    protected void assertCreator(PdfReader reader) {
        HashMap<String, String> meta = reader.getInfo();
        assertEquals(Sejda.CREATOR, meta.get(PdfMetadataKey.CREATOR.getKey()));
    }

    /**
     * Assert that the version on the read document is the same as the input one
     * 
     * @param reader
     * @param version
     */
    protected void assertVersion(PdfReader reader, PdfVersion version) {
        assertEquals(version.getVersionAsCharacter(), reader.getPdfVersion());
    }
}
