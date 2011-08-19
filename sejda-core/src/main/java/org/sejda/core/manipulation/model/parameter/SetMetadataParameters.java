/*
 * Created on 09/lug/2010
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.sejda.core.manipulation.model.output.TaskOutput;
import org.sejda.core.manipulation.model.pdf.PdfMetadataKey;
import org.sejda.core.validation.constraint.NotEmpty;
import org.sejda.core.validation.constraint.ValidSingleOutput;

/**
 * Parameter class for the set metadata manipulation.
 * 
 * @author Andrea Vacondio
 * 
 */
@ValidSingleOutput
public final class SetMetadataParameters extends SinglePdfSourceParameters implements SingleOutputDocumentParameter {

    @NotEmpty
    private final Map<PdfMetadataKey, String> metadata = new HashMap<PdfMetadataKey, String>();
    private String outputName;
    @Valid
    @NotNull
    private TaskOutput output;

    public SetMetadataParameters() {
        super();
    }

    /**
     * @param outputName
     *            to be used when the output is not a file destination
     */
    public SetMetadataParameters(String outputName) {
        this.outputName = outputName;
    }

    @Override
    public TaskOutput getOutput() {
        return output;
    }

    @Override
    public void setOutput(TaskOutput output) {
        this.output = output;
    }

    /**
     * @see Map#entrySet()
     * @return an unmodifiable set view of map
     */
    public Set<Entry<PdfMetadataKey, String>> entrySet() {
        return Collections.unmodifiableSet(metadata.entrySet());
    }

    public String getOutputName() {
        return outputName;
    }

    /**
     * @see Map#keySet()
     * @return a unmodifiable set containing keys of the map
     */
    public Set<PdfMetadataKey> keySet() {
        return Collections.unmodifiableSet(metadata.keySet());
    }

    /**
     * @see Map#putAll(Map)
     * @param m
     */
    public void putAll(Map<PdfMetadataKey, String> m) {
        metadata.putAll(m);
    }

    /**
     * adds the key,value
     * 
     * @see Map#put(Object, Object)
     * @param key
     * @param metadata
     */
    public void put(PdfMetadataKey key, String metadata) {
        this.metadata.put(key, metadata);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(metadata).append(outputName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SetMetadataParameters)) {
            return false;
        }
        SetMetadataParameters parameter = (SetMetadataParameters) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(metadata.entrySet(), parameter.entrySet())
                .append(outputName, parameter.getOutputName()).isEquals();
    }
}
