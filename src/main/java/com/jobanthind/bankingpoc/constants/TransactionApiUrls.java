package com.jobanthind.bankingpoc.constants;

import static com.jobanthind.bankingpoc.constants.ApiConstants.BASE_URL;

public class TransactionApiUrls {
    public static final String TRANSACTION_BASE_URL =  BASE_URL + "/transactions";
    public static final String TRANSFER_FUNDS_URL = TRANSACTION_BASE_URL + "/transfer";
    public static final String TRANSACTION_HISTORY_URL = TRANSACTION_BASE_URL + "/history/{accountId}";
}
