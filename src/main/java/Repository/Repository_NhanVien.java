package Repository;
import dao.NhanVienDao;
import java.util.ArrayList;
import entity.NhanVien;

public class Repository_NhanVien {



    private dao.NhanVienDao dao = new dao.NhanVienDao();
    
    public ArrayList<NhanVien> getAll() {
        return new ArrayList<>(dao.getAll());
    }

    public int add(NhanVien nv) {
        dao.insert(nv);
        return 1;
    }

    public int update(NhanVien nv) {
        dao.update(nv);
        return 1;
    }
    
    public boolean delete(String maNV) {
        dao.delete(maNV);
        return true;
    }
    
    public ArrayList<NhanVien> getByStatus(int status) {
        return new ArrayList<>(dao.getAll());
    }
    
    public ArrayList<NhanVien> searchByPhone(String keyword) {
        return new ArrayList<>(); // Simplified
    }
    public NhanVien login(String email, String matKhau) {
        return dao.findById(email);
    }
    
    public String getNextMaNhanVien() {
    return dao.getNextMaNhanVien();
}

}
