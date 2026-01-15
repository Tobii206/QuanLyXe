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
public class KichThuoc {
    private String maKichThuoc;
    private String tenKichThuoc;
    
    public String getMaKichThuoc() { 
        return maKichThuoc; 
    }
    
    public void setMaKichThuoc(String maKichThuoc) { 
        this.maKichThuoc = maKichThuoc; 
    }
    
    public String getKichThuoc() { 
        return tenKichThuoc; 
    }
    
    public void setKichThuoc(String kichThuoc) { 
        this.tenKichThuoc = kichThuoc; 
    }
    
    public String getTenKichThuoc() { 
        return tenKichThuoc; 
    }
    
    public void setTenKichThuoc(String tenKichThuoc) { 
        this.tenKichThuoc = tenKichThuoc; 
    }
    
    @Override
    public String toString(){
        return tenKichThuoc != null ? tenKichThuoc : "";
    }
}
