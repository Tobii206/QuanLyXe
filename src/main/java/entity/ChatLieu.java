/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 *
 * @author Nga Cọt
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ChatLieu {
    private String maChatLieu; 
    private String tenChatLieu;
    
    public String getMaChatLieu() { 
        return maChatLieu; 
    }
    
    public void setMaChatLieu(String maChatLieu) { 
        this.maChatLieu = maChatLieu; 
    }
    
    public String getChatLieu() { 
        return tenChatLieu; 
    }
    
    public void setChatLieu(String chatLieu) { 
        this.tenChatLieu = chatLieu; 
    }
    
    public String getTenChatLieu() { 
        return tenChatLieu; 
    }
    
    public void setTenChatLieu(String tenChatLieu) { 
        this.tenChatLieu = tenChatLieu; 
    }
    
    @Override
    public String toString(){
        return tenChatLieu != null ? tenChatLieu : "";
    }
}
