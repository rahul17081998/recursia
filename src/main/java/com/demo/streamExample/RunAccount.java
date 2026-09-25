package com.demo.streamExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RunAccount {
    public static List<Account> buildAccounts() {

        // ---- Account 1: ACC1001, USD ----
        List<Account.Transaction> t1 = Arrays.asList(
                new Account.Transaction("TXN1", "CREDIT", 5000.0, "SUCCESS", LocalDate.of(2026, 1, 5)),
                new Account.Transaction("TXN2", "DEBIT",  1200.0, "SUCCESS", LocalDate.of(2026, 1, 8)),
                new Account.Transaction("TXN3", "DEBIT",   1200.0, "FAILED",  LocalDate.of(2026, 1, 9)),
                new Account.Transaction("TXN4", "CREDIT", 2500.0, "PENDING", LocalDate.of(2026, 1, 12))
        );

        // ---- Account 2: ACC1002, INR ----
        List<Account.Transaction> t2 = Arrays.asList(
                new Account.Transaction("TXN5", "CREDIT", 9000.0, "SUCCESS", LocalDate.of(2026, 1, 3)),
                new Account.Transaction("TXN6", "DEBIT",  1200.0, "SUCCESS", LocalDate.of(2025, 1, 6)),
                new Account.Transaction("TXN7", "DEBIT",  4500.0, "PENDING", LocalDate.of(2026, 1, 10)),
                new Account.Transaction("TXN8", "CREDIT", 1500.0, "PENDING", LocalDate.of(2026, 1, 11))
        );

        // ---- Account 3: ACC1003, USD (zero pending — for Q5) ----
        List<Account.Transaction> t3 = Arrays.asList(
                new Account.Transaction("TXN9",  "CREDIT", 12000.0, "SUCCESS", LocalDate.of(2026, 1, 2)),
                new Account.Transaction("TXN10", "DEBIT",   2000.0, "SUCCESS", LocalDate.of(2026, 1, 7)),
                new Account.Transaction("TXN11", "DEBIT",   6000.0, "FAILED",  LocalDate.of(2026, 1, 9))
        );

        // ---- Account 4: ACC1004, EUR (empty transaction list — edge case) ----
        List<Account.Transaction> t4 = new ArrayList<>();

        // ---- Account 5: ACC1005, INR ----
        List<Account.Transaction> t5 = Arrays.asList(
                new Account.Transaction("TXN12", "CREDIT", 7000.0, "SUCCESS", LocalDate.of(2026, 1, 4)),
                new Account.Transaction("TXN13", "DEBIT",  1000.0, "PENDING", LocalDate.of(2026, 1, 13)),
                new Account.Transaction("TXN14", "CREDIT", 3500.0, "FAILED",  LocalDate.of(2026, 1, 14))
        );

        return Arrays.asList(
                new Account("ACC1001", "Aarav Sharma",  "USD", t1),
                new Account("ACC1002", "Bhavna Rao",    "INR", t2),
                new Account("ACC1003", "Charan Mehta",  "USD", t3),
                new Account("ACC1004", "Divya Nair",    "EUR", t4),
                new Account("ACC1005", "Esha Kulkarni", "INR", t5)
        );
    }

    public static void main(String[] args) throws JsonProcessingException {
        List<Account> accounts = buildAccounts();
        System.out.println("Loaded " + accounts.size() + " accounts");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());                       // handles LocalDate
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);    // "2026-01-05" not [2026,1,5]
        mapper.enable(SerializationFeature.INDENT_OUTPUT);                 // pretty-print

        String json = mapper.writeValueAsString(accounts);
        System.out.println(json);

        // write your Stream solutions here

        // Warm-ups

/*
        1. Print the txnId of every transaction with an amount greater than 4000,
        across all accounts.
*/

        List<String> txnIdColl = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .filter(acc -> acc.getAmount() > 4000)
                .map(Account.Transaction::getTxnId)
                .collect(Collectors.toList());
        System.out.println(txnIdColl);




/*
        2. Get a sorted (descending by amount) list of all SUCCESS transactions,
        regardless of account.
*/

        List<Account.Transaction> successTrxns = accounts.stream()
                .flatMap(userAccount -> userAccount.getTransactions().stream())
                .filter(trx -> "SUCCESS".equals(trx.getStatus()))
                .sorted(new Comparator<Account.Transaction>() {

                    @Override
                    public int compare(Account.Transaction o1, Account.Transaction o2) {
                        return Double.compare(o2.getAmount(), o1.getAmount());
                    }
                })
                .collect(Collectors.toList());
        System.out.println(successTrxns);





