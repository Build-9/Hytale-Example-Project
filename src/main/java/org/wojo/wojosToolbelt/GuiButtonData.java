package org.wojo.wojosToolbelt.ui;

import com.hypixel.hytale.server.core.Message;

import javax.annotation.Nonnull;
import java.util.*;

public static class GuiButtonData {
        public Message buttonMsg = null;
        public String buttonText = "";
        public String tooltipText = "";

        public String buttonIcon = "";
        public String isButtonDisabled = "false";
        public String buttonStyle = "";
        public String buttonHtmlId = "";
        public String iconHtmlId = "";

        public ButtonData (Message msg, String text, String tooltip, String icon, String is_disabled, String style, String btn_html_id, String icn_html_id){
            this.buttonMsg = msg;
            this.buttonText = "";
            this.tooltipText = "";

            this.buttonIcon = icon;
            this.isButtonDisabled = is_disabled;
            this.buttonStyle = style;
            this.buttonHtmlId = btn_html_id;
            this.iconHtmlId = icn_html_id;
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
            return output;
        }
    }
