package org.wojo.wojosToolbelt.ui;

import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.*;

public class GuiButtonData {
        public Message buttonMsg = null;
        public String buttonText = "";
        public String tooltipText = "";

        public String buttonIcon = "";
        public String isButtonDisabled = "false";
        public String buttonStyle = "";
        public String buttonHtmlId = "";
        public String iconHtmlId = "";
        public String buttonHighlightImage = "";// Background image to show when hovering over Icon
        public String buttonPressImage = "";    // Background image to show when pressing the button

        public boolean isSubButton = false;     // Button doesn't have Icon and is used to link an area of display to a different Icon Button;
        public String associatedSlectionButtonHtmlId = "0";

        public GuiButtonData (Message msg, String text, String tooltip, String icon, String is_disabled, String style, String btn_html_id, String icn_html_id, String btn_highlight_img, String btn_press_img){
            this.buttonMsg = msg;
            this.buttonText = "";
            this.tooltipText = "";

            this.buttonIcon = icon;
            this.isButtonDisabled = is_disabled;
            this.buttonStyle = style;
            this.buttonHtmlId = btn_html_id;
            this.iconHtmlId = icn_html_id;
            // Used for radials as they were just different static images 
            // (Can't make custom button shapes or handle mouse position to use code for UI so use buttons over a BG img)
            this.buttonHighlightImage = btn_highlight_img;
            this.buttonPressImage = btn_press_img;
        }

        public String getDebugString() {
            String output = "-----------\n[DEBUG] ButtonData: \n";
            if (buttonMsg != null){
                output += (" - msg: "+buttonMsg.getRawText())+"\n";
            }else{
                output += " - msg: null\n";
            }

            output += " - text: "+buttonText+"\n";
            output += " - tooltip: "+tooltipText+"\n";
            output += " - icon: "+buttonIcon+"\n";
            output += " - isDis: "+isButtonDisabled+"\n";
            output += " - style: "+buttonStyle+"\n";
            output += " - btnHtmlId: "+buttonHtmlId+"\n";
            output += " - iconHtmlId: "+iconHtmlId+"\n";
            output += " - highlightImg: "+buttonHighlightImage+"\n";
            output += " - pressImg: "+buttonPressImage+"\n";
            return output;
        }
    }