/*
        3. Count how many distinct currencies appear in the account list.
*/

        Long countDistintCurrency = accounts.stream()
                .map(Account::getCurrency)
                .distinct()
                .collect(Collectors.counting());
        System.out.println(countDistintCurrency);

        // second way
        int size = accounts.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getCurrency(),
                        Collectors.counting()
                ))
                .size();
        System.out.println(size);


        // Collecting into maps

/*
        4. Build a Map<String, String> of accountId → holderName.
*/

        Map<String, String> holderNameByAccId = accounts.stream()
                .collect(Collectors.toMap(
                        a -> a.getAccountId(),
                        a -> a.getHolderName(),
                        (a,b)->a
                ));

        System.out.println(holderNameByAccId);





/*
        5. Partition all transactions into two groups — high value (amount ≥ 5000) and
        the rest — using partitioningBy. Result type: Map<Boolean, List<Transaction>>.
*/

        Map<Boolean, List<Account.Transaction>> booleanListMap = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> {
                            if (t.getAmount() >= 5000) return true;
                            else return false;
                        },
                        Collectors.mapping(t->t, Collectors.toList())
                ));

        System.out.println(booleanListMap);

        // another way
        Map<Boolean, List<Account.Transaction>> booleanListMap2 = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.partitioningBy(
                        tx->tx.getAmount()>=5000,
                        Collectors.mapping(t->t, Collectors.toList())
                ));

        System.out.println(booleanListMap2);

/*
        Tough & but important
        6. For each currency, find the total successful credit amount.
                    Result: Map<String, Double>.
*/

        Map<String, Double> totSuccCredAmountByCurrency = accounts.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getCurrency(),
                        Collectors.summingDouble(a -> a.getTransactions().stream()
                                .filter(t -> "SUCCESS".equals(t.getStatus()) && "CREDIT".equals(t.getType()))
                                .mapToDouble(Account.Transaction::getAmount)
                                .sum()
                        )
                ));

        System.out.println(totSuccCredAmountByCurrency);







        // Nested grouping and stats
/*
        7. Group all transactions by status, and for each status get the count.
        Then do it again getting the average amount instead.
        (Two separate one-liners with groupingBy + a downstream collector.)
*/

        Map<String, Long> countByStatus = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getStatus(),
                        Collectors.counting()
                ));
        System.out.println(countByStatus);

        Map<String, Double> avgAmountByStatus = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getStatus(),
                        Collectors.averagingDouble(t -> t.getAmount())
                ));
        System.out.println(avgAmountByStatus);

/*
        8. Produce a Map<String, Map<String, Long>>
        — currency → (status → count of transactions in that status). This is groupingBy nested inside groupingBy.

*/

        Map<String, Map<String, Long>> stringMapMap = accounts.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getCurrency(),
                      Collectors.flatMapping(a->a.getTransactions().stream(),
                              Collectors.groupingBy(
                                      Account.Transaction::getStatus,
                                      Collectors.counting()
                              ))
                ));
        System.out.println(stringMapMap);


/*
        9. Get a DoubleSummaryStatistics (count, sum, min, max, average) over all debit amounts.
        One call gives you all five numbers.
*/

        DoubleSummaryStatistics doubleSummaryStatistics = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .filter(t -> "DEBIT".equals(t.getType()))
                .mapToDouble(t -> t.getAmount())
                .summaryStatistics();
        System.out.println(doubleSummaryStatistics);


        // Trickier

/*
        10. Find the account with the single highest total transaction amount
        (sum of all its transactions). Return the accountId. You'll need to sum per account,
        then max by that sum.

*/

        String accId=accounts.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getAccountId(),
                        Collectors.summingDouble(a -> a.getTransactions().stream()
                                .mapToDouble(t -> t.getAmount())
                                .sum())
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println(accId);



        // 2nd way
        String accId2=accounts.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getAccountId(),
                        Collectors.summingDouble(a -> a.getTransactions().stream()
                                .mapToDouble(t -> t.getAmount())
                                .sum())
                ))
                .entrySet().stream()
                .max(new Comparator<Map.Entry<String, Double>>() {
                    @Override
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                        return Double.compare(o1.getValue(), o2.getValue());
                    }
                })
                .map(m->m.getKey())
                .orElse(null);
        System.out.println(accId2);




//       3rd way

        String accountId = accounts.stream()
                .max(new Comparator<Account>() {
                    @Override
                    public int compare(Account o1, Account o2) {
                        double sum1 = o1.getTransactions().stream()
                                .mapToDouble(t -> t.getAmount())
                                .sum();
                        double sum2 = o2.getTransactions().stream()
                                .mapToDouble(t -> t.getAmount())
                                .sum();
                        return Double.compare(sum1, sum2);
                    }
                })
                        .map(a->a.getAccountId()).orElse(null);
        System.out.println(accountId);






