/*
 * Created on Sep 30, 2011
 * Copyright 2010 by Eduard Weissmann (edi.weissmann@gmail.com).
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
package org.sejda.cli.transformer;

import org.sejda.cli.model.CropTaskCliArguments;
import org.sejda.conversion.RectangularBoxAdapter;
import org.sejda.model.parameter.CropParameters;

/**
 * {@link CommandCliArgumentsTransformer} for the Crop task command line interface
 * 
 * @author Eduard Weissmann
 * 
 */
public class CropCliArgumentsTransformer extends BaseCliArgumentsTransformer implements
        CommandCliArgumentsTransformer<CropTaskCliArguments, CropParameters> {

    /**
     * Transforms {@link CropTaskCliArguments} to {@link CropParameters}
     * 
     * @param taskCliArguments
     * @return populated task parameters
     */
    @Override
    public CropParameters toTaskParameters(CropTaskCliArguments taskCliArguments) {
        CropParameters parameters = new CropParameters();

        for (RectangularBoxAdapter cropArea : taskCliArguments.getCropAreas()) {
            parameters.addCropArea(cropArea.getRectangularBox());
        }

        populateSourceParameters(parameters, taskCliArguments);
        populateOutputTaskParameters(parameters, taskCliArguments);
        populateAbstractParameters(parameters, taskCliArguments);

        return parameters;
    }
}
