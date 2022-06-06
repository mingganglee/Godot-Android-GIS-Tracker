extends Control

var gis_tracker = null
func _ready():
	init_gis()

func init_gis():
	if Engine.has_singleton("GISTracker"):
		self.gis_tracker = Engine.get_singleton("GISTracker")
		self.gis_tracker.init()
		refresh_gis()

func refresh_gis():
	var lat = self.gis_tracker.getStringLatitude()
	var lon = self.gis_tracker.getStringLongitude()
	var accuracy = self.gis_tracker.getStringGPSAccuracy()
	var bearing = self.gis_tracker.getStringGPSBearing()
	var speed = self.gis_tracker.getStringGPSSpeed()
	var gps_time = self.gis_tracker.getStringGPSTime()
	var altitude = self.gis_tracker.getStringAltitude()
	var network_state = self.gis_tracker.getNetworkState()
	var gps_state = self.gis_tracker.getGPSState()
	
	$Layout/Lat.set_text("lat: %s" % lat)
	$Layout/Lon.set_text("lon: %s" % lon)
	$Layout/Accuracy.set_text("accuracy: %s" % accuracy)
	$Layout/Bearing.set_text("bearing: %s" % bearing)
	$Layout/Speed.set_text("speed: %s" % speed)
	$Layout/GpsTime.set_text("gps_time: %s" % gps_time)
	$Layout/Altitude.set_text("altitude: %s" % altitude)
	$Layout/NetworkState.set_text("network_state: %s" % network_state)
	$Layout/GpsState.set_text("gps_state: %s" % gps_state)

func _on_DisplayGISInfo_pressed():
	if self.gis_tracker:
		refresh_gis()
