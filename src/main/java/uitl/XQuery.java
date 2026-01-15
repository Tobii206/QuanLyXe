package uitl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import poly.cafe.entity.User;

/**
 * Lớp tiện ích hỗ trợ truy vấn và chuyển đổi sang đối tượng
 *
 * @author NghiemN
 * @version 1.0
 */
public class XQuery {

    /**
     * Truy vấn 1 đối tượng
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param beanClass lớp của đối tượng kết quả
     * @param sql câu lệnh truy vấn
     * @param values các giá trị cung cấp cho các tham số của SQL
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    public static <B> B getSingleBean(Class<B> beanClass, String sql, Object... values) {
        List<B> list = XQuery.getBeanList(beanClass, sql, values);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * Truy vấn nhiều đối tượng
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param beanClass lớp của đối tượng kết quả
     * @param sql câu lệnh truy vấn
     * @param values các giá trị cung cấp cho các tham số của SQL
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    public static <B> List<B> getBeanList(Class<B> beanClass, String sql, Object... values) {
        List<B> list = new ArrayList<>();
        try {
            ResultSet resultSet = XJdbc.executeQuery(sql, values);
            while (resultSet.next()) {
                list.add(XQuery.readBean(resultSet, beanClass));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    /**
     * Tạo bean với dữ liệu đọc từ bản ghi hiện tại
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param resultSet tập bản ghi cung cấp dữ liệu
     * @param beanClass lớp của đối tượng kết quả
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    private static <B> B readBean(ResultSet resultSet, Class<B> beanClass) throws Exception {
    B bean = beanClass.getDeclaredConstructor().newInstance();
    Method[] methods = beanClass.getDeclaredMethods();

    // Build a map of normalized column labels -> actual column label from ResultSetMetaData
    java.util.Map<String, String> colMap = new java.util.HashMap<>();
    java.sql.ResultSetMetaData md = resultSet.getMetaData();
    int colCount = md.getColumnCount();
    for (int i = 1; i <= colCount; i++) {
        String label = md.getColumnLabel(i);
        if (label == null || label.isEmpty()) label = md.getColumnName(i);
        String key = normalize(label);
        colMap.putIfAbsent(key, label);
    }

    for (Method method : methods) {
        String name = method.getName();
        if (name.startsWith("set") && method.getParameterCount() == 1) {
            try {
                String fieldName = name.substring(3); // e.g. GiaTri
                String[] keysToTry = new String[] {
                    normalize(fieldName),
                    normalize(decapitalize(fieldName)),
                    normalize(camelToSnake(fieldName)),
                    normalize(fieldName.toLowerCase())
                };

                String matchedLabel = null;
                for (String k : keysToTry) {
                    if (k == null) continue;
                    if (colMap.containsKey(k)) {
                        matchedLabel = colMap.get(k);
                        break;
                    }
                }

                Object value = null;
                Class<?> paramType = method.getParameterTypes()[0];
                if (matchedLabel != null) {
                    // Read value using the matched label
                    if (paramType == java.math.BigDecimal.class) {
                        value = resultSet.getBigDecimal(matchedLabel);
                    } else {
                        value = resultSet.getObject(matchedLabel);
                    }
                }

                // Convert BigDecimal to numeric primitives if needed
                if (value instanceof BigDecimal) {
                    if (paramType == Double.class || paramType == double.class) {
                        value = ((BigDecimal) value).doubleValue();
                    } else if (paramType == Float.class || paramType == float.class) {
                        value = ((BigDecimal) value).floatValue();
                    } else if (paramType == Integer.class || paramType == int.class) {
                        value = ((BigDecimal) value).intValue();
                    } else if (paramType == Long.class || paramType == long.class) {
                        value = ((BigDecimal) value).longValue();
                    }
                }

                // Convert numeric types to boolean when setter expects boolean
                if (value instanceof Number && (paramType == Boolean.class || paramType == boolean.class)) {
                    value = ((Number) value).intValue() != 0;
                }

                // For primitives, ensure non-null default; for BigDecimal keep zero default to avoid nulls
                if (value == null) {
                    if (paramType == int.class) value = 0;
                    else if (paramType == long.class) value = 0L;
                    else if (paramType == double.class) value = 0.0d;
                    else if (paramType == float.class) value = 0.0f;
                    else if (paramType == boolean.class) value = false;
                    else if (paramType == java.math.BigDecimal.class) value = java.math.BigDecimal.ZERO;
                }

                method.invoke(bean, value);
            } catch (Exception e) {
                // Swallow per-field errors to avoid breaking UI; print minimal debug
                System.out.printf("Error setting field '%s': %s\n", name.substring(3), e.getMessage());
            }
        }
    }
    return bean;
}

    private static String normalize(String s) {
        if (s == null) return null;
        return s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    }

    private static String camelToSnake(String s) {
        if (s == null) return null;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c) && sb.length() > 0) sb.append('_');
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    private static String decapitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    public static void main(String[] args) {
//        demo1();
//        demo2();
    }

//    private static void demo1() {
//        String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";
//        User user = XQuery.getSingleBean(User.class, sql, "NghiemN", "123456");
//    }
//
//    private static void demo2() {
//        String sql = "SELECT * FROM Users WHERE Fullname LIKE ?";
//        List<User> list = XQuery.getBeanList(User.class, sql, "%Nguyễn %");
//    }
}