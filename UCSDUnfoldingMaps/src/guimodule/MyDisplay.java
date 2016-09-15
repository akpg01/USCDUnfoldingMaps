package guimodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setup(){
		size(400,400);
		background(130,150,250);
	}
	
	public void draw(){
		fill(255,255,0);
		ellipse(200,200,390,390);
		fill(0,0,0);
		ellipse(130,130,50,70);
		ellipse(280,130,50,70);
		
		noFill();
		arc(200, 280, 150,95,0,PI);
	}

}
