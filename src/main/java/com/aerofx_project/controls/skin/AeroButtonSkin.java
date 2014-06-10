/*
 * Copyright (c) 2014, Matthias Meidinger
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package com.aerofx_project.controls.skin;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import com.aerofx_project.util.BindableTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Matthias on 10.06.2014.
 */
public class AeroButtonSkin extends ButtonSkin implements AeroSkin {

    private Rectangle focusBorderRect;

    private InvalidationListener focusBorderListener;

    private InvalidationListener focusAnimationListener;
    private BindableTransition focusedButtonTransition;

    public AeroButtonSkin(Button button) {
        super(button);
        focusBorderRect = new Rectangle(0,0, Color.TRANSPARENT);
        getChildren().add(focusBorderRect);
        focusBorderRect.setVisible(false);
        focusBorderRect.getStyleClass().add("button-focus-border");
        focusBorderListener = (e) -> focusBorderRect.setVisible(getSkinnable().isFocused());
//        setFocusedButtonAnimation();
        getSkinnable().focusedProperty().addListener(focusBorderListener);
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        focusBorderRect.setX(x+2);
        focusBorderRect.setY(y+2);
        focusBorderRect.setWidth(w-4);
        focusBorderRect.setHeight(h-4);
    }

    private void setFocusedButtonAnimation(){
        if(!getSkinnable().isDisabled()){
            if(focusedButtonTransition != null && focusedButtonTransition.getStatus() == Animation.Status.RUNNING)
                focusedButtonTransition.stop();
            else{
                final Duration duration = Duration.millis(1000);
                focusedButtonTransition = new BindableTransition(duration);
                focusedButtonTransition.setCycleCount(Timeline.INDEFINITE);
                focusedButtonTransition.setAutoReverse(true);

                //starting gradient
                final Color startColor1 = Color.rgb(242,242,242);
                final Color startColor2 = Color.rgb(235,235,235);
                final Color startColor3 = Color.rgb(221,221,221);
                final Color startColor4 = Color.rgb(207,207,207);

                //ending gradient
                final Color endColor1 = Color.rgb(235,246,252);
                final Color endColor2 = Color.rgb(229,243,251);
                final Color endColor3 = Color.rgb(203,232,248);
                final Color endColor4 = Color.rgb(184,221,242);

                focusedButtonTransition.fractionProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        List<BackgroundFill> list = new ArrayList<>();

                        Stop[] stops = new Stop[] { new Stop(0f, Color.color(
                                (endColor1.getRed() - startColor1.getRed()) * newValue.doubleValue() + startColor1.getRed(),
                                (endColor1.getGreen() - startColor1.getGreen()) * newValue.doubleValue() + startColor1.getGreen(),
                                (endColor1.getBlue() - startColor1.getBlue()) * newValue.doubleValue() + startColor1.getBlue())),
                        new Stop(0.49f, Color.color(
                                (endColor2.getRed() - startColor2.getRed()) * newValue.doubleValue() + startColor2.getRed(),
                                (endColor2.getGreen() - startColor2.getGreen()) * newValue.doubleValue() + startColor2.getGreen(),
                                (endColor2.getBlue() - startColor2.getBlue()) * newValue.doubleValue() + startColor2.getBlue())),
                        new Stop(0.5f, Color.color(
                                (endColor3.getRed() - startColor3.getRed()) * newValue.doubleValue() + startColor3.getRed(),
                                (endColor3.getGreen() - startColor3.getGreen()) * newValue.doubleValue() + startColor3.getGreen(),
                                (endColor3.getBlue() - startColor3.getBlue()) * newValue.doubleValue() + startColor3.getBlue())),
                        new Stop(1f, Color.color(
                                (endColor4.getRed() - startColor4.getRed()) * newValue.doubleValue() + startColor4.getRed(),
                                (endColor4.getGreen() - startColor4.getGreen()) * newValue.doubleValue() + startColor4.getGreen(),
                                (endColor4.getBlue() - startColor4.getBlue()) * newValue.doubleValue() + startColor4.getBlue())) };

                        LinearGradient gradient = new LinearGradient(0.0,0.0,0.0,1.0,true, CycleMethod.NO_CYCLE,stops);
                        BackgroundFill bgFill = new BackgroundFill(gradient, new CornerRadii(1.0), new Insets(2.0));
                        list.add(bgFill);

                        getSkinnable().setBackground(new Background(list.get(0)));
                    }
                });

                focusedButtonTransition.play();
            }
        }
    }

    public void dispose() {
        super.dispose();
        getSkinnable().focusedProperty().removeListener(focusBorderListener);
    }


}