/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Nga Cọt
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class XuatXu {
    private String maXuatXu;
    private String tenXuatXu;
    
    // Tương thích với code cũ
    public String getMaXuatSu() { return maXuatXu; }
    public void setMaXuatSu(String maXuatSu) { this.maXuatXu = maXuatSu; }
    public String getNoiNhap() { return tenXuatXu; }
    public void setNoiNhap(String noiNhap) { this.tenXuatXu = noiNhap; }
    
    @Override
    public String toString(){
        return tenXuatXu;
    }
}
