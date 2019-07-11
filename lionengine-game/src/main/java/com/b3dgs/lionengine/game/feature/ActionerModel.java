/*
 * Copyright (C) 2013-2019 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.b3dgs.lionengine.game.feature;

import java.util.List;

/**
 * Actioner model implementation.
 */
@FeatureInterface
public class ActionerModel extends FeatureModel implements Actioner
{
    /** Allowed actions name. */
    private final List<ActionRef> actions;

    /**
     * Create feature.
     * 
     * @param setup The setup reference.
     */
    public ActionerModel(Setup setup)
    {
        super();

        actions = ActionsConfig.imports(setup, this::getFeature);
    }

    /*
     * Actioner
     */

    @Override
    public List<ActionRef> getActions()
    {
        return actions;
    }
}