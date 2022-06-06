# Godot Android GIS Tracker

## Refre

- [godot_gps](https://github.com/seagsm/godot_gps)
- [Advanced Godot | Creating Custom Android Modules](https://www.youtube.com/watch?v=a6MDzdqC5a0)

## Example

- [GISTrackerExample](https://github.com/lminggang/Godot-Android-GIS-Tracker/tree/main/example)

## Notice

Android device location permission enabled

## How to use

### Download arr

Download [`gis_tracker.aar`](https://github.com/lminggang/Godot-Android-GIS-Tracker/releases/download/v0.1/gis_tracker.aar)

### Download Android Template

Project -> Install Android Build Template...

### Settings project

1. `gis_tracker.aar` file move to `android/plugins/`
2. create `gis_tracker.gdap` file

```text
[config]

name="GISTracker"
binary_type="local"
binary="gis_tracker.aar"
```

3. Export apk

```text
# Export for android -> Option
Use Custom Buildd -> On
Gis Tracker -> On
```
