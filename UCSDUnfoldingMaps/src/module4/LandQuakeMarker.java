package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Grace Akpan
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	float radius;
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		
		radius = (Float) quake.getProperty("radius");
		
		// setting field in earthquake marker
		isOnLand = true;
	}


	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Draw a centered circle for land quakes
		// DO NOT set the fill color here.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered circle.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method

		pg.beginDraw();
		
		// place an x on earthquake markers that occurred during the past day
		String age = getLastOccurrence();
		int height = 10;
		int width = 10;
		pg.ellipse(x, y, radius, radius);
		if(age.equals("Past Day")){
			pg.strokeWeight(2);
			pg.line(x, y, x+width, y+height);
			pg.line(x+width, y, x, y+height);
		}
		
		pg.endDraw();
		
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}
	
	/*
	 * returns the last occurrence of an earthquake. The age property
	 * is categorized as either: "Past Day", "Past Week" or "Past Month"
	 * */
	public String getLastOccurrence(){
		return (String)getProperty("age");
	}
	


		
}