import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

import myMath.Polynom;
import myMath.StdDraw;


public class M {


	public static void main(String[] args) {

		Polynom p = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		p.draw(-2, 6);
	}

	




	

}
