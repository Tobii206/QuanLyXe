/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.XuatXu;
import java.util.List;
import uitl.XJdbc;
import uitl.XQuery;

/**
 *
 * @author Nga Cọt
 */
public class XuatXuDao {
    
    String getAll = "select * from XuatXu ";
    
    
    public List<XuatXu> getAll(){
     return XQuery.getBeanList(XuatXu.class, getAll);
    }
    
    String findByMaXX = "select * from XuatXu where MaXuatXu = ?";
    public XuatXu findByMaXX(String maXX){
     return XQuery.getSingleBean(XuatXu.class,findByMaXX,maXX);
    }
    
    String createSql = """
                       INSERT INTO [dbo].[XuatXu]
                                  ([MaXuatXu]
                                  ,[TenXuatXu])
                            VALUES
                                  (?,?)
                       """;
    public int create(XuatXu xuatXu){
     Object[] value={   
        xuatXu.getMaXuatSu(),
         xuatXu.getNoiNhap()
     };
     return XJdbc.executeUpdate(createSql, value);
    }
    String updateSql = """
                       UPDATE [dbo].[XuatXu]
                          SET 
                             [TenXuatXu] = ?
                        WHERE [MaXuatXu] =?
                       """;
    public int update(XuatXu xuatXu){
        Object[]value={
          xuatXu.getNoiNhap(),
          xuatXu.getMaXuatSu()
        };
        return XJdbc.executeUpdate(updateSql, value);
    }
    String deleteSql= """
                      DELETE FROM [dbo].[XuatXu]
                            WHERE [MaXuatXu] =?
                      """;
    public int delete(String ma){
     return XJdbc.executeUpdate(deleteSql,ma);
    }
    String findByTen ="""
                      select * from XuatXu where TenXuatXu like ?
                      """;
    public List<XuatXu> findByTen(String ten){
     return XQuery.getBeanList(XuatXu.class,findByTen,"%"+ten.toLowerCase()+"%");
    }
        String findXuatXuSql = "SELECT * FROM XuatXu WHERE MaXuatXu = ?";
    public XuatXu findxuatXu(String MaXuatSu){
    return XQuery.getSingleBean(XuatXu.class, findXuatXuSql, MaXuatSu);
    }
}