/*
        11. Build a Map<String, List<String>> of accountId → list of txnIds,
            but only include transactions that are PENDING, and drop accounts that end up with an empty list.
*/


        Map<String, List<String>> pendingByAccount = accounts.stream()
                .collect(Collectors.toMap(
                        a -> a.getAccountId(),
                        a -> a.getTransactions().stream()
                                .filter(t -> "PENDING".equals(t.getStatus()))
                                .map(t -> t.getTxnId())
                                .collect(Collectors.toList())
                ))
                .entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                ));
        System.out.println(pendingByAccount);




/*
        12. Create one flat, comma-separated String of every holder name that has at least one
        FAILED transaction, sorted alphabetically — using filter, map, sorted, and Collectors.joining(", ").
 */

        String allHolderNames = accounts.stream()
                .filter(a -> {
                    boolean anyFailedTrxn = a.getTransactions().stream()
                            .anyMatch(t -> "FAILED".equals(t.getStatus()));
                    return anyFailedTrxn;
                })
                .map(a -> a.getHolderName())
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                })
                .collect(Collectors.joining(","));
        System.out.println(allHolderNames);


//        Classic "find/count" patterns
/*
Q13. Find the SECOND highest transaction amount across all accounts (distinct values).
     Return a double (or Optional<Double>). Watch out for duplicate amounts.
*/


        OptionalDouble secondHighest = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .sorted(new Comparator<Account.Transaction>() {
                    @Override
                    public int compare(Account.Transaction o1, Account.Transaction o2) {
                        return Double.compare(o2.getAmount(), o1.getAmount());
                    }
                })
                .mapToDouble(t -> t.getAmount())
                .distinct()
                .skip(1)
                .findFirst();
        System.out.println(secondHighest);
/*
Q14. Check whether ALL accounts have at least one SUCCESS transaction.
     Return a boolean.
*/

        boolean isAllAccSuccess = accounts.stream()
                .allMatch(a -> {
                    boolean isAtLeastOneSuccess= a.getTransactions().stream()
                            .anyMatch(t -> "SUCCESS".equals(t.getStatus()));
                    return isAtLeastOneSuccess;
                });
        System.out.println(isAllAccSuccess);

/*
Q15. Find the FIRST credit transaction with amount greater than 3000, scanning all
     transactions in order. Return Optional<Account.Transaction>.
*/
        Optional<Account.Transaction> first = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .filter(t -> "CREDIT".equals(t.getType()) && t.getAmount() > 3000)
                .findFirst();
        System.out.println(first);

//        Reduce and aggregation
/*
Q16. Using reduce (NOT sum / mapToDouble), compute the total amount of ALL transactions
     across every account. Return a double.
*/


        Double totalAmount = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .map(t -> t.getAmount())
                .reduce(0.0, (t1, t2) -> t1 + t2);
        System.out.println(totalAmount);
/*
Q17. Find the LONGEST holder name using reduce. Return the name as a String.
*/

        String longname = accounts.stream()

                .map(a -> a.getHolderName())
                .reduce("", (a, b) -> a.length() >= b.length() ? a : b);
        System.out.println(longname);


//        Map manipulation — the bread and butter
/*
Q18. Build a Map<String, Long> of transaction type -> count (DEBIT vs CREDIT),
     across all accounts.
*/

        Map<String, Long> countByType = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getType(),
                        Collectors.counting()
                ));
        System.out.println(countByType);

/*
Q19. Find which transaction type (DEBIT or CREDIT) occurs more frequently overall.
     Return the type as a String.
*/
        Optional<String> frequentTrxnType = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getType(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                        return Double.compare(o1.getValue(), o2.getValue());
                    }
                })
                .map(e -> e.getKey());
        System.out.println(frequentTrxnType);

/*
Q20. Build a Map<String, Double> of accountId -> AVERAGE transaction amount.
     Skip accounts that have no transactions entirely (no NaN, no empty-division issues).
*/

        Map<String, Double> avgAmountByAccId = accounts.stream()
                .filter(a -> !a.getTransactions().isEmpty())
                .collect(Collectors.toMap(
                        a -> a.getAccountId(),
                        a -> a.getTransactions().stream()
                                .mapToDouble(t -> t.getAmount())
                                .average().getAsDouble()
                ));
        System.out.println(avgAmountByAccId);
