<table>
    <tr>
        <td>
            <a href="http://www.b3dgs.com/v6/page.php?lang=en&section=lionengine"><img src="http://www.b3dgs.com/v6/projects/lionengine/lionengine.jpg"/></a>
        </td>
        <td>
            <h4 align="center">Summary</h4>
            <div align="left">
                <ul>
                    <li><a href="#presentation">Presentation</a></li>
                    <li><a href="#general-features">General features</a></li>
                    <li><a href="#download">Download</a></li>
                    <li><a href="#installation">Installation</a></li>
                    <li><a href="#getting-started">Getting Started</a></li>
                    <li><a href="#tutorials">Tutorials</a></li>
                </ul>
            </div>
        </td>
    </tr>
</table>

## Presentation

The __LionEngine__ is a game engine especially developed during the project [Lionheart Remake] (http://www.b3dgs.com/v6/page.php?lang=en&section=lionheart_remake) for an easy Java use.
The engine is as a library, in Jar format (_including its javadoc_), which can be included in any project;
for utility class uses, or to directly implement and inherit a game skeleton (_including management of frame rate, extrapolation, input output..._).

Using Java 7 internal libraries, it is specifically designed for 2D games (no support for 3D at the moment), and proposes a set of functions for 2D resources management (_images_, _sprites_, _animations_, _tiles_...).
Inputs and outputs are also available, with an easy keys retrieval, mouse movement... Management of music file are also available (_Wav_, _Midi_, and more using plug-ins, such as _Sc68_).
Windowed, full-screen and applet formats are fully supported, with a complete frame rate control.

In its current version, the engine greatly simplifies the development of __Platform__, __Strategy__ and __Shoot'em Up__ games, and also __Network__ layer.

Since the version __6__, it supports __Android 1.5__ *(API 3)*.
The only change to perform is the gameplay part, as the '__mouse__' and '__keyboard__' concepts are different on Android.
Everything else is fully compatible and does not require any changes.

## General Features

* #### __lionengine-core__
>  * Simple initialization, with version control, screen configuration and resource directory
>  * Extrapolation control (_machine speed independent_)
>  * Advanced filtering capability (_Bilinear, HQ2X, HQ3X_)
>  * Sequence control (_intro, menu, game part, credits..._)
>  * Easy resource management (_relative to resource directory_)
>  * Advanced image usage (_sprite, animation, tile, font, parallax_)
>  * File I/O (_binary & XML reader & writer_)
>  * Utility classes (_Random, Conversions, Maths, File..._)
>  * Verbosity control


* #### __lionengine-core-awt__
>  * Engine implementation using __AWT__ from _JDK 7_


* #### __lionengine-core-swt__
>  * Engine implementation by using __SWT 3.5.1__


* #### __lionengine-core-android__
>  * Engine implementation using __Android 1.5 (API 3)__


* #### __lionengine-game__
>  * Tile based map package (_with minimap support, native save & load function_)
>    * Ray cast collision system
>    * Compatibility with raster bar effect
>  * Projectile system (_with a duo: launcher / projectile_)
>  * Effect system (_and its handler_)
>  * Entity base (_support external XML configuration, gravity and collision_)
>  * Camera management (_view and movement_)
>  * Cursor (_synced or not to system pointer_)
>  * General object factory system (_create instance of entity / effect / projectile_)
>  * Tile extractor (_generate tilesheet from a level rip image_)
>  * Level rip converter (_generate a level data file from a tile sheet and a level rip image_)
  

* #### __lionengine-game-platform__
>  * Background package (_for an easy background composition_)
>  * Extended entity & map package (_including compatibility with raster bar effect_)
>  * Extended camera system
  

* #### __lionengine-game-pathfinding__
>  * __A Star (A star)__ pathfinding implementation
>  * Compatible with game map system


* #### __lionengine-game-strategy__
>  * Extended map package (_adding support to pathfinding and fog of war_)
>  * Extended entity package (_attacker_, _extractor_, _mover_, _producer_ _..._)
>  * Entity skill system (_representing its actions, accessible from icons_)
>  * Control panel system (_map area for game action, HUD area for icons and more_)
>  * Extended cursor (_can interact with entities on map and control panel_)


* #### __lionengine-network__
>  * Server & Client system
>  * Customizable network message
>  * Integrated chat system


* #### __lionengine-audio-wav__
>  * Support for Wav sound


* #### __lionengine-audio-midi__
>  * Support for Midi music


* #### __lionengine-audio-sc68__
>  * Support for Sc68 Atari music

## Download

* [Go to website](http://www.b3dgs.com/v6/page.php?lang=en&section=lionengine)
* [Last version](http://www.b3dgs.com/v6/projects/lionengine/files/LionEngine_6.2.0_lib.zip)

## Installation

Steps to include the __LionEngine__ in your project:

1. Install at least the [Java JDK 7] (http://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. Install the [Android SDK 1.5] (http://developer.android.com/sdk/index.html) (only if you use __lionengine-android__)
3. Choose your favourite IDE ([Eclipse] (http://www.eclipse.org/downloads/), [Netbeans] (https://netbeans.org/downloads/)...)
4. Download the latest [LionEngine] (http://www.b3dgs.com/v6/page.php?lang=en&section=lionengine)
5. Include all __LionEngine__ libraries you need for your project, following the tree dependency:
  * __lionengine-core__ _(minimum requirement)_
    * __lionengine-core-awt__ _(required if you use_ __AWT__ _as graphic renderer, target for computer)_
	* __lionengine-core-swt__ _(required if you use_ __SWT__ _as graphic renderer, target for computer)_
    * __lionengine-core-android__ _(required if you use_ __Android 1.5__, _target for phones)_
    * __lionengine-game__ _(base for game development)_
    * __lionengine-game-platform__ _(specialized for platform games)_
      * __lionengine-game-pathfinding__ _(support for pathfinding)_
        * __lionengine-game-rts__ _(specialized for strategy games)_
    * __lionengine-network__ _(support for network)_
	* __lionengine-audio-wav__ _(support for Wav sound)_
	* __lionengine-audio-midi__ _(support for Midi music)_
    * __lionengine-audio-sc68__ _(support for Sc68 Atari music)_
6. Join (if you want) the javadoc for each library
7. You are now ready to use the __LionEngine__ in your project

## Getting Started

Once you installed the __LionEngine__ in your project, you may would like to know how to prepare a quick sample as a first try.

#### Main class

* Using __lionengine-core-awt__ or __lionengine-core-swt__
```java
public final class AppJava
{
    public static void main(String[] args)
    {
        Engine.start("AppJava", Version.create(1, 0, 0), Verbose.CRITICAL, "resources");
        final Resolution output = new Resolution(640, 480, 60);
        final Config config = new Config(output, 16, true);
        final Loader loader = new Loader(config);
        loader.start(Scene.class);
    }
}
```

* Using __lionengine-core-android__
```java
public final class AppAndroid
        extends Activity
{
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
 
        Engine.start("AppAndroid", Version.create(1, 0, 0), Verbose.CRITICAL, this);
        final Resolution output = new Resolution(800, 480, 60);
        final Config config = new Config(output, 32, false);
        final Loader loader = new Loader(config);
        loader.start(Menu.class);
    }
 
    @Override
    public void finish()
    {
        super.finish();
        Engine.terminate();
    }
}
```

#### Minimal sequence
```java
final class Scene
        extends Sequence
{
    private static final Resolution NATIVE = new Resolution(320, 240, 60);

    private final Keyboard keyboard;
 
    Scene(Loader loader)
    {
        super(loader, Scene.NATIVE);
        keyboard = getInputDevice(Keyboard.class);
    }
 
    @Override
    protected void load()
    {
        // Load
    }
 
    @Override
    protected void update(double extrp)
    {
        if (keyboard.isPressed(Keyboard.ESCAPE))
        {
            end();
        }
        // Update
    }
 
    @Override
    protected void render(Graphic g)
    {
        // Render
    }
}
```

## Tutorials

* [LionEngine WebSite](http://lionengine.b3dgs.com)
