package com.sam.webtasks.perceptualTask;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.types.DragBounds;
import com.ait.lienzo.client.core.types.LinearGradient;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.shared.core.types.DragConstraint;
import com.ait.lienzo.shared.core.types.TextAlign;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sam.webtasks.client.SequenceHandler;

public class ConfidenceRating {
	public static void Run() {
		PerceptDisplay.panel.remove(PerceptDisplay.responseLayer);
        PerceptDisplay.panel.remove(PerceptDisplay.fixLayer);
        PerceptDisplay.panel.remove(PerceptDisplay.grid2Layer);
        
        RootPanel.get().remove(PerceptDisplay.wrapper);
        
        final int sliderWidth = 400;
        final int sliderHeight = 20;
        final int width = sliderWidth + 2 * sliderHeight;
        final int height = 5 * sliderHeight;

        final int sliderRange = sliderWidth - (2 * sliderHeight);

        LinearGradient gradient = new LinearGradient(0, -50, 0, 50);
        gradient.addColorStop(0.5, "#4DA4F3");
        gradient.addColorStop(0.8, "#ADD9FF");
        gradient.addColorStop(1, "#9ED1FF");

        LienzoPanel lienzoPanel = new LienzoPanel(width, height);

        Rectangle slider = new Rectangle(sliderWidth, sliderHeight, sliderHeight / 2);

        slider.setX(sliderHeight).setY(2 * sliderHeight).setFillGradient(gradient).setStrokeColor(ColorName.GRAY.getValue()).setStrokeWidth(1);

        int Ystart = 2*sliderHeight;
        int Yend = Ystart - (sliderHeight/2);
        int X0 = sliderHeight;
        int X1 = (sliderWidth/5)+sliderHeight;
        int X2 = (2*(sliderWidth/5))+sliderHeight;
        int X3 = (3*(sliderWidth/5))+sliderHeight;
        int X4 = (4*(sliderWidth/5))+sliderHeight;
        int X5 = width-sliderHeight;
        
        Line tick0 = new Line (X0,Ystart,X0,Yend);
        Line tick1 = new Line(X1,Ystart,X1,Yend);
        Line tick2 = new Line(X2,Ystart,X2,Yend);
        Line tick3 = new Line(X3,Ystart,X3,Yend);
        Line tick4 = new Line(X4,Ystart,X4,Yend);
        Line tick5 = new Line(X5, Ystart, X5, Yend);
        
        Text label0 = new Text("50%", "Verdana, sans-serif", 10);
        label0.setX(X0);
        label0.setY(Yend - 10);
        label0.setTextAlign(TextAlign.CENTER);
        label0.setFillColor(ColorName.BLACK);
        
        Text label1 = new Text("60%", "Verdana, sans-serif", 10);
        label1.setX(X1);
        label1.setY(Yend - 10);
        label1.setTextAlign(TextAlign.CENTER);
        label1.setFillColor(ColorName.BLACK);
        
        Text label2 = new Text("70%", "Verdana, sans-serif", 10);
        label2.setX(X2);
        label2.setY(Yend - 10);
        label2.setTextAlign(TextAlign.CENTER);
        label2.setFillColor(ColorName.BLACK);
        
        Text label3 = new Text("80%", "Verdana, sans-serif", 10);
        label3.setX(X3);
        label3.setY(Yend - 10);
        label3.setTextAlign(TextAlign.CENTER);
        label3.setFillColor(ColorName.BLACK);
        
        Text label4 = new Text("90%", "Verdana, sans-serif", 10);
        label4.setX(X4);
        label4.setY(Yend - 10);
        label4.setTextAlign(TextAlign.CENTER);
        label4.setFillColor(ColorName.BLACK);
        
        Text label5 = new Text("100%", "Verdana, sans-serif", 10);
        label5.setX(X5);
        label5.setY(Yend - 10);
        label5.setTextAlign(TextAlign.CENTER);
        label5.setFillColor(ColorName.BLACK);
        
        final Circle thumbCircle = new Circle(sliderHeight);
        final Circle thumbOverlay = new Circle(sliderHeight);
        
        final Group thumb = new Group();
        
        thumbOverlay.setFillColor(ColorName.BLACK);
        thumbOverlay.setAlpha(0.000000001);

        thumb.add(thumbCircle);
        thumb.add(thumbOverlay);

        thumb.setX(slider.getX() + sliderHeight + (sliderRange / 2));
        thumb.setY(slider.getY() + sliderHeight / 2);
        thumbCircle.setStrokeColor(ColorName.GRAY.getValue());
        thumbCircle.setStrokeWidth(1);
        thumb.setDraggable(false);
        thumb.setDragConstraint(DragConstraint.HORIZONTAL);
        thumb.setDragBounds(new DragBounds().setX1(slider.getX() + sliderHeight).setX2(slider.getX() + sliderWidth - sliderHeight));
        thumbCircle.setFillColor(ColorName.LIGHTGRAY.getValue());

        thumbCircle.setVisible(false);

        final Layer sliderLayer = new Layer();
        final Layer thumbLayer = new Layer();
        
        sliderLayer.add(slider);
        sliderLayer.add(tick0);
        sliderLayer.add(tick1);
        sliderLayer.add(tick2);
        sliderLayer.add(tick3);
        sliderLayer.add(tick4);
        sliderLayer.add(tick5);
        sliderLayer.add(label0);
        sliderLayer.add(label1);
        sliderLayer.add(label2);
        sliderLayer.add(label3);
        sliderLayer.add(label4);
        sliderLayer.add(label5);
        
        thumbLayer.add(thumb);
        
        lienzoPanel.add(thumbLayer);
        lienzoPanel.add(sliderLayer);

        final HorizontalPanel sliderPanel = new HorizontalPanel();

        final Label leftValue = new Label("(complete guess)");
        final Label rightValue = new Label("(absolutely certain)");

        sliderPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        sliderPanel.add(leftValue);
        sliderPanel.add(lienzoPanel);
        sliderPanel.add(rightValue);

        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        final VerticalPanel verticalPanel = new VerticalPanel();

        //set up vertical panel
        verticalPanel.setWidth("75%");
        //verticalPanel.setHeight(Window.getClientHeight() + "px");
        verticalPanel.setHeight("25%");

        verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        //add elements to panel
        final HTML displayText = new HTML("How confident are you that you chose correctly?");
        displayText.setStyleName("instructionText");
        verticalPanel.add(displayText);
        verticalPanel.add(sliderPanel);

        //place vertical panel inside horizontal panel, so it can be centred
        horizontalPanel.setWidth(Window.getClientWidth() + "px");
        horizontalPanel.setHeight(Window.getClientHeight() + "px");

        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

        horizontalPanel.add(verticalPanel);
        
        //set up click input
        slider.addNodeMouseClickHandler(new NodeMouseClickHandler() {
        	public void onNodeMouseClick(NodeMouseClickEvent event) {
        		thumbLayer.moveToTop();
        		
        		final int x = event.getX() - (sliderWidth / 2) - sliderHeight;
        		
        		thumbCircle.setX(x);
        		
        		thumbCircle.setVisible(true);
        		thumbCircle.setFillColor(ColorName.LIGHTPINK.getValue());
        		thumb.getLayer().draw();

                new Timer() {
                    public void run() {
                    	RootPanel.get().remove(horizontalPanel);
                    	
                    	RootPanel.get().add(PerceptDisplay.wrapper);
                    	
                    	new Timer() {
                    		public void run() {
                    			//transform to the range 0 - 400:
                    			
                    			int c = x + (sliderWidth / 2);

                    			PerceptTrial.confidence = c;
                    			PerceptResponse.Run();
                    		}
                    	}.schedule(250); 
                    }
                }.schedule(250);
        	}
        });

        //add panel to root
        RootPanel.get().add(horizontalPanel);
    }
}
