# Evernight

This is a rather small mod that makes the night last forever without stopping time.  It does this by modifying just two classes in Minecraft: `Level` and `ClientLevel`.

In `Level`, it makes `getSunAngle()` return the same value every time and sets the sky brightness according to the phase of the moon.  The methods for determining time of day are not touched, allowing things like villager schedules to proceed as normal.

In ClientLevel, it makes `getTimeOfDay()` and `getDayTime()` return whatever value you have configured.

Configuration is fairly straightforward.  By default, time will appear to be frozen at 14000t.  You can set the time and the dimensions where it should apply in `evernight-common.toml`.

Because of the way it works, this mod should be compatible with any mod that changes how the sky is rendered, including shaders installed via Oculus or Iris.  I have no idea if it will work with OptiFine.

Thanks to Architectury, this mod is available for both Forge and Fabric.
