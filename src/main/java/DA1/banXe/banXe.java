/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package DA1.banXe;

import Login.DangNhapJF;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lenovo
 */
public class banXe {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DangNhapJF loginForm = new DangNhapJF();
            loginForm.setVisible(true);
        });
    }
    
}
