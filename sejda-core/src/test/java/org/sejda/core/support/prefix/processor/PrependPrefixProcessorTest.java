/*
 * Created on 03/lug/2010
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
package org.sejda.core.support.prefix.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sejda.core.support.prefix.model.NameGenerationRequest.nameRequest;

import org.junit.jupiter.api.Test;

/**
 * Test unit for the {@link PrependPrefixProcessor}
 * 
 * @author Andrea Vacondio
 * 
 */
public class PrependPrefixProcessorTest {

    private PrependPrefixProcessor victim = new PrependPrefixProcessor();

    @Test
    public void positive() {
        assertEquals("prefix_name", victim.process("prefix_", nameRequest().originalName("name")));
    }

    @Test
    public void nullRequest() {
        assertEquals("prefix_", victim.process("prefix_", null));
    }

    @Test
    public void noName() {
        assertEquals("prefix_", victim.process("prefix_", nameRequest()));
    }
}