//        Sorting with tie-breakers
/*
Q21. Sort ALL transactions by amount DESCENDING, then by date ASCENDING as a tie-breaker.
     Return List<Account.Transaction>.

*/

        List<Account.Transaction> collect4 = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .sorted(new Comparator<Account.Transaction>() {
                    @Override
                    public int compare(Account.Transaction o1, Account.Transaction o2) {
                        int result = Double.compare(o2.getAmount(), o1.getAmount());
                        if (result != 0) return result;

                        if (o1.getDate().isBefore(o2.getDate())) {
                            return -1;
                        } else if (o1.getDate().isEqual(o2.getDate())) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                })
                .collect(Collectors.toList());
        System.out.println(collect4);

//        String / collector combos
/*
Q22. Produce a single String like "ACC1001: 4 txns, ACC1002: 4 txns, ..." — each accountId
     with its transaction count, comma-separated, sorted by accountId.
*/

//        The one everyone gets asked
/*
Q23. Find duplicate txnIds, if any exist — group all transactions by txnId and keep only
     the entries whose count is greater than 1. Return Map<String, Long>.
     (With the current data this is empty, but write the reusable pattern.)
*/






        /*
Q1. Group all transactions by their date's MONTH, and for each month collect the
    list of txnIds that occurred in it.
    Result: Map<Integer, List<String>>   (monthNumber -> list of txnIds)
    Map<Integer, List<String>>
*/

        Map<Integer, List<String>> txnIdByMonth = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonthValue(),
                        Collectors.mapping(
                                txn -> txn.getTxnId(),
                                Collectors.toList()
                        )
                ));
        System.out.println(txnIdByMonth);

        // second way
        Map<Integer, List<String>> txnIdByMonth2 = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        txn -> txn.getDate().getMonthValue(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .map(t -> t.getTxnId())
                                        .collect(Collectors.toList())
                        )
                ));
        System.out.println(txnIdByMonth2);


/*
Q2. For each transaction STATUS, find the single transaction with the highest amount
    in that status group.
    Result: Map<String, Optional<Account.Transaction>>   (status -> max txn)

*/

        Map<String, Account.Transaction> hightestTxnByStatus = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        txn -> txn.getStatus(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(new Comparator<Account.Transaction>() {
                                    @Override
                                    public int compare(Account.Transaction o1, Account.Transaction o2) {
                                        return Double.compare(o1.getAmount(), o2.getAmount());
                                    }
                                }),
                                Optional::get
                        )
                ));
        System.out.println(hightestTxnByStatus);


/*
Q3. Group accounts by CURRENCY, and for each currency compute the total sum of all
    transaction amounts belonging to accounts of that currency.
    Result: Map<String, Double>   (currency -> total amount)

currency =kay
total amount belongs to that currency
*/

        Map<String, Double> totalAmountByCurrency = accounts.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getCurrency(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                acc -> acc.stream()
                                        .flatMap(a -> a.getTransactions().stream())
                                        .mapToDouble(t -> t.getAmount())
                                        .sum()

                        )
                ));
        System.out.println(totalAmountByCurrency);


/*
Q4. Group all transactions by TYPE (DEBIT/CREDIT), and for each type produce a
    comma-separated String of its txnIds.
    Result: Map<String, String>   (type -> "TXN1, TXN4, TXN5, ...")

*/
        Map<String, String> collect = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getType(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                txn -> txn.stream()
                                        .map(t -> t.getTxnId())
                                        .collect(Collectors.joining(", "))
                        )
                ));
        System.out.println(collect);


        //2nd way
        Map<String, String> collect1 = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getType(),
                        Collectors.mapping(
                                txn -> txn.getTxnId(),
                                Collectors.joining(", ")
                        )
                ));
        System.out.println(collect1);

/*
Q5. Group all transactions by STATUS, but instead of the default HashMap, collect into
    a TreeMap so the statuses come out in sorted key order, with the count per status.
    Result: TreeMap<String, Long>   (status -> count, keys sorted)
    Downstream: counting(), plus the mapFactory argument (TreeMap::new)
*/

        TreeMap<String, Long> collect2 = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getStatus(),
                        TreeMap::new,
                        Collectors.counting()
                ));
        System.out.println(collect2);

        // question
// {SUCCESS=TXN9(12000), FAILED=TXN11(6000), PENDING=TXN7(4500)}

        Map<String, Account.Transaction> collect3 = accounts.stream()
                .flatMap(a -> a.getTransactions().stream())
                .collect(Collectors.groupingBy(
                        t -> t.getStatus(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(new Comparator<Account.Transaction>() {
                                    @Override
                                    public int compare(Account.Transaction o1, Account.Transaction o2) {
                                        return Double.compare(o1.getAmount(), o2.getAmount());
                                    }
                                }),
                                Optional::get
                        )

                ));
        System.out.println(collect4);

    }









}

