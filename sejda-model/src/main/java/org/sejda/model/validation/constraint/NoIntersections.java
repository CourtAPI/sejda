/*
 * Created on 11/ago/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.model.validation.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import org.sejda.model.validation.validator.NoIntersectionsValidator;

/**
 * Constraint to validate that a page range selection doesn't have any intersection among the selected ranges.
 * 
 * @author Andrea Vacondio
 * 
 */
@Target({ FIELD, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { NoIntersectionsValidator.class })
@Documented
public @interface NoIntersections {

    String message() default "The selected page ranges contain intersections.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
