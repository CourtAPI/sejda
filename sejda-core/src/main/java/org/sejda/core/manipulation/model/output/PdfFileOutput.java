/*
 * Created on 30/mag/2010
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
package org.sejda.core.manipulation.model.output;

import java.io.File;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.sejda.core.validation.constraint.PdfFile;

/**
 * {@link File} output destination
 * 
 * @author Andrea Vacondio
 * 
 */
public final class PdfFileOutput implements TaskOutput {

    @PdfFile
    private final File file;

    private PdfFileOutput(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public OutputType getOutputType() {
        return OutputType.FILE_OUTPUT;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(getOutputType()).append(file).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(file).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PdfFileOutput)) {
            return false;
        }
        PdfFileOutput output = (PdfFileOutput) other;
        return new EqualsBuilder().append(file, output.getFile()).isEquals();
    }

    /**
     * Creates a new instance of a PdfFileOutput using the input file
     * 
     * @param file
     * @return the newly created instance
     * @throws IllegalArgumentException
     *             if the input file is null or not a file
     */
    public static PdfFileOutput newInstance(File file) {
        if (file == null || !file.isFile()) {
            throw new IllegalArgumentException("A valid instance is expected (not null && isFile).");
        }
        return new PdfFileOutput(file);
    }

}
