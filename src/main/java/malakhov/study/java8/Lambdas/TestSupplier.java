package malakhov.study.java8.Lambdas;

import java.util.function.Supplier;

        public class TestSupplier {
            public static void main(String[] args) {
                print(Accounts.getAccount());
                print(() -> Accounts.getAccount());
            }

            public static void print(Account account) {
                //some logic
                System.out.println(account);
            }

            public static void print(Supplier<Account> accountSupplier) {
                //some logic
                System.out.println(accountSupplier.get());
            }
        }

        class Account {
            //some logic
        }

        class Accounts {
            public static Account getAccount() {
                System.out.println("Account is created");
                return new Account();
            }
        }
