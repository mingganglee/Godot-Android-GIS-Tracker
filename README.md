# Godot Android GIS Tracker

## Example

- [GISTrackerExample](https://github.com/lminggang/Godot-Android-GIS-Tracker/tree/main/example)

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
