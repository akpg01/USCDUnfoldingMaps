package module4;


import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Grace Akpan
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	float radius;
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		radius = (Float) quake.getProperty("radius");
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		pg.beginDraw();
		// place an x on earthquake markers that occurred during the past day
		String age = getLastOccurrence();
		int height = 10;
		int width = 10;
		
		pg.rect(x, y, radius, radius);
		if(age.equals("Past Day")){
			pg.strokeWeight(2);
			pg.line(x, y, x+width, y+height);
			pg.line(x+width, y, x, y+height);
		}
		
		pg.endDraw();
		
	}
	
	/*
	 * returns the last occurrence of an earthquake. The age property
	 * is categorized as either: "Past Day", "Past Week" or "Past Month"
	 * */
	public String getLastOccurrence(){
		return (String)getProperty("age");
	}
	

	

}
