/*
 * Copyright (C) 2013-2021 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
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
package com.b3dgs.lionengine.audio.sc68;

import com.b3dgs.lionengine.LionEngineException;
import com.b3dgs.lionengine.audio.Audio;

/**
 * Allows to play Sc68 musics (AtariST musics).
 */
public interface Sc68 extends Audio
{
    /**
     * Configure the audio output.
     * 
     * @param interpolation <code>true</code> to use interpolation, <code>false</code> else.
     * @param joinStereo <code>true</code> to join stereo, <code>false</code> else.
     */
    void setConfig(boolean interpolation, boolean joinStereo);

    /**
     * Set starting tick (starting audio position).
     * 
     * @param tick The starting tick <code>[0 - {@link #getTicks()}]</code>.
     * @throws LionEngineException If argument is invalid.
     */
    void setStart(long tick);

    /**
     * Set loop area in tick.
     * 
     * @param first The first tick <code>[0 - last}]</code>.
     * @param last The last tick <code>[first - {@link #getTicks()}}]</code>.
     * @throws LionEngineException If arguments are invalid.
     */
    void setLoop(long first, long last);

    /**
     * Pause the audio (can be resumed).
     */
    void pause();

    /**
     * Resume the audio (if paused).
     */
    void resume();
}
