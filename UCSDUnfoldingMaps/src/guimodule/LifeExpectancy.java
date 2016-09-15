package guimodule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

/*
 * Displays a map that represents countries and average life expectancies at birth
 * of its citizens the dark blue color indicates high levels of life expectancies
 * and red colors implies lower average life expectancies. */

public class LifeExpectancy extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	Map<String, Float> lifeExpMap;
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup(){
		// set up size of canvas
		size(800,600,OPENGL);
		
		// create new map object
		map = new UnfoldingMap(this, 50,50,700,500, new Google.GoogleMapProvider());
		
		// allows user to interact with map
		MapUtils.createDefaultEventDispatcher(this, map);
				
		// read in life expectancy file
		lifeExpMap = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule3.csv");
				
		// features and markers
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		
		// country markers are shaded according to life expectancy
		shadeCountries();
		
	}
	public void draw(){
		map.draw();
	}

	/*
	 * Input: csv file Reads file from csv and adds the key and corresponding
	 * value into a hashmap. returns a hashmap
	 */
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName) {
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		// read rows in csv file
		String[] rows = loadStrings(fileName);
		for (String row : rows) {
			String[] columns = row.split(",");
			// ignore any data that does not contain 6 columns and countries with 
			// no life expectancy data.
			if(columns.length == 6 && !columns[5].equals("..")){
				lifeExpMap.put(columns[4], Float.parseFloat(columns[5]));
			}
		}
		return lifeExpMap;
	}
	
	/*shades the country according to the */
	private void shadeCountries(){
		for(Marker marker: countryMarkers){
			String countryId = marker.getId();
			if(lifeExpMap.containsKey(countryId)){
				float lifeExp = lifeExpMap.get(countryId);
				
				/* life expectancy, range (40-90) and color intensity range (10-255) 
				 * low life expectancy color is red and blue for high life expectancy*/
				int colorLevel = (int) map(lifeExp, 40,90,10,255);
				marker.setColor(color(255-colorLevel,100,colorLevel));
				
			}else{
				/*use default color; shade of gray, if the life expectancy is 
				 * unknown for a particular country*/
				marker.setColor(color(150,150,150));
			}
		}
	}
}
