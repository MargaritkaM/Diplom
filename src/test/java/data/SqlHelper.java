package data;


import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    private SqlHelper() {
    }

    private static QueryRunner queryRunner;
    private static Connection connection;

    @SneakyThrows
    public static void setup() {
        queryRunner = new QueryRunner();
        connection = DriverManager
                .getConnection("jdbc:postgresql://89.223.70.43:5432/app", "margo", "queenMargo");

    }
    @SneakyThrows
    public static String getStatus() {
        setup();
        String code = "SELECT status FROM public.payment_entity;";
        return queryRunner.query(connection, code, new ScalarHandler<>());
    }

    @SneakyThrows
   public static void cleanAll(){
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
//    }
//
//    @SneakyThrows
//    public static String getTransactionCardStatusByDebitCard() {
//        var runner = new QueryRunner();
//        var codeSQL = "SELECT status from public.payment_entity order by created DESC LIMIT 1;";
//        try (
//                var conn = DriverManager.getConnection("jdbc:postgresql://89.223.70.43:5432/app", "margo", "queenMargo")
//        ) {
//            return runner.query(conn, codeSQL, new ScalarHandler<>());
//        }
//
//    }
//
//    @SneakyThrows
//    public static String getTransactionCardStatusByCreditCard() {
//        var runner = new QueryRunner();
//
//        var codeSQL = "SELECT status from public.credit_request_entity order by created DESC LIMIT 1;";
//        try (
//                var conn = DriverManager.getConnection("jdbc:postgresql://89.223.70.43:5432/app", "margo", "queenMargo")
//        ) {
//            return runner.query(conn, codeSQL, new ScalarHandler<>());
//        }
//
//    }
//
//    @SneakyThrows
//    public static void clearSUT() {
//        var runner = new QueryRunner();
//        var deleteCreditPaymentInfoTableSQL = "DELETE FROM public.credit_request_entity ;";
//        var deleteOrderInfoTableSQL = "DELETE FROM public.order_entity ;";
//        var deleteDebitPaymentInfoTableSQL1 = "DELETE FROM public.payment_entity ;";
//
//        try (
//                var conn = DriverManager.getConnection("jdbc:postgresql://89.223.70.43:5432/app", "margo", "queenMargo");
//        ) {
//            var deleteCodes = runner.update(conn, deleteCreditPaymentInfoTableSQL);
//            var deleteCardsInfo = runner.update(conn, deleteOrderInfoTableSQL);
//            var deleteUsers1 = runner.update(conn, deleteDebitPaymentInfoTableSQL1);
//        }
//    }
}