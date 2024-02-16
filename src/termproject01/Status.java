package termproject01;

import java.io.Serializable;

public enum Status implements Serializable{
	
	NULL, PEN, STRAIGHT_LINE, CURVE, SQUARE, CIRCLE, OVAL, TRIANGLE, NARROW, ERASER, FONT_SIZE, WORD, REDO, UNDO
	,creatingOBJ,ready2createOBJ, drawing, ready2draw, ready2Move, moving, targetSelected, active, inselected, selected, Resizing, ready2Resize, ready2Rotate, Rotating;
}
