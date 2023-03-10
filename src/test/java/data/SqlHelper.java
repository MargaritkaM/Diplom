package data;


import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {

        private SqlHelper() {
        }

        private static QueryRunner queryRunner;
        private static String url = System.getProperty("db.url");
        private static String user = System.getProperty("db.user");
        private static String password = System.getProperty("db.password");
        private static Connection connection;

        @SneakyThrows
        public static void setup() {
            queryRunner = new QueryRunner();
            connection = DriverManager
                    .getConnection(url, user, password);

        }
    @SneakyThrows
    public static String getStatus() {
        setup();
        String code = "SELECT status FROM public.payment_entity;";
        return queryRunner.query(connection, code, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getStatusCredit() {
        setup();
        String code = "SELECT status FROM public.credit_request_entity;";
        return queryRunner.query(connection, code, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanAll() {
        setup();
        queryRunner.update(connection, "DELETE FROM credit_request_entity;");
        queryRunner.update(connection, "DELETE FROM order_entity;");
        queryRunner.update(connection, "DELETE FROM payment_entity;");
    }

//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//
//    public class PaymentModel {
//
//        String id;
//        String amount;
//        String created;
//        String status;
//        String transaction_id;
//

}